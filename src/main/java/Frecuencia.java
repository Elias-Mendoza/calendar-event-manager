import java.time.LocalDateTime;

public abstract class Frecuencia {

    public Frecuencia() {
    }
    /* Calcula la siguiente fecha en la que se repetirá el evento después de la fecha y hora de inicio de evento pasada como argumento,
    teniendo en cuenta la frecuencia que tenga el evento
    (La fecha pasada como argumento debe ser una fecha de inicio del evento o de alguna repetición)
    */
    public abstract LocalDateTime siguienteFechaYHoraDeInicioEvento(LocalDateTime fechaYHoraEvento);
}