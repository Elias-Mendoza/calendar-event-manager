import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumSet;

public class FrecuenciaSemanal extends Frecuencia implements Serializable {
    EnumSet<DayOfWeek> diasSemana;

    public FrecuenciaSemanal(EnumSet<DayOfWeek> diasSemana) {
        this.diasSemana = diasSemana;
    }

    @Override
    public LocalDateTime siguienteFechaYHoraDeInicioEvento(LocalDateTime fechaHoraEvento) {
        EnumSet<DayOfWeek> diasQueSeRepite = this.diasSemana;
        LocalDateTime siguienteFechaHora = fechaHoraEvento.plusDays(1);

        while (!diasQueSeRepite.contains(siguienteFechaHora.getDayOfWeek())) {
            siguienteFechaHora = siguienteFechaHora.plusDays(1);
        }

        return siguienteFechaHora.with(LocalTime.of(fechaHoraEvento.getHour(), fechaHoraEvento.getMinute()));
    }
}
