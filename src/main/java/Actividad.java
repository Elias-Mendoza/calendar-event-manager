import java.util.ArrayList;
import java.util.List;

public abstract class Actividad {
    private String titulo;
    private String descripcion;
    protected List<Alarma> alarmas;
    public Actividad(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.alarmas = new ArrayList<>();
    }

    public void modificarTituloActividad(String titulo){
        this.titulo = titulo;
    }
    public void modificarDescripcionActividad(String descripcion){
        this.descripcion = descripcion;
    }

    public abstract void agregarAlarmaActividad(Alarma alarma);

    public abstract void mostrarDatos();

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getTitulo() {
        return this.titulo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }

}