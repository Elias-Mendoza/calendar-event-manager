import java.time.LocalDate;
import java.time.LocalDateTime;
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
        actividad.modificarTituloActividad(nuevoTitulo);
    }
    public void modificarDescripcion(Actividad actividad, String nuevaDescripcion){
        actividad.modificarDescripcionActividad(nuevaDescripcion);
    }
    public void agregarRepeticionEvento(Evento evento, RepeticionEvento repeticion){
        evento.agregarRepeticion(repeticion);
    }
    public void agregarAlarma(Actividad actividad, Alarma alarma) {
        actividad.agregarAlarmaActividad(alarma);
    }

    public LocalDateTime calcularSiguienteHorarioAlarma(){
        LocalDateTime proximoHorarioAlarma = LocalDateTime.MAX;
        for(Evento evento : obtenerEventos()){
            if(evento.proximaActivacionAlarma() != null){
                if(evento.proximaActivacionAlarma().isBefore(proximoHorarioAlarma) && evento.proximaActivacionAlarma().isAfter(LocalDateTime.now()) ){
                    proximoHorarioAlarma = evento.proximaActivacionAlarma();
                }
            }
        }
        for(Tarea tarea : obtenerTareas()){
            if(tarea.proximaActivacionAlarma() != null){
                if(tarea.proximaActivacionAlarma().isBefore(proximoHorarioAlarma) && tarea.proximaActivacionAlarma().isAfter(LocalDateTime.now()) ){
                    proximoHorarioAlarma = tarea.proximaActivacionAlarma();
                }
            }
        }
        if(proximoHorarioAlarma == LocalDateTime.MAX){
            proximoHorarioAlarma = null;
        }
        return proximoHorarioAlarma;
    }

    public void modificarFechaYHoraVencimientoTarea(TareaConFechaYHoraVencimiento tarea, LocalDateTime nuevaFechaYHoraVencimiento){
        tarea.modificarFechaYHoraVencimiento(nuevaFechaYHoraVencimiento);
    }
    public void modificarFechaTareaDiaCompleto(TareaDiaCompleto tarea, LocalDate nuevaFechaTarea){
        tarea.modificarFecha(nuevaFechaTarea);
    }
    public void modificarRepeticion(Evento evento, RepeticionEvento nuevaRepeticionEvento){
        evento.modificarRepeticionEvento(nuevaRepeticionEvento);
    }
    public void modificarFrecuencia(Evento evento, Frecuencia nuevaFrecuencia){
        evento.modificarFrecuenciaEvento(nuevaFrecuencia);
    }
    public void modificarInicioYFinEvento(EventoConFechaYHora evento, LocalDateTime nuevoInicio, LocalDateTime nuevoFin){
        evento.modificarInicioYFin(nuevoInicio, nuevoFin);
    }
    public void modificarInicioYFinEvento(EventoDiaCompleto evento, LocalDate nuevoInicio, LocalDate nuevoFin){
        evento.modificarInicioYFin(nuevoInicio, nuevoFin);
    }
    public void mostrarDatosEvento(Evento evento) {
        evento.mostrarDatos();
    }
}
