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
        return this.frecuencia;
    }
    public void setFrecuencia(Frecuencia frecuencia){
        this.frecuencia = frecuencia;
    }

}