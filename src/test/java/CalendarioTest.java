import javafx.util.Pair;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CalendarioTest {

    @Test
    public void alarmaEnRepeticionInfinitaEnEventoFrecuenciaAnualDelAnio3000() {
        Calendario miCalendario = new Calendario();
        LocalDate inicioEvento = LocalDate.of(3000,5,27);
        LocalDate finEvento = LocalDate.of(3000,5,27);
        Frecuencia frecuencia = new FrecuenciaAnual();
        RepeticionEvento repeticion = new RepeticionInfinita(frecuencia);
        Evento evento = new EventoDiaCompleto("Cumpleaños", "...", inicioEvento, finEvento);

        evento.agregarRepeticion(repeticion);
        miCalendario.agregarEvento(evento);
        evento.agregarAlarmaActividad(new Alarma(10));

        LocalDateTime fechaYHoraEsperada = LocalDateTime.of(3000, 5,26,23,50);
        Pair<LocalDateTime, Actividad> resultado = miCalendario.calcularSiguienteHorarioAlarma();
        LocalDateTime proximoHorarioAlarma = resultado.getKey();

        assertEquals(fechaYHoraEsperada, proximoHorarioAlarma );
    }

    @Test
    public void alarmaEnRepeticionInfinitaEnEventoFrecuenciaMensualDelAnio3000() {
        Calendario miCalendario = new Calendario();
        LocalDate inicioEvento = LocalDate.of(3000, 5, 27);
        LocalDate finEvento = LocalDate.of(3000, 5, 27);
        Frecuencia frecuencia = new FrecuenciaMensual();
        RepeticionEvento repeticion = new RepeticionInfinita(frecuencia);
        Evento evento = new EventoDiaCompleto("Cumpleaños", "...", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);
        evento.agregarAlarmaActividad(new Alarma(10));
        miCalendario.agregarEvento(evento);

        LocalDateTime fechaYHoraEsperada = LocalDateTime.of(3000, 5,26,23,50);

        Pair<LocalDateTime, Actividad> resultado = miCalendario.calcularSiguienteHorarioAlarma();
        LocalDateTime proximoHorarioAlarma = resultado.getKey();

        assertEquals(fechaYHoraEsperada, proximoHorarioAlarma );
    }

    @Test
    public void siguienteAlarmaAlEliminarEvento() {
        Calendario miCalendario = new Calendario();
        LocalDate inicioEvento = LocalDate.of(1000,5,27);
        LocalDate finEvento = LocalDate.of(1000,5,27);
        Frecuencia frecuencia = new FrecuenciaMensual();
        RepeticionEvento repeticion = new RepeticionInfinita(frecuencia);
        Evento evento = new EventoDiaCompleto("Cumpleaños", "...", inicioEvento, finEvento);
        miCalendario.agregarEvento(evento);
        evento.agregarRepeticion(repeticion);
        evento.agregarAlarmaActividad(new Alarma(10));
        miCalendario.eliminarEvento(evento);

        Pair<LocalDateTime, Actividad> resultado = miCalendario.calcularSiguienteHorarioAlarma();
        LocalDateTime proximoHorarioAlarma = resultado.getKey();

        assertEquals(null, proximoHorarioAlarma);

    }

    @Test
    public void testPersistencia() {
        // Crea una instancia de Calendario y se agregan eventos y tareas
        Calendario calendario = new Calendario();

        LocalDateTime inicioEvento1 = LocalDateTime.of(2023, 10, 1, 17, 0);
        LocalDateTime finalEvento1 = LocalDateTime.of(2023, 10, 1, 18, 0);
        EventoConFechaYHora evento1 = new EventoConFechaYHora("Evento1", "Descripcion evento", inicioEvento1, finalEvento1);
        Frecuencia frecuencia1 = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion1 = new RepeticionHastaNRepeticiones(frecuencia1, 5);
        evento1.agregarRepeticion(repeticion1);
        evento1.agregarAlarmaActividad(new Alarma(30));

        LocalDateTime fechaVencimientoTarea1 = LocalDateTime.of(2023, 10, 2, 22, 00);
        TareaConFechaYHoraVencimiento tarea1 = new TareaConFechaYHoraVencimiento("Tarea1", "Descripcion tarea", fechaVencimientoTarea1);

        calendario.agregarEvento(evento1);
        calendario.agregarTarea(tarea1);

        // Se guarda el calendario en un ByteArrayOutputStream
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            calendario.guardarCalendario(byteOut);
        } catch (Calendario.CalendarioNoGuardadoException e) {
            throw new RuntimeException(e);
        }

        // Se carga el calendario desde el ByteArrayOutputStream
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        Calendario calendarioCargado = calendario.cargarCalendario(byteIn);

        // Verifica que los eventos y tareas cargados sean correctos
        assertNotNull(calendarioCargado);
        assertEquals(1, calendarioCargado.obtenerEventos().size());
        assertEquals("Evento1", calendarioCargado.obtenerEventos().get(0).getTitulo());
        assertEquals("Descripcion evento", calendarioCargado.obtenerEventos().get(0).getDescripcion());
        assertTrue(calendarioCargado.obtenerEventos().get(0).getRepeticion().getFrecuencia() instanceof FrecuenciaDiaria);
        assertTrue(calendarioCargado.obtenerEventos().get(0).getRepeticion() instanceof RepeticionHastaNRepeticiones);
        assertSame(calendarioCargado.obtenerEventos().get(0).getAlarmas().get(0).getTipo(), Alarma.TipoAlarma.DE_MINUTOS_ANTES);


        assertEquals(1, calendarioCargado.obtenerTareas().size());
        assertEquals("Tarea1", calendarioCargado.obtenerTareas().get(0).getTitulo());
        assertEquals("Descripcion tarea", calendarioCargado.obtenerTareas().get(0).getDescripcion());
        assertTrue(calendarioCargado.obtenerTareas().get(0) instanceof TareaConFechaYHoraVencimiento);
        assertEquals(fechaVencimientoTarea1, ((TareaConFechaYHoraVencimiento) calendarioCargado.obtenerTareas().get(0)).getFechaYHoraVencimiento());
    }


}