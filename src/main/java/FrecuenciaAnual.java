import java.time.LocalDate;
import java.time.LocalDateTime;

public class FrecuenciaAnual extends Frecuencia {
    public FrecuenciaAnual() {
        this.tipoFrecuencia = TipoFrecuencia.ANUAL;
    }

    @Override
    public LocalDateTime siguienteFechaYHora(LocalDateTime fechaYHoraOrigen) {
        LocalDateTime siguienteFechaHora = fechaYHoraOrigen.plusYears(1);
        LocalDate fechaAniversario = LocalDate.of(fechaYHoraOrigen.getYear(), fechaYHoraOrigen.getMonthValue(), fechaYHoraOrigen.getDayOfMonth());
        if (!siguienteFechaHora.toLocalDate().isEqual(fechaAniversario)) {
            siguienteFechaHora = siguienteFechaHora.withMonth(fechaAniversario.getMonthValue()).withDayOfMonth(fechaAniversario.getDayOfMonth());
            if (siguienteFechaHora.isBefore(fechaYHoraOrigen)) {
                siguienteFechaHora = siguienteFechaHora.plusYears(1);
            }
        }
        return siguienteFechaHora;
    }

}
/*    @Override
    public boolean seRepiteEn(LocalDate fecha) {
        return fecha.getDayOfYear() == getFechaInicio().getDayOfYear();
    }*/