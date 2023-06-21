import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class VistaDia extends Vista{
    private static Scene escenaPrincipal;
    private Month mesActual;
    private Stage escenario;
    private int diaActual;

    public VistaDia(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaDia.fxml"));

    }
    public int getDiaActual(){
        return diaActual;
    }
    public void setDiaActual(int fechaDia){
        diaActual = fechaDia;
    }
}
