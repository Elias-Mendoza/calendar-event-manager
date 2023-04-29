import java.time.LocalDate;
import java.time.LocalDateTime;

public class FrecuenciaMensual extends Frecuencia {
    public FrecuenciaMensual() {
        this.tipoFrecuencia = TipoFrecuencia.MENSUAL;
    }

    @Override
    public int calcularCantidadRepeticionesHechas(LocalDateTime fechaYHoraOrigen) {
        int cantidadRepeticiones = 0;
        LocalDateTime fechaYHora = fechaYHoraOrigen;
        if(fechaYHora.isBefore(LocalDateTime.now())){
            cantidadRepeticiones++;
        }
        while(siguienteFechaYHora(fechaYHora).isBefore(LocalDateTime.now())){
            fechaYHora = siguienteFechaYHora(fechaYHora);
            cantidadRepeticiones++;
        }
        return cantidadRepeticiones;
    }

    @Override
    public LocalDateTime siguienteFechaYHora(LocalDateTime fecha) {
        LocalDate siguienteFecha = fecha.plusMonths(1).toLocalDate(); // Convertir a LocalDate
        int maxDia = siguienteFecha.getMonth().length(siguienteFecha.isLeapYear());
        LocalDate siguienteFechaValida = LocalDate.of(siguienteFecha.getYear(), siguienteFecha.getMonth(), Math.min(fecha.getDayOfMonth(), maxDia));
        if (siguienteFechaValida.isBefore(fecha.toLocalDate()) || siguienteFechaValida.isEqual(fecha.toLocalDate())) { // Convertir a LocalDate
            siguienteFechaValida = siguienteFechaValida.plusMonths(1);
        }
        return siguienteFechaValida.atTime(fecha.toLocalTime()); // Convertir a LocalDateTime
    }

}

/*    @Override
    public boolean seRepiteEn(LocalDate fecha) {
        if (!super.seRepiteEn(fecha)) {
            return false;
        }
        return fecha.getDayOfMonth() == diaDelMes;
    }*/