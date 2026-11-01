import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TareaConFechaYHoraVencimiento extends Tarea implements Serializable {
    private LocalDateTime fechaYHoraVencimiento;

    public TareaConFechaYHoraVencimiento(String titulo, String descripcion, LocalDateTime fechaYHoraVencimiento){
        super(titulo, descripcion);
        this.fechaYHoraVencimiento = fechaYHoraVencimiento;
    }

    @Override
    public LocalDateTime getFechaYHoraVencimiento() {
        return this.fechaYHoraVencimiento;
    }
    public void setFechaYHoraVencimiento(LocalDateTime fechaYHoraVencimiento) {
        this.fechaYHoraVencimiento = fechaYHoraVencimiento;
    }

    //Devuelve el tiempo restante a la fecha y hora de vencimiento de la tarea.
    public Duration tiempoRestanteAlVencimiento() {
        Duration tiempoRestante;
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.isAfter(this.fechaYHoraVencimiento)) {
            tiempoRestante = Duration.ZERO;
        } else {
            tiempoRestante = Duration.between(ahora, this.fechaYHoraVencimiento);
        }
        return tiempoRestante;
    }

    //Modifica la fecha y hora de vencimiento de la tarea.
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
    public String tiempoRestanteLegible()  {
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
        System.out.println("Tipo de tarea: CON FECHA Y HORA DE VENCIMIENTO");
        System.out.println("Fecha y hora de vencimiento: " + getFechaYHoraVencimiento() );
        System.out.println("Tiempo restante: " + tiempoRestanteLegible());
    }
    @Override
    public LocalDate getFechaTarea(){
        return this.fechaYHoraVencimiento.toLocalDate();
    }

}
