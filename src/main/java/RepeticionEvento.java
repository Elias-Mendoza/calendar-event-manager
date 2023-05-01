import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;

public abstract class RepeticionEvento {
    protected Frecuencia frecuencia;
    protected LocalDate fechaLimite;
    protected int cantidadRepeticiones;
    boolean esRepeticionInfinita;
    protected TipoRepeticion tipoRepeticion;

    enum TipoRepeticion{
        INFINITA, HASTA_X_FECHA, HASTA_N_REPETICIONES
    }
    public RepeticionEvento(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }


    public void setCantidadRepeticiones(int cantidadRepeticiones){
        this.cantidadRepeticiones = cantidadRepeticiones;
    }
    public LocalDate getFechaLimite() {
        return this.fechaLimite;
    }
    public Frecuencia getFrecuencia() {
        return this.frecuencia;
    }
    public void setFrecuencia(Frecuencia frecuencia){
        this.frecuencia = frecuencia;
    }

}