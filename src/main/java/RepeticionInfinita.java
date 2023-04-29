import java.time.LocalDateTime;

public class RepeticionInfinita extends RepeticionEvento {
    public RepeticionInfinita(Frecuencia frecuencia) {
        super(frecuencia);
        this.tipoRepeticion = TipoRepeticion.INFINITA;
        this.esRepeticionInfinita = true;
    }

}