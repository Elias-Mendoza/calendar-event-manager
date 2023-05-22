import java.time.LocalDate;
import java.time.LocalDateTime;

public class RepeticionHastaFecha extends RepeticionEvento {
    private int cantidadRepeticiones;
    private LocalDate fechaLimite;
    public RepeticionHastaFecha(Frecuencia frecuencia, LocalDate fechaLimite) {
        super(frecuencia);
        this.fechaLimite = fechaLimite;
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
        int contadorRepeticiones = 0;
        LocalDateTime fechaYHora = inicio;
        LocalDateTime momentoLimite = this.fechaLimite.plusDays(1).atStartOfDay();
        while(fechaYHora.isBefore(momentoLimite)){
            fechaYHora = frecuencia.siguienteFechaYHoraDeInicioEvento(fechaYHora);
            if(fechaYHora.isBefore(momentoLimite)){
                contadorRepeticiones++;
            }
        }
        this.cantidadRepeticiones = contadorRepeticiones;
    }

    public int getCantidadRepeticiones(){
        return this.cantidadRepeticiones;
    }
}
