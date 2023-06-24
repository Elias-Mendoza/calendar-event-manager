import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class ControladorSemana{

    Calendario calendario;
    VistaSemana vistaSemana;
    LocalDate fechaActual;
    public ControladorSemana() {
    }
    public ControladorSemana(Calendario calendario, VistaSemana vistaSemana) {
        this.calendario = calendario;
        this.vistaSemana = vistaSemana;
        this.fechaActual = LocalDate.now();
    }
    public ControladorSemana(VistaSemana vistaSemana) {
        this.vistaSemana = vistaSemana;
    }

    public void iniciar() {
        vistaSemana.obtenerBoton("botonSiguienteVistaSemana").setOnAction(actionEvent -> plusSemanas(1));
        vistaSemana.obtenerBoton("botonAnteriorVistaSemana").setOnAction(actionEvent -> plusSemanas(-1));
    }
    public void plusSemanas(int cantidadSemanas){
        System.out.println("hola");
        fechaActual = fechaActual.plusDays(cantidadSemanas*7);
        vistaSemana.mostrarMesActual(fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase());
        vistaSemana.mostrarAnioActual(fechaActual.getYear());
    }


}