import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Tarea extends Actividad{
    private boolean estaCompletada;
    public Tarea(String titulo, String descripcion){
        super(titulo, descripcion);
        this.estaCompletada = false;
    }

    public abstract String tiempoRestanteLegible();

/*    public void agregarAlarma(Alarma alarma) {
        this.alarmas.add(alarma);
    }*/

    public void completarTarea(){
        this.estaCompletada = true;
    }
}