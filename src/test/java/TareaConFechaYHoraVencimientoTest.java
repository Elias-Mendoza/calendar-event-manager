import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TareaConFechaYHoraVencimientoTest {

    @Test
    public void alarmaCorrectaAlCambiarFechaVencimiento() {
        LocalDateTime vencimientoTarea = LocalDateTime.of(2024,8,15,0,0);
        Alarma alarma = new Alarma(10);
        TareaConFechaYHoraVencimiento tarea = new TareaConFechaYHoraVencimiento("Titulo","Descripcion", vencimientoTarea);
        tarea.agregarAlarmaActividad(alarma);
        tarea.modificarFechaYHoraVencimiento(LocalDateTime.of(2023,7,2,0,0));

        assertEquals(LocalDateTime.of(2023,7,1,23,50), tarea.proximaActivacionAlarma() );
    }

    @Test
    public void horarioDeActivacionDeAlarmaDeTareaConFechaDeAniosAnteriores() {
        LocalDateTime vencimientoTarea = LocalDateTime.of(2020,8,15,0,0);
        Alarma alarma = new Alarma(10);
        TareaConFechaYHoraVencimiento tarea = new TareaConFechaYHoraVencimiento("Titulo","Descripcion", vencimientoTarea);
        tarea.agregarAlarmaActividad(alarma);

        assertEquals(LocalDateTime.of(2020,8,14,23,50), tarea.proximaActivacionAlarma() );
    }

}