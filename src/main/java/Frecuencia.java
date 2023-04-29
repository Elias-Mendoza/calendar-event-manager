import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Frecuencia {
    protected TipoFrecuencia tipoFrecuencia;
    enum TipoFrecuencia{
        DIARIA, SEMANAL, MENSUAL, ANUAL
    }

    public Frecuencia() {
    }

    public abstract int calcularCantidadRepeticionesHechas(LocalDateTime fechaYHoraOrigen);
    public TipoFrecuencia getTipoFrecuencia(){
        return this.tipoFrecuencia;
    }

//    public abstract boolean seRepiteEn(LocalDate fecha);

    /* Calcula la siguiente fecha en la que se repetirá el evento después de la fecha pasada como argumento,
    teniendo en cuenta los días de la semana en los que se repite el evento.
    */
    public abstract LocalDateTime siguienteFechaYHora(LocalDateTime fechaYHora);
}