import java.io.Serializable;
import java.time.LocalDateTime;

public class FrecuenciaDiaria extends Frecuencia implements Serializable {

    // Es cada cuantos días se repite el evento
    private final int intervalo;

    public FrecuenciaDiaria(int intervalo) {
        this.intervalo = intervalo;
    }

    public int getIntervalo() {
        return intervalo;
    }

    @Override
    public LocalDateTime siguienteFechaYHoraDeInicioEvento(LocalDateTime fechaYHora) {
        LocalDateTime siguienteFecha = fechaYHora.plusDays(intervalo);
        return siguienteFecha;
    }
}
