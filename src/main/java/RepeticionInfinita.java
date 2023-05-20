import java.time.LocalDateTime;

public class RepeticionInfinita extends RepeticionEvento {
    public RepeticionInfinita(Frecuencia frecuencia) {
        super(frecuencia);
    }

    @Override
    public LocalDateTime calcularSiguienteRepeticion(LocalDateTime fecha, LocalDateTime inicio) {
        LocalDateTime repeticionADevolver = inicio;
        while(repeticionADevolver.isBefore(fecha)){
            repeticionADevolver = frecuencia.siguienteFechaYHoraDeInicioEvento(repeticionADevolver);
        }
        return repeticionADevolver;
    }

    @Override
    public void actualizarCantidadRepeticiones(LocalDateTime inicio) {

    }
}