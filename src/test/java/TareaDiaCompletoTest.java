import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TareaDiaCompletoTest {

    @Test
    public void alarmaMinutosAntesEnTareaDiaCompleto() {
        TareaDiaCompleto tarea = new TareaDiaCompleto("...","...", LocalDate.of(2025,1,1));
        Alarma alarma = new Alarma(60);
        tarea.agregarAlarmaActividad(alarma);
        LocalDateTime esperado = LocalDateTime.of(2024,12,31,23,0);

        assertEquals(esperado, tarea.proximaActivacionAlarma() );
    }

}