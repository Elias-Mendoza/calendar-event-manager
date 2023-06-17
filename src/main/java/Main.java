import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args){
        Calendario miCalendario;

        // Carga el calendario desde el archivo o crea uno nuevo si no existe
        try (InputStream is = new FileInputStream("calendario.dat")) {
            miCalendario = Calendario.cargarCalendario(is);
            if (miCalendario == null) {
                // No se pudo cargar el calendario, crea uno nuevo
                miCalendario = new Calendario();
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo. Se creará un nuevo calendario.");
            miCalendario = new Calendario();
        }

        // Verifica si existen eventos y tareas cargados previamente
        if (!miCalendario.obtenerEventos().isEmpty() || !miCalendario.obtenerTareas().isEmpty()) {
            System.out.println("Eventos y tareas existentes en el calendario cargado:");
            for (Evento evento : miCalendario.obtenerEventos()) {
                evento.mostrarDatos();
            }
            for (Tarea tarea : miCalendario.obtenerTareas()) {
                tarea.mostrarDatos();
            }
        } else {
            System.out.println("No hay eventos ni tareas existentes en el calendario cargado.");
        }

        // Agrega nuevos eventos y tareas al calendario
        LocalDateTime inicioEvento1 = LocalDateTime.of(2023, 5, 1, 17, 0);
        LocalDateTime finalEvento1 = LocalDateTime.of(2023, 5, 1, 18, 0);
        EventoConFechaYHora evento1 = new EventoConFechaYHora("Fulbo", "En Sanca", inicioEvento1, finalEvento1);
        evento1.agregarAlarmaActividad(new Alarma(30));
        miCalendario.agregarEvento(evento1);

        LocalDate inicioEvento3 = LocalDate.of(2023, 7, 3);
        LocalDate finEvento3 = LocalDate.of(2023, 8, 20);
        EventoDiaCompleto evento3 = new EventoDiaCompleto("Vacaciones de invierno", "Sin facultad", inicioEvento3, finEvento3);
        miCalendario.agregarEvento(evento3);

        LocalDateTime fechaVencimientoTarea1 = LocalDateTime.of(2023, 5, 1, 23, 59);
        TareaConFechaYHoraVencimiento tarea1 = new TareaConFechaYHoraVencimiento("Entregar TP Algo3", "Etapa2", fechaVencimientoTarea1);
        miCalendario.agregarTarea(tarea1);

        // Guarda el calendario actualizado en el archivo
        try (OutputStream os = new FileOutputStream("calendario.dat")) {
            miCalendario.guardarCalendario(os);
        } catch (IOException e) {
            System.out.println("Error al guardar el calendario: " + e.getMessage());
        }
    }

}