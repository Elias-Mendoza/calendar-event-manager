import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Calendario miCalendario = new Calendario();

        LocalDateTime inicioEvento1 = LocalDateTime.of(2023, 5, 1, 17, 0);
        LocalDateTime finalEvento1 = LocalDateTime.of(2023, 5, 1, 18, 0);
        EventoConFechaYHora evento1 = new EventoConFechaYHora("Fulbo", "En Sanca", inicioEvento1, finalEvento1);
        miCalendario.agregarEvento(evento1);
        evento1.agregarAlarmaActividad(new Alarma(30));

        LocalDate inicioEvento2 = LocalDate.of(2023, 4, 26);
        LocalDate finEvento2 = LocalDate.of(2023, 4, 26);
        EventoDiaCompleto evento2 = new EventoDiaCompleto("Cumpleaños de Luciano y Nico", "Chano cumple 31, y Nico cumple 15", inicioEvento2, finEvento2);
        miCalendario.agregarEvento(evento2);

        LocalDate inicioEvento3 = LocalDate.of(2023, 7, 3);
        LocalDate finEvento3 = LocalDate.of(2023, 8, 20);
        EventoDiaCompleto evento3 = new EventoDiaCompleto("Vacaciones de invierno", "Sin facultad", inicioEvento3, finEvento3);
        miCalendario.agregarEvento(evento3);

        LocalDateTime inicioEvento4 = LocalDateTime.of(2023, 4, 22, 22, 0);
        LocalDateTime finalEvento4 = LocalDateTime.of(2023, 4, 23, 5, 0);
        EventoConFechaYHora evento4 = new EventoConFechaYHora("Estudio nocturno", "De todas las materias", inicioEvento4, finalEvento4);
        miCalendario.agregarEvento(evento4);

        LocalDateTime fechaVencimientoTarea1 = LocalDateTime.of(2023, 5, 1, 23, 59);
        TareaConFechaYHoraVencimiento tarea1 = new TareaConFechaYHoraVencimiento("Entregar TP Algo3", "Si no lo entrego recurso", fechaVencimientoTarea1);
        miCalendario.agregarTarea(tarea1);

        LocalDateTime inicioEvento7 = LocalDateTime.of(2023, 4, 25, 22, 0);
        LocalDateTime finalEvento7 = LocalDateTime.of(2023, 4, 25, 22, 30);
        Frecuencia frecuenciaEvento7 = new FrecuenciaDiaria(1);
        RepeticionEvento repeticionEvento7 = new RepeticionHastaNRepeticiones(frecuenciaEvento7, 10);
        EventoConFechaYHora evento7 = new EventoConFechaYHora("Cenar", "En casa", inicioEvento7, finalEvento7);
        evento7.agregarRepeticion(repeticionEvento7);
        miCalendario.agregarEvento(evento7);

        for (int i = 0; i < miCalendario.obtenerEventos().size(); i++) {
            miCalendario.obtenerEventos().get(i).mostrarDatos();
        }
    }

}