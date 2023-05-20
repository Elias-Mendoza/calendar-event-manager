import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class RepeticionEvento {
    protected Frecuencia frecuencia;

    public RepeticionEvento(Frecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    // Se devuelve la fecha y hora de la siguiente repetición del evento después de la FECHA Y HORA pasada por parámetro.
    // Devuelve NULL en caso que no haya repetición.
    public abstract LocalDateTime calcularSiguienteRepeticion(LocalDateTime fechaYHora, LocalDateTime inicio);

    // Actualiza la cantidad de repeticiones del evento.
    public abstract void actualizarCantidadRepeticiones(LocalDateTime inicio);

    public Frecuencia getFrecuencia() {
        return this.frecuencia;
    }
    public void setFrecuencia(Frecuencia frecuencia){
        this.frecuencia = frecuencia;
    }

}