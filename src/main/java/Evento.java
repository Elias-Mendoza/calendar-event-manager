import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Evento extends Actividad{
    protected LocalDateTime inicio;
    protected LocalDateTime fin;
    protected RepeticionEvento repeticion;
    public Evento(String titulo, String descripcion, LocalDateTime inicio, LocalDateTime fin) {
        super(titulo, descripcion);
        this.inicio = inicio;
        this.fin = fin;
    }


    //Se devuelve la fecha y hora de la siguiente repetición después de la fecha pasada por parámetro. devuelve NULL en caso que no haya repetición.
    public LocalDateTime siguienteRepeticionAFecha(LocalDateTime fechaYHora){
        LocalDateTime repeticionADevolver = null;
        if(fechaYHora.isBefore(this.inicio)){
            repeticionADevolver = this.inicio;
        }
        else if(hayRepeticion()){
            repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHoraDeInicioEvento(this.inicio);
            if(this.repeticion.esRepeticionInfinita){
                while(repeticionADevolver.isBefore(fechaYHora)){
                    repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHoraDeInicioEvento(repeticionADevolver);
                }
            }
            else{
                int contador = this.repeticion.cantidadRepeticiones;
                while(repeticionADevolver.isBefore(fechaYHora) && contador > 0){
                    repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHoraDeInicioEvento(repeticionADevolver);
                    contador--;
                }
                if(contador==0){
                    repeticionADevolver = null;
                }
            }
        }

        return (repeticionADevolver);
    }

    @Override
    public void agregarAlarmaActividad(Alarma alarma){
        if(alarma.getTipo() == Alarma.TipoAlarma.DE_MINUTOS_ANTES){
            alarma.setMomentoActivacion(calcularProximaActivacionAlarmaMinutosAntes(alarma));
        }
        this.alarmas.add(alarma);
    }

    private LocalDateTime calcularProximaActivacionAlarmaMinutosAntes(Alarma alarma){
        LocalDateTime horarioRepeticion = this.siguienteRepeticionAFecha(LocalDateTime.now());
        if(horarioRepeticion == null){
            return null;
        }
        return (horarioRepeticion.minusMinutes(alarma.getMinutosAntes()));
    }

    //Devuelve la hora de la próxima alarma del evento, devuelve null en caso que no haya.
    public LocalDateTime proximaActivacionAlarma(){
        LocalDateTime proximoHorarioAlarma = LocalDateTime.MAX;
        for(Alarma alarma : alarmas){
            if(alarma.getMomentoActivacion().isBefore(proximoHorarioAlarma)){
                proximoHorarioAlarma = alarma.getMomentoActivacion();
            }
        }
        if(proximoHorarioAlarma == LocalDateTime.MAX){
            proximoHorarioAlarma = null;
        }
        return proximoHorarioAlarma;
    }

    //Agrega la repetición pasada por parámetro, y actualiza la cantidad de repeticiones en caso de ser
    //necesario (que sea un evento con tipo de repetición hasta una fecha)
    public void agregarRepeticion(RepeticionEvento repeticion){
        this.repeticion = repeticion;
        if(repeticion.tipoRepeticion == RepeticionEvento.TipoRepeticion.HASTA_X_FECHA){
            repeticion.setCantidadRepeticiones(calcularCantidadRepeticionesEventoHastaFecha(repeticion));
        }
    }
    //Calcula la cantidad de repeticiones de un evento con repetición de tipo "hasta fecha determinada"
    private int calcularCantidadRepeticionesEventoHastaFecha(RepeticionEvento repeticion){
        int contadorRepeticiones = 1;
        LocalDateTime fechaYHora = this.inicio;
        LocalDateTime momentoLimite = repeticion.getFechaLimite().plusDays(1).atStartOfDay();
        while(fechaYHora.isBefore(momentoLimite)){
            fechaYHora = repeticion.frecuencia.siguienteFechaYHoraDeInicioEvento(fechaYHora);
            if(fechaYHora.isBefore(momentoLimite)){
                contadorRepeticiones++;
            }
        }
        return contadorRepeticiones;
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
        this.getRepeticion().setCantidadRepeticiones( calcularCantidadRepeticionesEventoHastaFecha(this.getRepeticion()) );
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