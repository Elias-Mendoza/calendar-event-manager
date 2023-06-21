import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Month;

public class VistaSemana extends Vista{
    private static Scene escenaPrincipal;
    private Month mesActual;
    private Stage escenario;

    public VistaSemana(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaSemana.fxml"));

    }

}