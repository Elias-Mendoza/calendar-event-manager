import java.time.LocalDate;
import java.time.LocalDateTime;

public class RepeticionHastaFecha extends RepeticionEvento {
    public RepeticionHastaFecha(Frecuencia frecuencia, LocalDate fechaLimite) {
        super(frecuencia);
        super.fechaLimite = fechaLimite;
        this.tipoRepeticion = TipoRepeticion.HASTA_X_FECHA;
        this.esRepeticionInfinita = false;
    }
}
