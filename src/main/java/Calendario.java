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

    //Modifica el título de un evento o tarea.
    public void modificarTitulo(Actividad actividad, String nuevoTitulo) {
        actividad.modificarTituloActividad(nuevoTitulo);
    }

    //Modifica la descripción de un evento o tarea.
    public void modificarDescripcion(Actividad actividad, String nuevaDescripcion){
        actividad.modificarDescripcionActividad(nuevaDescripcion);
    }

    //Agrega un tipo de repetición pasado por parámetro al evento que también se pasa por parámetro
    public void agregarRepeticionEvento(Evento evento, RepeticionEvento repeticion){
        evento.agregarRepeticion(repeticion);
    }

    //Agrega una alarma que es pasada por parámetro a un Evento o Tarea
    public void agregarAlarma(Actividad actividad, Alarma alarma) {
        actividad.agregarAlarmaActividad(alarma);
    }

    //Calcula el horario de la siguiente alarma entre todas las tareas y alarmas del calendario
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

    //Modifica la fecha y hora de una tarea con fecha y hora de vencimiento
    public void modificarFechaYHoraVencimientoTarea(TareaConFechaYHoraVencimiento tarea, LocalDateTime nuevaFechaYHoraVencimiento){
        tarea.modificarFechaYHoraVencimiento(nuevaFechaYHoraVencimiento);
    }

    //Modifica la fecha de una tarea de día completo
    public void modificarFechaTareaDiaCompleto(TareaDiaCompleto tarea, LocalDate nuevaFechaTarea){
        tarea.modificarFecha(nuevaFechaTarea);
    }

    //Modifica el tipo de repetición del evento por el pasado por parámetro.
    public void modificarRepeticion(Evento evento, RepeticionEvento nuevaRepeticionEvento){
        evento.modificarRepeticionEvento(nuevaRepeticionEvento);
    }

    //Modifica la frecuencia del evento pasado por parámetro por la frecuencia pasada por parámetro
    public void modificarFrecuencia(Evento evento, Frecuencia nuevaFrecuencia){
        evento.modificarFrecuenciaEvento(nuevaFrecuencia);
    }

    //Modifica el inicio y el fin de un evento con fecha y hora de inicio y fin arbitrarios.
    public void modificarInicioYFinEvento(EventoConFechaYHora evento, LocalDateTime nuevoInicio, LocalDateTime nuevoFin){
        evento.modificarInicioYFin(nuevoInicio, nuevoFin);
    }

    //Modifica el inicio y el fin de un evento de dia completo.
    public void modificarInicioYFinEvento(EventoDiaCompleto evento, LocalDate nuevoInicio, LocalDate nuevoFin){
        evento.modificarInicioYFin(nuevoInicio, nuevoFin);
    }
    public void mostrarDatosEvento(Evento evento) {
        evento.mostrarDatos();
    }
}
