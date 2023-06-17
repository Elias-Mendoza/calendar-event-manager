import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FrecuenciaAnual extends Frecuencia implements Serializable {
    public FrecuenciaAnual() {
    }

    @Override
    public LocalDateTime siguienteFechaYHoraDeInicioEvento(LocalDateTime fechaYHora) {
        LocalDateTime siguienteFechaHora = fechaYHora.plusYears(1);
        LocalDate fechaAniversario = LocalDate.of(fechaYHora.getYear(), fechaYHora.getMonthValue(), fechaYHora.getDayOfMonth());
        if (!siguienteFechaHora.toLocalDate().isEqual(fechaAniversario)) {
            siguienteFechaHora = siguienteFechaHora.withMonth(fechaAniversario.getMonthValue()).withDayOfMonth(fechaAniversario.getDayOfMonth());
            if (siguienteFechaHora.isBefore(fechaYHora)) {
                siguienteFechaHora = siguienteFechaHora.plusYears(1);
            }
        }
        return siguienteFechaHora;
    }
}