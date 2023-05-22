import java.time.LocalDateTime;

public abstract class Tarea extends Actividad{
    private boolean estaCompletada;
    public Tarea(String titulo, String descripcion){
        super(titulo, descripcion);
        this.estaCompletada = false;
    }

    public boolean getEstaCompletada(){
        return this.estaCompletada;
    }
    public abstract String tiempoRestanteLegible();
    public void completarTarea(){
        this.estaCompletada = true;
    }
}