import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TareaDiaCompleto extends Tarea{

    private LocalDate fechaTarea;

    public TareaDiaCompleto(String titulo, String descripcion, LocalDate fechaTarea) {
        super(titulo, descripcion);
        this.fechaTarea = fechaTarea;
    }

    public LocalDate getFechaTarea(){
        return this.fechaTarea;
    }
    @Override
    public String tiempoRestanteLegible() {
        Duration duracionRestante = Duration.between(LocalDateTime.now(), this.fechaTarea.atStartOfDay());
        long diasRestantes = duracionRestante.toDays();
        long horasRestantes = duracionRestante.toHours() % 24L;
        long minutosRestantes = duracionRestante.toMinutes() % 60L;
        long segundosRestantes = duracionRestante.getSeconds() % 60L;
        return String.format("%d días, %d horas, %d minutos, %d segundos", diasRestantes, horasRestantes, minutosRestantes, segundosRestantes);
    }
    @Override
    public void mostrarDatos() {
        System.out.println("\nInformación de la tarea:");
        System.out.println("Título: " + super.getTitulo());
        System.out.println("Estado de la tarea: ");
        System.out.println("Descripción: " + super.getDescripcion());
        System.out.println("Tipo de tarea: Con fecha y hora de vencimiento");
        System.out.println("Fecha y hora de vencimiento: " + getFechaTarea() );
        System.out.println("Tiempo restante: " + tiempoRestanteLegible());
    }
}
