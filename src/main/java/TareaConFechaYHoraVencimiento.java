import java.time.Duration;
import java.time.LocalDateTime;

public class TareaConFechaYHoraVencimiento extends Tarea{
    private LocalDateTime fechaYHoraVencimiento;

    public TareaConFechaYHoraVencimiento(String titulo, String descripcion, LocalDateTime fechaYHoraVencimiento){
        super(titulo, descripcion);
        this.fechaYHoraVencimiento = fechaYHoraVencimiento;
    }

    public LocalDateTime getFechaYHoraVencimiento() {
        return this.fechaYHoraVencimiento;
    }
    public void setFechaYHoraVencimiento(LocalDateTime fechaYHoraVencimiento) {
        this.fechaYHoraVencimiento = fechaYHoraVencimiento;
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
    public void modificarFechaYHoraVencimiento(LocalDateTime nuevaFechaYHoraVencimiento){
        this.fechaYHoraVencimiento = nuevaFechaYHoraVencimiento;
        actualizarAlarmas();
    }

    @Override
    public void agregarAlarmaActividad(Alarma alarma) {
        if(alarma.getTipo() == Alarma.TipoAlarma.DE_MINUTOS_ANTES){
            alarma.setMomentoActivacion(this.fechaYHoraVencimiento.minusMinutes(alarma.getMinutosAntes()));
        }
        this.alarmas.add(alarma);
    }

    @Override
    public String tiempoRestanteLegible() {
        Duration duracionRestante = Duration.between(LocalDateTime.now(), this.fechaYHoraVencimiento);
        long diasRestantes = duracionRestante.toDays();
        long horasRestantes = duracionRestante.toHours() % 24;
        long minutosRestantes = duracionRestante.toMinutes() % 60;
        long segundosRestantes = duracionRestante.getSeconds() % 60;

        return String.format("%d días, %d horas, %d minutos, %d segundos", diasRestantes, horasRestantes, minutosRestantes, segundosRestantes);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("\nInformación de la tarea:");
        System.out.println("Título: " + super.getTitulo());
        System.out.println("Estado de la tarea: ");
        System.out.println("Descripción: " + super.getDescripcion());
        System.out.println("Tipo de tarea: Con fecha y hora de vencimiento");
        System.out.println("Fecha y hora de vencimiento: " + getFechaYHoraVencimiento() );
        System.out.println("Tiempo restante: " + tiempoRestanteLegible());
    }

}
