import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EventoDiaCompletoTest {

    @Test
    public void actualizacionDeAlarmaAlModificarInicioYFinEventoDiaCompleto(){
        LocalDate inicioEvento = LocalDate.of(2023,5,10);
        LocalDate finEvento = LocalDate.of(2023,5,10);
        EventoDiaCompleto evento = new EventoDiaCompleto("Navidad","", inicioEvento, finEvento);
        evento.agregarAlarmaActividad(new Alarma(60));
        LocalDate nuevoInicio = LocalDate.of(2100,12,25);
        LocalDate nuevoFin = LocalDate.of(2100,12,25);
        evento.modificarInicioYFin(nuevoInicio, nuevoFin);
        LocalDateTime momentoAlarmaEsperada = LocalDateTime.of(2100, 12, 24,23,0);

        assertEquals(momentoAlarmaEsperada, evento.proximaActivacionAlarma());
    }

    @Test
    public void siguienteRepeticionNula(){
        LocalDate inicioEvento = LocalDate.of(2023,5,10);
        LocalDate finEvento = LocalDate.of(2023,5,10);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaNRepeticiones(frecuencia, 5);
        EventoDiaCompleto evento = new EventoDiaCompleto("Evento","", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);

        assertEquals(null, evento.siguienteRepeticionAFecha(LocalDateTime.of(2023,5,15,0,0) ) );
    }

    @Test
    public void siguienteRepeticionEnFechaLimite(){
        LocalDate inicioEvento = LocalDate.of(2023,5,1);
        LocalDate finEvento = LocalDate.of(2023,5,1);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaNRepeticiones(frecuencia, 1);
        EventoDiaCompleto evento = new EventoDiaCompleto("Evento","", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);
        LocalDateTime repeticionEsperada = LocalDateTime.of(2023, 5, 2,0,0);

        assertEquals(repeticionEsperada, evento.siguienteRepeticionAFecha(LocalDateTime.of(2023,5,1,0,0) ) );
    }
    @Test
    public void siguienteRepeticionEnFechaPosteriorATodasLasRepeticiones(){
        LocalDate inicioEvento = LocalDate.of(2023,5,1);
        LocalDate finEvento = LocalDate.of(2023,5,1);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaNRepeticiones(frecuencia, 1);
        EventoDiaCompleto evento = new EventoDiaCompleto("Evento","", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);

        assertEquals(null, evento.siguienteRepeticionAFecha(LocalDateTime.of(2025,5,10,0,0) ) );
    }

    @Test
    public void cantidadDeRepeticionesEnEventoHastaNRepeticionesAlCambiarFrecuencia(){
        LocalDate inicioEvento = LocalDate.of(2023,5,1);
        LocalDate finEvento = LocalDate.of(2023,5,1);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaNRepeticiones(frecuencia, 10);
        EventoDiaCompleto evento = new EventoDiaCompleto("Evento","", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);
        Frecuencia frecuenciaNueva = new FrecuenciaDiaria(10);
        evento.modificarFrecuenciaEvento(frecuenciaNueva);

        assertEquals(10, ((RepeticionHastaNRepeticiones) repeticion).getCantidadRepeticiones() );
    }


}