import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Tarea extends Actividad implements Serializable {
    private boolean estaCompletada;
    public Tarea(String titulo, String descripcion){
        super(titulo, descripcion);
        this.estaCompletada = false;
    }
    public abstract LocalDate getFechaTarea();
    public abstract LocalDateTime getFechaYHoraVencimiento();
    public boolean getEstaCompletada(){
        return this.estaCompletada;
    }
    public abstract String tiempoRestanteLegible();
    public void completarTarea(){
        this.estaCompletada = true;
    }
}