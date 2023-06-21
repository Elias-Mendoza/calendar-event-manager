import javafx.scene.Parent;

import java.awt.*;
import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.Month;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class Vista {

    private LocalDate fechaActual;
    private int anioActual;
    private Month mesActual;
    private int diaActual;
    private Parent rootVista;

    public int getAnioActual(){
        return anioActual;
    }
    public void setAnioActual(int anio){
        anioActual = anio;
    }
    public Month getMesActual(){
        return mesActual;
    }
    public void setMesActual(Month mes){
        mesActual = mes;
    }
    public int getDiaActual(){
        return diaActual;
    }
    public void setDiaActual(int dia){
        diaActual = dia;
    }
    public Button obtenerBoton(String id) {
        return (Button) rootVista.lookup("#" + id);
    }
    public Label obtenerLabel(String id) {
        return (Label) rootVista.lookup("#" + id);
    }
}
