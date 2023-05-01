import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CalendarioTest {

    @Test
    public void alarmaEnRepeticionInfinitaEnEventoFrecuenciaAnualDelAnio1000() {
        Calendario miCalendario = new Calendario();
        LocalDate inicioEvento = LocalDate.of(1000,5,27);
        LocalDate finEvento = LocalDate.of(1000,5,27);
        Frecuencia frecuencia = new FrecuenciaAnual();
        RepeticionEvento repeticion = new RepeticionInfinita(frecuencia);
        Evento evento = new EventoDiaCompleto("Cumpleaños", "...", inicioEvento, finEvento);
        miCalendario.agregarEvento(evento);
        miCalendario.agregarRepeticionEvento(evento, repeticion);
        miCalendario.agregarAlarma(evento, new Alarma(10));

        LocalDateTime fechaYHoraEsperada = LocalDateTime.of(LocalDateTime.now().getYear(), 5,26,23,50);

        assertEquals(fechaYHoraEsperada, miCalendario.calcularSiguienteHorarioAlarma() );
    }

    @Test
    public void alarmaEnRepeticionInfinitaEnEventoFrecuenciaMensualDelAnio1000() {
        Calendario miCalendario = new Calendario();
        LocalDate inicioEvento = LocalDate.of(1000,5,27);
        LocalDate finEvento = LocalDate.of(1000,5,27);
        Frecuencia frecuencia = new FrecuenciaMensual();
        RepeticionEvento repeticion = new RepeticionInfinita(frecuencia);
        Evento evento = new EventoDiaCompleto("Cumpleaños", "...", inicioEvento, finEvento);
        miCalendario.agregarEvento(evento);
        miCalendario.agregarRepeticionEvento(evento, repeticion);
        miCalendario.agregarAlarma(evento, new Alarma(10));

        LocalDateTime fechaYHoraEsperada = LocalDateTime.of(LocalDateTime.now().getYear(), 5,26,23,50);

        assertEquals(fechaYHoraEsperada, miCalendario.calcularSiguienteHorarioAlarma() );
    }

    @Test
    public void eliminarEvento() {
    }

    @Test
    public void agregarTarea() {
    }

    @Test
    public void obtenerEventos() {
    }

    @Test
    public void obtenerTareas() {
    }

    @Test
    public void modificarTitulo() {
    }

    @Test
    public void modificarDescripcion() {
    }

    @Test
    public void agregarRepeticionEvento() {
    }

    @Test
    public void agregarAlarma() {
    }

    @Test
    public void calcularSiguienteHorarioAlarma() {
    }

    @Test
    public void modificarFechaYHoraVencimientoTarea() {
    }

    @Test
    public void modificarFechaTareaDiaCompleto() {
    }

    @Test
    public void modificarRepeticion() {
    }

    @Test
    public void modificarFrecuencia() {
    }

    @Test
    public void modificarInicioYFinEvento() {
    }

    @Test
    public void testModificarInicioYFinEvento() {
    }
}