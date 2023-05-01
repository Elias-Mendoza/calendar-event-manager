import java.time.*;
import java.util.EnumSet;


public class Main {
    public static void main(String[] args) {
        Calendario miCalendario = new Calendario();


        LocalDateTime inicioEvento1 = LocalDateTime.of(2023, 5, 1, 17, 0);
        LocalDateTime finalEvento1 = LocalDateTime.of(2023, 5, 1, 18, 0);
        EventoConFechaYHora evento1 = new EventoConFechaYHora("Fulbo", "Con los pi en Sanca", inicioEvento1, finalEvento1);
        miCalendario.agregarEvento(evento1);
//        miCalendario.agregarAlarma(evento1, new Alarma(30));



//        miCalendario.mostrarDatosEvento(evento1);


        LocalDate inicioEvento2 = LocalDate.of(2023, 4, 26);
        LocalDate finEvento2 = LocalDate.of(2023, 4, 26);
        EventoDiaCompleto evento2 = new EventoDiaCompleto("Cumpleaños de Luciano y Nico", "Chano cumple 31, y Nico cumple 15", inicioEvento2, finEvento2);
        miCalendario.agregarEvento(evento2);

//        miCalendario.mostrarDatosEvento(evento2);


        LocalDate inicioEvento3 = LocalDate.of(2023, 7, 3);
        LocalDate finEvento3 = LocalDate.of(2023, 8, 20);
        EventoDiaCompleto evento3 = new EventoDiaCompleto("Vacaciones de invierno", "Sin facultad", inicioEvento3, finEvento3);
        miCalendario.agregarEvento(evento3);

//        miCalendario.mostrarDatosEvento(evento3);


        LocalDateTime inicioEvento4 = LocalDateTime.of(2023, 4, 22, 22, 0);
        LocalDateTime finalEvento4 = LocalDateTime.of(2023, 4, 23, 5, 0);
        EventoConFechaYHora evento4 = new EventoConFechaYHora("Estudio nocturno", "De todas las materioas", inicioEvento4, finalEvento4);
        miCalendario.agregarEvento(evento4);

//        miCalendario.mostrarDatosEvento(evento4);

        LocalDateTime fechaVencimientoTarea1 = LocalDateTime.of(2023, 5, 1, 23, 59);
        TareaConFechaYHoraVencimiento tarea1 = new TareaConFechaYHoraVencimiento("Entregar TP Algo3", "Si no lo entrego recurso", fechaVencimientoTarea1);
        miCalendario.agregarTarea(tarea1);

        LocalDate fechaTarea2 = LocalDate.of(2023, 5, 2);
        TareaDiaCompleto tarea2 = new TareaDiaCompleto("Ordenar la casa", "", fechaTarea2);
        miCalendario.agregarTarea(tarea2);
//        miCalendario.agregarAlarma(tarea1, new Alarma(10));
//        miCalendario.agregarAlarma(tarea2, new Alarma(20));


//        miCalendario.eliminarEvento(evento1);
//        miCalendario.eliminarEvento(evento2);


        LocalDateTime inicioEvento5 = LocalDateTime.of(2023, 4, 28, 16, 0);
        LocalDateTime finalEvento5 = LocalDateTime.of(2023, 4, 28, 17, 0);
        Frecuencia frecuenciaEvento5 = new FrecuenciaSemanal(EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SUNDAY, DayOfWeek.WEDNESDAY));
        RepeticionEvento repeticionEvento5 = new RepeticionHastaNRepeticiones(frecuenciaEvento5, 10);
        EventoConFechaYHora evento5 = new EventoConFechaYHora("Fulboooooooooo con los virgos", "En Sanca", inicioEvento5, finalEvento5, repeticionEvento5);
        miCalendario.agregarEvento(evento5);

        LocalDateTime inicioEvento6 = LocalDateTime.of(2024, 3, 31, 22, 30);
        LocalDateTime finalEvento6 = LocalDateTime.of(2024, 3, 31, 23, 30);
        Frecuencia frecuenciaEvento6 = new FrecuenciaMensual();
        RepeticionEvento repeticionEvento6 = new RepeticionHastaNRepeticiones(frecuenciaEvento6, 10);
        EventoConFechaYHora evento6 = new EventoConFechaYHora("Meditación del mes", "En casa", inicioEvento6, finalEvento6, repeticionEvento6);
        miCalendario.agregarEvento(evento6);

        LocalDateTime inicioEvento7 = LocalDateTime.of(2023, 4, 25, 22, 0);
        LocalDateTime finalEvento7 = LocalDateTime.of(2023, 4, 25, 22, 30);
        Frecuencia frecuenciaEvento7 = new FrecuenciaDiaria(1);
        RepeticionEvento repeticionEvento7 = new RepeticionHastaNRepeticiones(frecuenciaEvento7, 10);
        EventoConFechaYHora evento7 = new EventoConFechaYHora("Cenar", "En casa", inicioEvento7, finalEvento7, repeticionEvento7);
        miCalendario.agregarEvento(evento7);

        System.out.println("\nEL EVENTO 7 TIENE: " + evento7.getRepeticion().cantidadRepeticiones + " REPETICIONES.");

        System.out.println("\nEl evento 7 se repite en:");
        System.out.println(evento7.siguienteRepeticionAFecha(LocalDateTime.of(2023, 4, 26, 0, 0)));
//        miCalendario.agregarAlarma(evento7, new Alarma(30));

        LocalDate inicioEvento8 = LocalDate.of(2023, 5, 27);
        LocalDate finalEvento8 = LocalDate.of(2023, 5, 27);
        Frecuencia frecuenciaEvento8 = new FrecuenciaAnual();
        RepeticionEvento repeticionEvento8 = new RepeticionInfinita(frecuenciaEvento8);
        EventoDiaCompleto evento8 = new EventoDiaCompleto("Cumpleaños de Elias", "", inicioEvento8, finalEvento8, repeticionEvento8);
        miCalendario.agregarEvento(evento8);


        /*for (int i = 0; i < miCalendario.obtenerEventos().size(); i++) {
            miCalendario.obtenerEventos().get(i).mostrarDatos();
//            miCalendario.obtenerEventos().get(i).mostrarRepeticiones();
        }*/

        evento3.siguienteRepeticionAFecha(LocalDateTime.of(2023,4,6,5,30));

        miCalendario.modificarTitulo(tarea2, "Este es un nuevo título");

        /*for (int i = 0; i < miCalendario.obtenerTareas().size(); i++){
            miCalendario.obtenerTareas().get(i).mostrarDatos();
        }*/

        miCalendario.mostrarDatosEvento(evento8);
        miCalendario.agregarAlarma(evento8, new Alarma(30));
        System.out.println("\nSIGUIENTE HORARIO ALARMA: " + miCalendario.calcularSiguienteHorarioAlarma());
        evento8.modificarInicioYFin( LocalDate.of(2023,5, 29), LocalDate.of(2023,5, 29));
        miCalendario.mostrarDatosEvento(evento8);
        System.out.println("\nSIGUIENTE HORARIO ALARMA: " + miCalendario.calcularSiguienteHorarioAlarma());


        LocalDateTime inicioEvento9 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime finalEvento9 = LocalDateTime.of(2023, 5, 1, 18, 0);
        Frecuencia frecuenciaEvento9 = new FrecuenciaDiaria(1);
        RepeticionEvento repeticionEvento9 = new RepeticionHastaFecha(frecuenciaEvento9, LocalDate.of(2023,5,5));
        EventoConFechaYHora evento9 = new EventoConFechaYHora("PROGRAMAR", "En casa", inicioEvento9, finalEvento9, repeticionEvento9);
        miCalendario.agregarEvento(evento9);

        System.out.println("\nEL EVENTO 9 TIENE: " + evento9.getRepeticion().cantidadRepeticiones + " REPETICIONES.");
    }

}