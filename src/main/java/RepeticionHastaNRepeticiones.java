import java.time.LocalDate;
import java.time.LocalDateTime;

public class RepeticionHastaNRepeticiones extends RepeticionEvento {
    public RepeticionHastaNRepeticiones(Frecuencia frecuencia, int cantidadRepeticiones) {
        super(frecuencia);
        super.cantidadRepeticiones = cantidadRepeticiones;
        this.tipoRepeticion = TipoRepeticion.HASTA_N_REPETICIONES;
        this.esRepeticionInfinita = false;
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


/*    public LocalDate calcularFechaLimite(){
        switch (this.frecuencia.getTipoFrecuencia()){
            case DIARIA:
                return CalcularFechaLimiteConFrecuenciaDiaria();
            case SEMANAL:
                return CalcularFechaLimiteConFrecuenciaSemanal();
            case MENSUAL:
                return CalcularFechaLimiteConFrecuenciaMensual();
            case ANUAL:
                return CalcularFechaLimiteConFrecuenciaAnual();
        }
    }
 */