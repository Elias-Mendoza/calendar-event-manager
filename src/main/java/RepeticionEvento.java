import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class RepeticionEvento {
    protected Frecuencia frecuencia;
    protected TipoRepeticion tipoRepeticion;
    protected LocalDate fechaLimite;
    protected int cantidadRepeticiones;
    boolean esRepeticionInfinita;
    enum TipoRepeticion{
        INFINITA, HASTA_X_FECHA, HASTA_N_REPETICIONES
    }
    public RepeticionEvento(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Frecuencia getFrecuencia() {
        return frecuencia;
    }
    public LocalDate getFechaSiguiente(LocalDate fecha) {
        return null;
    }

    //Recibe una fecha y hora, y devuelve True en caso que el evento deba repetirse en esa fecha, false en caso contrario.
    public abstract boolean esRecurrente(LocalDateTime fecha);

    //Recibe una fecha por parámetro, y devuelve la fecha de la próxima repetición del evento
    public abstract LocalDateTime proximaFechaInicio(LocalDateTime fecha);


}