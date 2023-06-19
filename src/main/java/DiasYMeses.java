import java.time.LocalDateTime;
import java.time.Month;

public class DiasYMeses {
    public int getNumeroDiaActual(){
        return LocalDateTime.now().getDayOfMonth();
    }
    public String getNombreMesActual(){
        return LocalDateTime.now().getMonth().toString();
    }
    public int getAnioActual(){
        return LocalDateTime.now().getYear();
    }
}
