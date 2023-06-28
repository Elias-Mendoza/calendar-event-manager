import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Evento extends Actividad implements Serializable, Cloneable{
    protected LocalDateTime inicio;
    protected LocalDateTime fin;
    protected RepeticionEvento repeticion;
    public Evento(String titulo, String descripcion, LocalDateTime inicio, LocalDateTime fin) {
        super(titulo, descripcion);
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public Evento clone() {
        try {
            return (Evento) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no poder clonar
            return null;
        }
    }

    //Se devuelve la fecha y hora de la siguiente repetición después de la fecha pasada por parámetro. devuelve NULL en caso que no haya una repetición.
    public LocalDateTime siguienteRepeticionAFecha(LocalDateTime fechaYHora){
        LocalDateTime repeticionADevolver = null;
        if(fechaYHora.isBefore(this.inicio)){
            repeticionADevolver = this.inicio;
        }
        else if(hayRepeticion()){
            repeticionADevolver = repeticion.calcularSiguienteRepeticion(fechaYHora, this.inicio);
        }
        return (repeticionADevolver);
    }
    public Evento siguienteEventoRepeticionAFecha(LocalDateTime fecha) {

        Evento evento = null;
        LocalDateTime comienzoInstancia = siguienteRepeticionAFecha(fecha);

        if(comienzoInstancia != null){
            Duration duracion = Duration.between(this.inicio, this.fin);
            LocalDateTime finInstancia = comienzoInstancia.plus(duracion);

            evento = this.clone();
            evento.setInicio(comienzoInstancia);
            evento.setFin(finInstancia);
        }

        return evento;
    }
    public boolean estaEnFecha(LocalDate fecha){
        boolean estaContenidoEnLaFecha = false;
        if (getInicio().toLocalDate().isEqual(fecha) || getFin().toLocalDate().isEqual(fecha)){
         estaContenidoEnLaFecha = true;
        }
        else if( fecha.isAfter(getInicio().toLocalDate()) && fecha.isBefore(getFin().toLocalDate()) ) {
            estaContenidoEnLaFecha = true;
        }
        return estaContenidoEnLaFecha;
    }
    public long getDiasEntreInicioYFin(){
        int dias;
        Duration duracion= Duration.between(inicio, fin);
        return duracion.toDays();
    }

    @Override
    public void agregarAlarmaActividad(Alarma alarma){
        System.out.println("momentoActivacion = " + calcularProximaActivacionAlarmaMinutosAntes(alarma));
        if(alarma.getTipo() == Alarma.TipoAlarma.DE_MINUTOS_ANTES){
            alarma.setMomentoActivacion(calcularProximaActivacionAlarmaMinutosAntes(alarma));
        }
        System.out.println("Alarma = " + alarma);

        this.alarmas.add(alarma);
    }

    private LocalDateTime calcularProximaActivacionAlarmaMinutosAntes(Alarma alarma){
        LocalDateTime horarioRepeticion = this.siguienteRepeticionAFecha(LocalDateTime.now());
        System.out.println("Calculo de horario repeticion: " + horarioRepeticion);
        if(horarioRepeticion == null){
            return null;
        }
        return (horarioRepeticion.minusMinutes(alarma.getMinutosAntes()));
    }
    public void setInicio(LocalDateTime inicio){
        this.inicio = inicio;
    }
    public void setFin(LocalDateTime fin){
        this.fin = fin;
    }


    //Agrega la repetición pasada por parámetro, y actualiza la cantidad de repeticiones en caso de ser
    //necesario (que sea un evento con tipo de repetición hasta una fecha)
    public void agregarRepeticion(RepeticionEvento repeticion){
        this.repeticion = repeticion;
        repeticion.actualizarCantidadRepeticiones(this.inicio);
    }

    //Devuelve true en caso que el evento tenga repetición.
    public boolean hayRepeticion(){
        return (this.repeticion != null);
    }

    //Modifica el tipo de repetición del evento por el pasado por parámetro.
    public void modificarRepeticionEvento(RepeticionEvento nuevaRepeticionEvento){
        this.setRepeticionEvento(nuevaRepeticionEvento);
    }

    //Modifica la frecuencia del evento y actualiza su cantidad de repeticiones.
    public void modificarFrecuenciaEvento(Frecuencia frecuencia){
        this.getRepeticion().setFrecuencia(frecuencia);
        this.getRepeticion().actualizarCantidadRepeticiones(this.inicio);
    }
    public void setRepeticionEvento(RepeticionEvento repeticion){
        this.repeticion = repeticion;
    }

    public LocalDateTime getInicio(){
        return (this.inicio);
    }
    public LocalDateTime getFin(){
        return (this.fin);
    }

    public RepeticionEvento getRepeticion() {
        return this.repeticion;
    }

    public String tiempoRestanteLegible() {
        Duration duracionRestante = Duration.between(LocalDateTime.now(), this.fin);
        long diasRestantes = duracionRestante.toDays();
        long horasRestantes = duracionRestante.toHours() % 24L;
        long minutosRestantes = duracionRestante.toMinutes() % 60L;
        long segundosRestantes = duracionRestante.getSeconds() % 60L;
        return String.format("%d días, %d horas, %d minutos, %d segundos", diasRestantes, horasRestantes, minutosRestantes, segundosRestantes);
    }
}