import java.io.Serializable;
import java.time.LocalDateTime;

public class RepeticionHastaNRepeticiones extends RepeticionEvento implements Serializable {

    private int cantidadRepeticiones;
    public RepeticionHastaNRepeticiones(Frecuencia frecuencia, int cantidadRepeticiones) {
        super(frecuencia);
        this.cantidadRepeticiones = cantidadRepeticiones;
    }

    @Override
    public LocalDateTime calcularSiguienteRepeticion(LocalDateTime fecha, LocalDateTime inicio) {

        LocalDateTime repeticionADevolver = inicio;
        int contador = cantidadRepeticiones;
        while( ( repeticionADevolver.isBefore(fecha) || repeticionADevolver.isEqual(fecha) ) && ( contador > 0 ) ){
            repeticionADevolver = frecuencia.siguienteFechaYHoraDeInicioEvento(repeticionADevolver);
            contador--;
        }
        if(repeticionADevolver.isBefore(fecha) || repeticionADevolver.isEqual(fecha)){
            repeticionADevolver = null;
        }
        return repeticionADevolver;
    }

    @Override
    public void actualizarCantidadRepeticiones(LocalDateTime inicio) {
    }

    public int getCantidadRepeticiones(){
        return this.cantidadRepeticiones;
    }
}