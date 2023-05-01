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
        LocalDateTime nuevoInicio = LocalDateTime.of(2024,5,10,14,0);
        LocalDateTime nuevoFin = LocalDateTime.of(2024,5,10,14,0);
        evento.modificarInicioYFin(nuevoInicio, nuevoFin);
        LocalDateTime horarioAlarmaEsperado = LocalDateTime.of(2024, 5, 10, 13, 50);

        assertEquals(horarioAlarmaEsperado, evento.proximaActivacionAlarma());
    }

    @Test
    public void cantidadDeRepeticionesEnEventoHastaFecha(){
        LocalDateTime inicioEvento = LocalDateTime.of(2023,5,10,14,0);
        LocalDateTime finEvento = LocalDateTime.of(2023,5,10,16,0);
        Frecuencia frecuencia = new FrecuenciaDiaria(1);
        RepeticionEvento repeticion = new RepeticionHastaFecha(frecuencia, LocalDate.of(2023,5,10));
        EventoConFechaYHora evento = new EventoConFechaYHora("Evento de prueba","Descripcion", inicioEvento, finEvento);
        evento.agregarRepeticion(repeticion);
        assertEquals(1, evento.repeticion.cantidadRepeticiones);
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

        assertEquals(6, evento.repeticion.cantidadRepeticiones);
    }

    @Test
    public void getFin() {
    }

    @Test
    public void mostrarDatos() {
    }
}