import java.time.LocalDateTime;
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

    public void actualizarAlarmas(){
        for(Alarma alarma : this.alarmas){
            if(alarma.getTipo() == Alarma.TipoAlarma.DE_MINUTOS_ANTES){
                agregarAlarmaActividad(alarma);
                alarmas.remove(alarma);
            }
        }
    }

    //Devuelve la hora de la próxima alarma de la actividad, devuelve null en caso que no haya.
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

    public void modificarTituloActividad(String titulo){
        this.titulo = titulo;
    }
    public void modificarDescripcionActividad(String descripcion){
        this.descripcion = descripcion;
    }

    public abstract void agregarAlarmaActividad(Alarma alarma);

    public abstract void mostrarDatos();

    public String getTitulo() {
        return this.titulo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }

}