import java.util.ArrayList;
import java.util.List;

public class Calendario {
    private List<Evento> eventos;
    private List<Tarea> tareas;
    public Calendario() {
        this.eventos = new ArrayList<>();
        this.tareas = new ArrayList<>();
    }
    public void agregarEvento(Evento evento) {
        this.eventos.add(evento);
    }

    public void eliminarEvento(Evento evento) {
        this.eventos.remove(evento);
    }
    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    public List<Evento> obtenerEventos() {
        return (this.eventos);
    }
    public List<Tarea> obtenerTareas(){
        return (this.tareas);
    }

    public void mostrarDatosEvento(Evento evento) {
        evento.mostrarDatos();
    }
}
