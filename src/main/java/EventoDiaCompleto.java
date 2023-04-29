import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventoDiaCompleto extends Evento {

    //Crea un evento de dia completo sin repeticiones.
    public EventoDiaCompleto(String titulo, String descripcion, LocalDate inicio, LocalDate fin) {
        super(titulo, descripcion, inicio.atStartOfDay(), LocalDateTime.of(fin.getYear(), fin.getMonth(), fin.getDayOfMonth(), 23, 59 ) );
    }

    //Crea un evento de dia completo con Repeticiones
    public EventoDiaCompleto(String titulo, String descripcion, LocalDate inicio, LocalDate fin, RepeticionEvento repeticionEvento) {
        super(titulo, descripcion, inicio.atStartOfDay(), LocalDateTime.of(fin.getYear(), fin.getMonth(), fin.getDayOfMonth(), 23, 59 ) );
        super.repeticion = repeticionEvento;
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