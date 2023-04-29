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
        int contador = this.repeticion.cantidadRepeticiones;
        LocalDateTime repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(inicio);
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
        return (repeticionADevolver);
    }
    public void modificarFrecuencia(Frecuencia nuevaFecuencia){
        this.getRepeticion().setFrecuencia(nuevaFecuencia);
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
