import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumSet;

public class FrecuenciaSemanal extends Frecuencia {
    EnumSet<DayOfWeek> diasSemana;

    public FrecuenciaSemanal(EnumSet<DayOfWeek> diasSemana) {
        this.diasSemana = diasSemana;
        this.tipoFrecuencia = TipoFrecuencia.SEMANAL;
        System.out.println("DIAS DE LA SEMANA QUE SE REPITE: "+ diasSemana.toString() );
    }

    public void agregarDiaSemana(DayOfWeek dia) {
        this.diasSemana.add(dia);
    }

    public void eliminarDiaSemana(DayOfWeek dia) {
        this.diasSemana.remove(dia);
    }

    @Override
    public int calcularCantidadRepeticionesHechas(LocalDateTime fechaYHoraOrigen) {
        int contadorRepeticiones = 0;
        LocalDateTime fechaYHora = fechaYHoraOrigen;
        if(fechaYHora.isBefore(LocalDateTime.now())){
            contadorRepeticiones++;
        }
        while(siguienteFechaYHora(fechaYHora).isBefore(LocalDateTime.now() )){
            fechaYHora = siguienteFechaYHora(fechaYHora);
            contadorRepeticiones++;
        }
        return contadorRepeticiones;
    }

    @Override
    public LocalDateTime siguienteFechaYHora(LocalDateTime fechaHoraEvento) {
        EnumSet<DayOfWeek> diasQueSeRepite = this.diasSemana;
        LocalDateTime siguienteFechaHora = fechaHoraEvento.plusDays(1);

        while (!diasQueSeRepite.contains(siguienteFechaHora.getDayOfWeek())) {
            siguienteFechaHora = siguienteFechaHora.plusDays(1);
        }

        return siguienteFechaHora.with(LocalTime.of(fechaHoraEvento.getHour(), fechaHoraEvento.getMinute()));
    }
}
