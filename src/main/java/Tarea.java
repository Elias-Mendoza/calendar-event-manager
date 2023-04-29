import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Tarea extends Actividad{
    private LocalDateTime fechaYHoraVencimiento;
    private boolean estaCompletada;
    public Tarea(String titulo, String descripcion){
        super(titulo, descripcion);
        this.estaCompletada = false;
    }
    public Duration tiempoRestanteAlVencimiento() {
        Duration tiempoRestante;
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.isAfter(this.fechaYHoraVencimiento)) {
            tiempoRestante = Duration.ZERO;
//            return Duration.ZERO;
        } else {
            tiempoRestante = Duration.between(ahora, this.fechaYHoraVencimiento);
//            return Duration.between(ahora, this.fechaVencimiento);
        }
        return tiempoRestante;
    }
    public abstract String tiempoRestanteLegible();

/*    public void agregarAlarma(Alarma alarma) {
        this.alarmas.add(alarma);
    }*/

    public void completarTarea(){
        this.estaCompletada = true;
    }

    public LocalDateTime getFechaVencimiento() {
        return this.fechaYHoraVencimiento;
    }

    public void setFechaYHoraVencimiento(LocalDateTime fechaYHoraVencimiento) {
        this.fechaYHoraVencimiento = fechaYHoraVencimiento;
    }
}