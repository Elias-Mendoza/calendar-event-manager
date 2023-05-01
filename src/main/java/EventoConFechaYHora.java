import java.time.LocalDateTime;

public class EventoConFechaYHora extends Evento{

    //Crea un evento con fecha y hora fija con los datos pasados por parámetro
    public EventoConFechaYHora(String titulo, String descripcion, LocalDateTime inicio, LocalDateTime fin){
        super(titulo, descripcion, inicio, fin);
    }


    //Modifica el inicio y el fin del evento.
    public void modificarInicioYFin(LocalDateTime nuevoInicio, LocalDateTime nuevoFin){
        this.inicio = nuevoInicio;
        this.fin = nuevoFin;
        actualizarAlarmas();
    }

    public LocalDateTime getFin() {
        return (this.fin);
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
