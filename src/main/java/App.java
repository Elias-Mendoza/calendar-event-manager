import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class App extends Application  implements Serializable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        mainCalendario();

        Calendario calendario;
        //CARGAMOS EL MODELO
        // Carga el calendario desde el archivo o crea uno nuevo si no existe
        try (InputStream is = new FileInputStream("calendario.dat")) {
            calendario = Calendario.cargarCalendario(is);
            if (calendario == null) {
                // No se pudo cargar el calendario, crea uno nuevo
                calendario = new Calendario();
            }
        } catch (NotSerializableException e){
            System.out.println("No se pudo serializar el archivo para cargar: " + e.getMessage());
            calendario = new Calendario();
        } catch (IOException e) {
            System.out.println("No se encontró el archivo. Se creará un nuevo calendario."+ e.getMessage());
            calendario = new Calendario();
        }
        //FIN CARGAR EL MODELO

        //CARGAMOS LA VISTA
        VistaPrincipal vistaPrincipal = new VistaPrincipal(primaryStage, calendario);
        VistaDia vistaDia = new VistaDia(primaryStage, calendario);
        VistaSemana vistaSemana = new VistaSemana(primaryStage, calendario);
        VistaMes vistaMes = new VistaMes(primaryStage, calendario);

        //CARGAMOS EL CONTROLADOR
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(calendario, vistaPrincipal);
        ControladorDia controladorDia = new ControladorDia(calendario, vistaDia);
        ControladorSemana controladorSemana = new ControladorSemana(calendario, vistaSemana);
        ControladorMes controladorMes = new ControladorMes(calendario, vistaMes);

        controladorPrincipal.setVistas(vistaDia, vistaSemana, vistaMes);
        controladorPrincipal.setControladores(controladorDia, controladorSemana, controladorMes);

        vistaPrincipal.mostrarVista();
        vistaPrincipal.cambiarVista(vistaMes);
        controladorPrincipal.iniciar();
        controladorMes.iniciar();

        // Guarda el calendario actualizado en el archivo
        try (OutputStream os = new FileOutputStream("calendario.dat")) {
            calendario.guardarCalendario(os);
        } catch (NotSerializableException e){
            System.out.println("No se puede serializar el archivo: "+e.getMessage());
        } catch (Calendario.CalendarioNoGuardadoException e) {
            System.out.println("Error al guardar el calendario: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void mainCalendario(){
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

        LocalDate inicioEvento2 = LocalDate.of(2023, 5, 27);
        LocalDate finalEvento2 = LocalDate.of(2023, 5, 27);
        EventoDiaCompleto evento2 = new EventoDiaCompleto("Cumpleaños Elias", "Sin descripción", inicioEvento2, finalEvento2);
        FrecuenciaAnual frecuenciaEvento2 = new FrecuenciaAnual();
        RepeticionInfinita repeticionEvento2 = new RepeticionInfinita(frecuenciaEvento2);
        evento2.agregarRepeticion(repeticionEvento2);
        miCalendario.agregarEvento(evento2);

        LocalDate inicioEvento3 = LocalDate.of(2023, 7, 3);
        LocalDate finEvento3 = LocalDate.of(2023, 8, 20);
        EventoDiaCompleto evento3 = new EventoDiaCompleto("Vacaciones de invierno", "Sin facultad", inicioEvento3, finEvento3);
        miCalendario.agregarEvento(evento3);

        LocalDateTime fechaVencimientoTarea1 = LocalDateTime.of(2024, 2, 15, 23, 59);
        TareaConFechaYHoraVencimiento tarea1 = new TareaConFechaYHoraVencimiento("Entregar TP Algo3", "Etapa2", fechaVencimientoTarea1);
        miCalendario.agregarTarea(tarea1);

        LocalDateTime inicioEvento4 = LocalDateTime.of(2023, 6, 28, 3, 30);
        LocalDateTime finalEvento4 = LocalDateTime.of(2023, 6, 28, 4, 0);
        EventoConFechaYHora evento4 = new EventoConFechaYHora("Estudio nocturno", "Hasta tarde", inicioEvento4, finalEvento4);
        evento4.agregarAlarmaActividad(new Alarma(8));
        miCalendario.agregarEvento(evento4);

        // Guarda el calendario actualizado en el archivo
        try (OutputStream os = new FileOutputStream("calendario.dat")) {
            miCalendario.guardarCalendario(os);
        } catch (Calendario.CalendarioNoGuardadoException e) {
            System.out.println("Error al guardar el calendario: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}




