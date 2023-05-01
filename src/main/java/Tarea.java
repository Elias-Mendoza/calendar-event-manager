import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Tarea extends Actividad{
    private boolean estaCompletada;
    public Tarea(String titulo, String descripcion){
        super(titulo, descripcion);
        this.estaCompletada = false;
    }
    public LocalDateTime proximaActivacionAlarma(){
        LocalDateTime proximoHorarioAlarma = LocalDateTime.MAX;
        for(Alarma alarma : alarmas){
            if(alarma.getMomentoActivacion().isBefore(proximoHorarioAlarma)){
                proximoHorarioAlarma = alarma.getMomentoActivacion();
            }
        }
        if(proximoHorarioAlarma == LocalDateTime.MAX){
            proximoHorarioAlarma = null;
        }
        return proximoHorarioAlarma;
    }

    public boolean getEstaCompletada(){
        return this.estaCompletada;
    }
    public abstract String tiempoRestanteLegible();
    public void completarTarea(){
        this.estaCompletada = true;
    }
}