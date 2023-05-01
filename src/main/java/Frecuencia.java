import java.time.LocalDateTime;

public abstract class Frecuencia {

    public Frecuencia() {
    }
    /* Calcula la siguiente fecha en la que se repetirá el evento después de la fecha pasada como argumento,
    teniendo en cuenta los días de la semana en los que se repite el evento.
    */
    public abstract LocalDateTime siguienteFechaYHora(LocalDateTime fechaYHora);
}