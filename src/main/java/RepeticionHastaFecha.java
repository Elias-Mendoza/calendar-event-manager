import java.time.LocalDate;
import java.time.LocalDateTime;

public class RepeticionHastaFecha extends RepeticionEvento {
    public RepeticionHastaFecha(Frecuencia frecuencia, LocalDate fechaLimite) {
        super(frecuencia);
        super.fechaLimite = fechaLimite;
        super.cantidadRepeticiones = calcularCantidadRepeticiones(this.fechaLimite);
        this.tipoRepeticion = TipoRepeticion.HASTA_X_FECHA;
        this.esRepeticionInfinita = false;
    }

    public int calcularCantidadRepeticiones(LocalDate fechaLimite){
        int contador = 1;
        LocalDateTime fechaYHora = LocalDateTime.now();
        while(fechaYHora.isBefore(fechaLimite.atStartOfDay()) ){
            fechaYHora = this.frecuencia.siguienteFechaYHoraDeInicioEvento(fechaYHora);
            if(fechaYHora.isBefore(fechaLimite.atStartOfDay())){
                contador++;
            }
        }
        return contador;
    }

}
