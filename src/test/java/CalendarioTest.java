import org.junit.Test;

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

        assertEquals(fechaYHoraEsperada, miCalendario.calcularSiguienteHorarioAlarma() );
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

        assertEquals(fechaYHoraEsperada, miCalendario.calcularSiguienteHorarioAlarma() );
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

        assertEquals(null, miCalendario.calcularSiguienteHorarioAlarma());

    }

}