
public class RepeticionHastaNRepeticiones extends RepeticionEvento {
    public RepeticionHastaNRepeticiones(Frecuencia frecuencia, int cantidadRepeticiones) {
        super(frecuencia);
        super.cantidadRepeticiones = cantidadRepeticiones;
        this.tipoRepeticion = TipoRepeticion.HASTA_N_REPETICIONES;
        this.esRepeticionInfinita = false;
    }
}