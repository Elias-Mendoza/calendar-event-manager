import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;

import static org.junit.Assert.*;

public class EventoConFechaYHoraTest {

    @Test
    public void tituloVacio() {
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,10,14,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,10,16,0);
        EventoConFechaYHora evento = new EventoConFechaYHora("","En el aula 102", inicioEvento, finEvento);

        assertEquals("", evento.getTitulo());
    }
    @Test
    public void descripcionVacia(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,10,14,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,10,16,0);
        EventoConFechaYHora evento = new EventoConFechaYHora("Parcial de Algoritmos","", inicioEvento, finEvento);

        assertEquals("", evento.getDescripcion());
    }

    @Test
    public void actualizacionDeAlarmaAlModificarInicioYFin(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,10,14,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,10,16,0);
        EventoConFechaYHora evento = new EventoConFechaYHora("Parcial de Algoritmos","", inicioEvento, finEvento);
        evento.agregarAlarmaActividad(new Alarma(10));
        LocalDateTime nuevoInicio = LocalDateTime.of(2100,5,10,14,0);
        LocalDateTime nuevoFin = LocalDateTime.of(2100,5,10,14,0);
        evento.modificarInicioYFin(nuevoInicio, nuevoFin);
        LocalDateTime horarioAlarmaEsperado = LocalDateTime.of(2100, 5, 10, 13, 50);

        assertEquals(horarioAlarmaEsperado, evento.proximaActivacionAlarma());
    }

    @Test
    public void cantidadDeRepeticionesCeroEnEventoHastaFecha(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,10,14,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,10,16,0);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaFecha(frecuencia, LocalDate.of(2023,5,10));
        EventoConFechaYHora evento = new EventoConFechaYHora("Evento de prueba","Descripcion", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);

        assertEquals(0, ((RepeticionHastaFecha) repeticion).getCantidadRepeticiones() );
    }

    @Test
    public void cantidadDeRepeticionesEnEventoHastaFecha(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,10,14,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,10,16,0);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaFecha(frecuencia, LocalDate.of(2023,5,15));
        EventoConFechaYHora evento = new EventoConFechaYHora("Evento de prueba","Descripcion", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);

        assertEquals(5, ((RepeticionHastaFecha) repeticion).getCantidadRepeticiones() );
    }

    @Test
    public void cantidadDeRepeticionesEnEventoHastaFechaAlModificarFrecuencia(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,1,14,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,1,16,0);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaFecha(frecuencia, LocalDate.of(2023,5,10));
        EventoConFechaYHora evento = new EventoConFechaYHora("Evento de prueba","Descripcion", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);
        Frecuencia frecuenciaNueva = new FrecuenciaSemanal(EnumSet.of(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY));
        evento.modificarFrecuenciaEvento(frecuenciaNueva);

        assertEquals(5, ((RepeticionHastaFecha) repeticion).getCantidadRepeticiones() );
    }

    @Test
    public void siguienteRepeticionEnFechaYHoraLimite(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,1,12,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,1,14,0);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaNRepeticiones(frecuencia, 1);
        EventoConFechaYHora evento = new EventoConFechaYHora("Evento","", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);
        LocalDateTime repeticionEsperada = LocalDateTime.of(2023, 5, 2,12,0);

        assertEquals(repeticionEsperada, evento.siguienteRepeticionAFecha(LocalDateTime.of(2023,5,2,11,59) ) );
    }

    @Test
    public void siguienteRepeticionEnFechaYHoraLimiteNula(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,1,12,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,1,14,0);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaNRepeticiones(frecuencia, 2);
        EventoConFechaYHora evento = new EventoConFechaYHora("Evento","", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);

        assertEquals(null, evento.siguienteRepeticionAFecha(LocalDateTime.of(2023,5,3,12,00) ) );
    }

    @Test
    public void siguienteRepeticion(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,1,12,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,1,14,0);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaNRepeticiones(frecuencia, 10);
        EventoConFechaYHora evento = new EventoConFechaYHora("Evento","", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);

        LocalDateTime resultadoEsperado = LocalDateTime.of(2023,5,4,12,0);

        assertEquals(resultadoEsperado, evento.siguienteRepeticionAFecha(LocalDateTime.of(2023,5,3,12,1) ) );
    }

}