import java.util.List;

public abstract class Actividad {
    private String titulo;
    private String descripcion;
    private List<Alarma> alarmas;
    public Actividad(String titulo, String descripcion){
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public void modificarTituloActividad(String titulo){
        this.titulo = titulo;
    }
    public void modificarDescripcion(Actividad actividad, String descripcion){
        this.descripcion = descripcion;
    }

    public void agregarAlarmaActividad(Alarma alarma){
        this.alarmas.add(alarma);
    }

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