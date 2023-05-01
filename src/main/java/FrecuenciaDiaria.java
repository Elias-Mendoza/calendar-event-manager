import java.time.LocalDateTime;

public class FrecuenciaDiaria extends Frecuencia {

    // Es cada cuantos días se repite el evento
    private int intervalo;

    public FrecuenciaDiaria(int intervalo) {
        this.intervalo = intervalo;
    }

    public int getIntervalo() {
        return intervalo;
    }

    @Override
    public LocalDateTime siguienteFechaYHora(LocalDateTime fechaYHora) {
        LocalDateTime siguienteFecha = fechaYHora.plusDays(intervalo);
        return siguienteFecha;
    }
}

/*    @Override
    public boolean seRepiteEn(LocalDate fecha) {
        if (!super.seRepiteEn(fecha)) {
            return false;
        }
        long dias = ChronoUnit.DAYS.between(getFechaInicio(), fecha);
        return dias % intervalo == 0;
    }*/