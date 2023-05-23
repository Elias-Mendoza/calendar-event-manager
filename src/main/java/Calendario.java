import java.io.*;
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

    //Se guarda el estado del Calendario, se cierra el archivo luego de utilizarlo.
    public void guardarCalendario(String archivo) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(this);
            System.out.println("Calendario guardado exitosamente en: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el calendario: " + e.getMessage());
        }
    }

    //Se carga el estado del Calendario, se cierra el archivo luego de utilizarlo.
    public static Calendario cargarCalendario(String archivo) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            Calendario calendario = (Calendario) entrada.readObject();
            System.out.println("Calendario cargado exitosamente desde: " + archivo);
            return calendario;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el calendario: " + e.getMessage());
            return null;
        }
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

}
