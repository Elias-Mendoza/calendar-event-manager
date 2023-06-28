import javafx.util.Pair;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendario implements Serializable, Observable {
    private List<Evento> eventos;
    private List<Tarea> tareas;
    private List<Observer> observers;
    public Calendario() {
        this.eventos = new ArrayList<>();
        this.tareas = new ArrayList<>();
        this.observers = new ArrayList<>();
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

    //Se guarda el estado del Calendario
    public void guardarCalendario(OutputStream os) throws CalendarioNoGuardadoException {
        try (ObjectOutputStream salida = new ObjectOutputStream(os)) {
            salida.writeObject(this);
            System.out.println("Calendario guardado exitosamente.");
            salida.flush();
        } catch (IOException e) {
            throw new CalendarioNoGuardadoException("Error al guardar el calendario: " + e.getMessage());
        }
    }

    public boolean hayActividadesEnFecha(LocalDate fecha) {
        for (int i = 0; i < obtenerEventos().size(); i++){
            if(obtenerEventos().get(i).estaEnFecha(fecha) ){
                return true;
            }
            if(obtenerEventos().get(i).siguienteRepeticionAFecha(fecha.atStartOfDay()) != null &&
            obtenerEventos().get(i).siguienteEventoRepeticionAFecha(fecha.atStartOfDay()).estaEnFecha(fecha)){
                return true;
            }
        }
        for (int i = 0; i < obtenerTareas().size(); i++){
            if(obtenerTareas().get(i).getFechaTarea().isEqual(fecha)){
                return true;
            }
        }
        return false;
    }

    public List<Tarea> obtenerTareasEnFecha(LocalDate fecha){
        List<Tarea> listaTareasEnFecha = new ArrayList<>();
        Tarea tarea;
        for(int i=0; i < obtenerTareas().size(); i++){
            tarea = obtenerTareas().get(i);
            if(tarea.getFechaTarea().isEqual(fecha)){
                listaTareasEnFecha.add(tarea);
            }
        }
        return listaTareasEnFecha;
    }
    public List<Evento> obtenerEventosEnFecha(LocalDate fecha) {
        List<Evento> listaEventosEnFecha = new ArrayList<>();
        Evento evento;
        for (int i = 0; i < obtenerEventos().size(); i++){
            evento = obtenerEventos().get(i);
            if(evento.estaEnFecha(fecha)){
                listaEventosEnFecha.add(evento);
            }
            else if(evento.siguienteEventoRepeticionAFecha(fecha.atStartOfDay()) != null &&
                    evento.siguienteEventoRepeticionAFecha(fecha.atStartOfDay()).estaEnFecha(fecha)){
                listaEventosEnFecha.add(evento.siguienteEventoRepeticionAFecha(fecha.atStartOfDay()));
            }
        }
        return listaEventosEnFecha;
    }
    private void agregarRepeticionesEnFecha(List<Evento> listaEventos, Evento evento, LocalDate fecha){
        if(evento.siguienteEventoRepeticionAFecha(fecha.atStartOfDay()).estaEnFecha(fecha)){
            listaEventos.add(evento.siguienteEventoRepeticionAFecha(fecha.atStartOfDay()));
        }
    }

    static class CalendarioNoGuardadoException extends Exception{
        public CalendarioNoGuardadoException(String mensaje){
            super(mensaje);
        }
    }

    //Se carga el estado del Calendario
    public static Calendario cargarCalendario(InputStream is) {
        try (ObjectInputStream entrada = new ObjectInputStream(is)) {
            Calendario calendario = (Calendario) entrada.readObject();
            System.out.println("Calendario cargado exitosamente");
            return calendario;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el calendario: " + e.getMessage());
            return null;
        }
    }

    //Calcula el horario de la siguiente alarma entre todas las tareas y alarmas del calendario
    public Pair<LocalDateTime, Actividad> calcularSiguienteHorarioAlarma(){
        LocalDateTime proximoHorarioAlarma = LocalDateTime.MAX;
        Actividad actividadDeAlarma = null;
        for(Evento evento : obtenerEventos()){
            if(evento.proximaActivacionAlarma() != null){
                if(evento.proximaActivacionAlarma().isBefore(proximoHorarioAlarma) && evento.proximaActivacionAlarma().isAfter(LocalDateTime.now()) ){
                    proximoHorarioAlarma = evento.proximaActivacionAlarma();
                    actividadDeAlarma = evento;
                }
            }
        }
        for(Tarea tarea : obtenerTareas()){
            if(tarea.proximaActivacionAlarma() != null){
                if(tarea.proximaActivacionAlarma().isBefore(proximoHorarioAlarma) && tarea.proximaActivacionAlarma().isAfter(LocalDateTime.now()) ){
                    proximoHorarioAlarma = tarea.proximaActivacionAlarma();
                    actividadDeAlarma = tarea;
                }
            }
        }
        if(proximoHorarioAlarma == LocalDateTime.MAX){
            proximoHorarioAlarma = null;
        }
        return new Pair<>(proximoHorarioAlarma, actividadDeAlarma);
    }

    public void verificarAlarma() {
        Pair<LocalDateTime, Actividad> resultado = calcularSiguienteHorarioAlarma();
        if (resultado != null) {
            LocalDateTime proximoHorarioAlarma = resultado.getKey();
            Actividad actividad = resultado.getValue();
            System.out.println("Cantidad de observadores: " + observers.size());
            LocalDateTime ahora = LocalDateTime.now();
            System.out.println("FECHA Y HORA ACTUAL " + ahora);
            System.out.println("Proxima alarma: " + proximoHorarioAlarma);
            if (proximoHorarioAlarma != null && proximoHorarioAlarma.isAfter(ahora) && proximoHorarioAlarma.isBefore(ahora.plusSeconds(1))) {
                String mensaje = actividad.getTitulo();
                notifyObservers(mensaje);
            }
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.update(mensaje);
        }
    }
    public void clearObservers(){
        observers.clear();
    }

}
