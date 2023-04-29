import java.time.Duration;
import java.time.LocalDateTime;

public class EventoConFechaYHora extends Evento{

    //Crea un evento con fecha y hora de inicio sin repeticiones
    public EventoConFechaYHora(String titulo, String descripcion, LocalDateTime inicio, LocalDateTime fin){
        super(titulo, descripcion, inicio, fin);
    }
    //Crea un evento con fecha y hora de inicio con repeticiones
    public EventoConFechaYHora(String titulo, String descripcion, LocalDateTime inicio, LocalDateTime fin, RepeticionEvento repeticionEvento){
        super(titulo, descripcion, inicio, fin);
        super.repeticion = repeticionEvento;
//        super.crearRepeticiones();
    }

    public LocalDateTime getFin() {
        return (this.fin);
    }
    @Override
    public String tiempoRestanteLegible() {
        Duration duracionRestante = Duration.between(LocalDateTime.now(), this.fin);
        long diasRestantes = duracionRestante.toDays();
        long horasRestantes = duracionRestante.toHours() % 24L;
        long minutosRestantes = duracionRestante.toMinutes() % 60L;
        long segundosRestantes = duracionRestante.getSeconds() % 60L;
        return String.format("%d días, %d horas, %d minutos, %d segundos", diasRestantes, horasRestantes, minutosRestantes, segundosRestantes);
    }
    @Override
    public void mostrarDatos(){
        System.out.println("\nInformación del evento:");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descripción: " + getDescripcion());
        System.out.println("Tipo de evento: CON FECHA Y HORA FIJA");
        System.out.println("Inicio: " + getInicio());
        System.out.println("Fin: " + getFin());
        System.out.println("Tiempo restante: " + this.tiempoRestanteLegible());
    }
}
