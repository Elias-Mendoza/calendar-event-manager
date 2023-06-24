import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    @FXML
    private Parent rootVistaDia;
    @FXML
    private Button botonSiguienteVistaDia;
    @FXML
    private Button botonAnteriorVistaDia;
    @FXML
    private Label labelDiaYMesActualVistaDia;
    @FXML
    private Label labelAnioActualVistaDia;
    @FXML
    private VBox vistaPorDia;
    private Stage escenario;

    public VistaDia(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaDia.fxml"));
            Parent root = loader.load();
            rootVistaDia = root;

            super.setRoot(rootVistaDia);

            labelAnioActualVistaDia = (Label) loader.getNamespace().get("labelAnioActualVistaDia");
            labelDiaYMesActualVistaDia = (Label) loader.getNamespace().get("labelDiaYMesActualVistaDia");
            botonSiguienteVistaDia = (Button) loader.getNamespace().get("botonAnteriorVistaDia");
            botonSiguienteVistaDia = (Button) loader.getNamespace().get("botonSiguienteVistaDia");
            vistaPorDia = (VBox) loader.getNamespace().get("vistaPorDia");

            System.out.println("ESTE ES EL CONSTRUCTOR DE VISTAMES");



            labelDiaYMesActualVistaDia.setText(LocalDate.now().getDayOfMonth() + " de " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase());
            labelAnioActualVistaDia.setText(String.valueOf(LocalDate.now().getYear()));
            //  labelAnioActualVistaMes.setText(String.valueOf(LocalDate.now().getYear()));
            //mesActualVistaMes = LocalDate.now().getMonth();
            //anioActualVistaMes = LocalDate.now().getYear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarDiaYMes(int numeroFecha, String nombreMes){
        labelDiaYMesActualVistaDia.setText(String.valueOf(numeroFecha) + " de " + nombreMes);
    }

    public Node getRoot() {
        return rootVistaDia;
    }

    public void mostrarAnio(int year) {
        labelAnioActualVistaDia.setText(String.valueOf(year));
    }
}
