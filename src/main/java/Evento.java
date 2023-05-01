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
    public void actualizarAlarmas(){
        for(Alarma alarma : this.alarmas){
            if(alarma.getTipo() == Alarma.TipoAlarma.DE_MINUTOS_ANTES){
                agregarAlarmaActividad(alarma);
                alarmas.remove(alarma);
            }
        }
    }
    private LocalDateTime calcularProximaActivacionAlarmaMinutosAntes(Alarma alarma){
        LocalDateTime horarioRepeticion = this.siguienteRepeticionAFecha(LocalDateTime.now());
        if(horarioRepeticion == null){
            return null;
        }
        return (horarioRepeticion.minusMinutes(alarma.getMinutosAntes()));
    }
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
    public boolean hayRepeticion(){
        return (this.repeticion != null);
    }
    public void modificarFin(LocalDateTime fin){
        this.fin = fin;
    }
    public void modificarRepeticionEvento(RepeticionEvento nuevaRepeticionEvento){
        this.setRepeticionEvento(nuevaRepeticionEvento);
    }
    public void modificarFrecuenciaEvento(Frecuencia frecuencia){
        this.getRepeticion().setFrecuencia(frecuencia);
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

/*//Se devuelve la fecha y hora de la siguiente repetición después de la fecha pasada por parámetro. devuelve NULL en caso que no haya repetición.
    public LocalDateTime siguienteRepeticionAFecha(LocalDateTime fechaYHora){
        LocalDateTime repeticionADevolver = null;
        if(hayRepeticion()){
            int contador = this.repeticion.cantidadRepeticiones;
            repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(inicio);
            if(repeticionADevolver != null){
                if(this.repeticion.esRepeticionInfinita){
                    while(repeticionADevolver.isBefore(fechaYHora)){
                        repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(repeticionADevolver);
                    }
                }
                else{
                    while(repeticionADevolver.isBefore(fechaYHora) && contador > 0){
                        repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(repeticionADevolver);
                        contador--;
                    }
                    if(contador==0){
                        repeticionADevolver = null;
                    }
                }
            }
        }
        return (repeticionADevolver);
    }*/
