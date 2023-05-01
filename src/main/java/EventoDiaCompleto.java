import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventoDiaCompleto extends Evento {

    //Crea un evento de día completo con los datos pasados por parámetro
    public EventoDiaCompleto(String titulo, String descripcion, LocalDate inicio, LocalDate fin) {
        super(titulo, descripcion, inicio.atStartOfDay(), LocalDateTime.of(fin.getYear(), fin.getMonth(), fin.getDayOfMonth(), 23, 59 ) );
    }

    //Modifica el inicio y el fin del evento.
    public void modificarInicioYFin(LocalDate nuevoInicio, LocalDate nuevoFin){
        this.inicio = nuevoInicio.atStartOfDay();
        this.fin = nuevoFin.plusDays(1).atStartOfDay();
        actualizarAlarmas();
    }

    @Override
    public void mostrarDatos(){
        System.out.println("\nInformación del evento:");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descripción: " + getDescripcion());
        System.out.println("Tipo de evento: DE DIA COMPLETO");
        System.out.println("Inicio: " + (getInicio()));
        System.out.println("Fin: " + (getFin()));
        System.out.println("Tiempo restante: " + this.tiempoRestanteLegible());
    }
}