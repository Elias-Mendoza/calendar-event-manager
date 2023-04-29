import java.time.LocalDate;
import java.time.LocalDateTime;

public class FrecuenciaAnual extends Frecuencia {
    public FrecuenciaAnual() {
        this.tipoFrecuencia = TipoFrecuencia.ANUAL;
    }

    @Override
    public int calcularCantidadRepeticionesHechas(LocalDateTime fechaYHoraOrigen) {
        LocalDateTime fechaYHora = fechaYHoraOrigen;
        int cantidadRepeticiones = 0;
        if(fechaYHora.isBefore((LocalDateTime.now()))){
            cantidadRepeticiones++;
        }
        while(siguienteFechaYHora(fechaYHora).isBefore(LocalDateTime.now())){
            cantidadRepeticiones++;
            fechaYHora = siguienteFechaYHora(fechaYHora);
        }
        return cantidadRepeticiones;
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