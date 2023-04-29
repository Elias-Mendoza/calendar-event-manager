import java.time.LocalDateTime;

public abstract class Evento extends Actividad{
    protected LocalDateTime inicio;
    protected LocalDateTime fin;
    protected RepeticionEvento repeticion;
    //    protected List<LocalDateTime> repeticiones;
    public Evento(String titulo, String descripcion, LocalDateTime inicio, LocalDateTime fin) {
        super(titulo, descripcion);
        this.inicio = inicio;
        this.fin = fin;
//        this.repeticiones = new ArrayList<>();
    }
    /*
        public void crearRepeticiones(){
            int cantidadReps = 0;
            LocalDateTime fechaYHora = this.getInicio();
            this.repeticiones.add(this.getInicio());
            while(cantidadReps < 5){
                this.repeticiones.add(this.repeticion.frecuencia.siguienteFechaYHora(fechaYHora));
                System.out.println(this.repeticiones.get(cantidadReps));
                fechaYHora = this.repeticion.frecuencia.siguienteFechaYHora(fechaYHora);
                cantidadReps++;
            }
        }
        public void mostrarRepeticiones(){
            for(int i=0; i<this.repeticiones.size(); i++){
                System.out.println(this.repeticiones.get(i));
            }
        }
        */
    //Se devuelve la fecha y hora de la siguiente repetición después de la fecha pasada por parámetro. devuelve NULL en caso que no haya repetición.
    public LocalDateTime siguienteRepeticionAFecha(LocalDateTime fechaYHora){
        int contador = this.repeticion.cantidadRepeticiones;
        LocalDateTime repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(inicio);
        if(this.repeticion.esRepeticionInfinita){
            while(repeticionADevolver.isBefore(fechaYHora)){
                repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(repeticionADevolver);
            }
            repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(repeticionADevolver);
        }
        else{
            while(repeticionADevolver.isBefore(fechaYHora) && contador > 0){
                repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(repeticionADevolver);
                contador--;
            }
            repeticionADevolver = this.repeticion.frecuencia.siguienteFechaYHora(repeticionADevolver);
            if(contador==0){
                repeticionADevolver = null;
            }
        }
        return (repeticionADevolver);
    }
    public LocalDateTime getInicio(){
        return (this.inicio);
    }
    public LocalDateTime getFin(){
        return (this.fin);
    }
    public RepeticionEvento getRepeticion() {
        return this.repeticion;
    }
    public abstract String tiempoRestanteLegible();

}
