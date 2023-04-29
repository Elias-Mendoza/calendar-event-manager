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

    public void modificarTitulo(Actividad actividad, String nuevoTitulo) {
        if (actividad instanceof Evento) {
            int i = eventos.indexOf(actividad);
            if (i != -1) {
                eventos.get(i).modificarTituloActividad(nuevoTitulo);
            }
        } else if (actividad instanceof Tarea) {
            int i = tareas.indexOf(actividad);
            if (i != -1) {
                tareas.get(i).modificarTituloActividad(nuevoTitulo);
            }
        }
    }

    public void modificarRepeticionEvento(Evento evento, RepeticionEvento nuevaRepeticionEvento){
        evento.modificarRepeticionEvento(nuevaRepeticionEvento);
    }

    public void mostrarDatosEvento(Evento evento) {
        evento.mostrarDatos();
    }
}
