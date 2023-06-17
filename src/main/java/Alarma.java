import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class Alarma implements Serializable {
    private LocalDateTime momentoActivacion;
    private final TipoAlarma tipoAlarma;
    private int minutosAntes;
    public enum TipoAlarma {
        FECHA_HORA_ABSOLUTA,
        DE_MINUTOS_ANTES
    }

    //Constructor de una alarma con una fecha y hora de activación.
    public Alarma(LocalDateTime momento) {
        this.momentoActivacion = momento;
        this.tipoAlarma = TipoAlarma.FECHA_HORA_ABSOLUTA;
        this.minutosAntes = -1;
    }

    //Constructor de una alarma con parámetro como los minutos antes al evento para su activación
    public Alarma(int minutosAntes) {
        this.tipoAlarma = TipoAlarma.DE_MINUTOS_ANTES;
        this.minutosAntes = minutosAntes;
    }

    public TipoAlarma getTipo(){
        return tipoAlarma;
    }

    public LocalDateTime getMomentoActivacion() {
        return this.momentoActivacion;
    }

    public void setMomentoActivacion(LocalDateTime momentoActivacion) {
        this.momentoActivacion = momentoActivacion;
    }

    public int getMinutosAntes() {
        return this.minutosAntes;
    }
    public void setMinutosAntes(int minutosAntes) {
        this.minutosAntes = minutosAntes;
    }
    //Devuelve true en caso que la fecha y hora pasada por parámetro coincida con la fecha y hora de activacion la alarma
    public boolean debeSonar(LocalDateTime fechaYHora){
        return (this.momentoActivacion.isEqual(fechaYHora));
    }

    //Devuelve la duración del tiempo restante entre la fecha y hora pasada por parámetro y la hora que debe sonar la alarma
    public Duration tiempoRestante(LocalDateTime fechaYHora){
        return Duration.between(this.momentoActivacion, fechaYHora);
    }
}
