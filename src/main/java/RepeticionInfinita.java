import java.time.LocalDateTime;

public class RepeticionInfinita extends RepeticionEvento {
    public RepeticionInfinita(Frecuencia frecuencia) {
        super(frecuencia);
        this.tipoRepeticion = TipoRepeticion.INFINITA;
        this.esRepeticionInfinita = true;
    }

    @Override
    public boolean esRecurrente(LocalDateTime fecha) {
        return false;
    }

    @Override
    public LocalDateTime proximaFechaInicio(LocalDateTime fecha) {
        return null;
    }

}