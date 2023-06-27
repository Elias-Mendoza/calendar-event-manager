import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class VistaSemana extends Vista{
    @FXML
    private Parent rootVistaSemana;
    @FXML
    private Button botonSiguienteVistaSemana;
    @FXML
    private Button botonAnteriorVistaSemana;
    @FXML
    private Label labelMesActualVistaSemana;
    @FXML
    private Label labelAnioActualVistaSemana;
    @FXML
    private GridPane vistaPorSemana;
    @FXML
    private GridPane numerosDiasVistaSemana;
    private Stage escenario;

    public VistaSemana(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaSemana.fxml"));
            Parent root = loader.load();
            rootVistaSemana = root;

            super.setRoot(rootVistaSemana);

            labelAnioActualVistaSemana = (Label) loader.getNamespace().get("labelAnioActualVistaSemana");
            labelMesActualVistaSemana = (Label) loader.getNamespace().get("labelMesActualVistaSemana");
            botonSiguienteVistaSemana = (Button) loader.getNamespace().get("botonAnteriorVistaSemana");
            botonSiguienteVistaSemana = (Button) loader.getNamespace().get("botonSiguienteVistaSemana");
            vistaPorSemana = (GridPane) loader.getNamespace().get("vistaPorSemana");
            numerosDiasVistaSemana = (GridPane) loader.getNamespace().get("numerosDiasVistaSemana");

            System.out.println("ESTE ES EL CONSTRUCTOR DE VISTA SEMANA");

            labelMesActualVistaSemana.setText(LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase());
            labelAnioActualVistaSemana.setText(String.valueOf(LocalDate.now().getYear()));
            //  labelAnioActualVistaMes.setText(String.valueOf(LocalDate.now().getYear()));
            //mesActualVistaMes = LocalDate.now().getMonth();
            //anioActualVistaMes = LocalDate.now().getYear();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mostrarMesActual(String mes) {
        labelMesActualVistaSemana.setText(mes);
        System.out.println("PRUEBA");
    }
    public void mostrarAnioActual(int anio){
        labelAnioActualVistaSemana.setText(String.valueOf(anio));
    }

    public GridPane getNumerosDiasVistaSemana() {
        return numerosDiasVistaSemana;
    }

    public GridPane getVistaPorSemana() {
        return vistaPorSemana;
    }

    public void mostrarAnio(int year) {
        labelAnioActualVistaSemana.setText(String.valueOf(year));
    }

    public void mostrarMes(String mes) {
        labelMesActualVistaSemana.setText(mes);
    }
}