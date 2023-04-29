import java.time.Duration;
import java.time.LocalDateTime;

public class Alarma {
    private LocalDateTime momento;
    private Duration minutosAntes;
    private TipoAlarma tipoAlarma;

    public Alarma(LocalDateTime momento) {
        this.momento = momento;
        this.tipoAlarma = TipoAlarma.FECHA_HORA_ABSOLUTA;
    }

    public Alarma(Duration minutosAntes) {
        this.minutosAntes = minutosAntes;
        this.tipoAlarma = TipoAlarma.TIEMPO_RELATIVO;
    }

    public LocalDateTime getMomento() {
        return this.momento;
    }

    public void setMomento(LocalDateTime momento) {
        this.momento = momento;
    }

    public Duration getMinutosAntes() {
        return this.minutosAntes;
    }

    public void setMinutosAntes(Duration minutosAntes) {
        this.minutosAntes = minutosAntes;
    }

    public boolean debeSonar(LocalDateTime ahora) {
        LocalDateTime momentoAlarma = this.momento.minus(this.minutosAntes);
        return ahora.isAfter(momentoAlarma);
    }

    public Duration tiempoRestante(LocalDateTime ahora) {
        LocalDateTime momentoAlarma = this.momento.minus(this.minutosAntes);
        return Duration.between(ahora, momentoAlarma);
    }
}
enum TipoAlarma {
    FECHA_HORA_ABSOLUTA,
    TIEMPO_RELATIVO;
}