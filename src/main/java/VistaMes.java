import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;
import javafx.scene.control.Label;


public class VistaMes extends Vista{
    private Stage escenario;
    @FXML
    private Parent rootVistaMes;
    @FXML
    private Label labelAnioActualVistaMes;
    @FXML
    private Label labelMesActualVistaMes;
    @FXML
    private Button botonAnteriorVistaMes;
    @FXML
    private Button botonSiguienteVistaMes;
    @FXML
    private GridPane vistaPorMes;
    @FXML
    private GridPane numerosDiasVistaMes;

    public VistaMes(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaMes.fxml"));
            Parent root = loader.load();
            rootVistaMes = root;

            super.setRoot(rootVistaMes);

            labelAnioActualVistaMes = (Label) loader.getNamespace().get("labelAnioActualVistaMes");
            labelMesActualVistaMes = (Label) loader.getNamespace().get("labelMesActualVistaMes");
            botonSiguienteVistaMes = (Button) loader.getNamespace().get("botonAnteriorVistaMes");
            botonSiguienteVistaMes = (Button) loader.getNamespace().get("botonSiguienteVistaMes");
            vistaPorMes = (GridPane) loader.getNamespace().get("vistaPorMes");
            numerosDiasVistaMes = (GridPane) loader.getNamespace().get("numerosDiasVistaMes");

            System.out.println("ESTE ES EL CONSTRUCTOR DE VISTAMES");

            labelMesActualVistaMes.setText(LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase());
            labelAnioActualVistaMes.setText(String.valueOf(LocalDate.now().getYear()));
            mostrarNumerosMeses();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void mostrarNumerosMeses(){

    }
    public void mostrarMes(String nombreMes){
        labelMesActualVistaMes.setText(nombreMes);
    }

    public Node getRoot() {
        return rootVistaMes;
    }

    public void mostrarAnio(int year) {
        labelAnioActualVistaMes.setText(String.valueOf(year));
    }

    public GridPane getVistaPorMes() {
        return vistaPorMes;
    }

    public GridPane getNumerosDiasVistaMes() {
        return numerosDiasVistaMes;
    }
    /*

    public Button obtenerBoton(String id) {
        return (Button) rootVistaMes.lookup("#" + id);
    }

    public void setMesActual(Month mes) {
        mesActualVistaMes = mes;
    }
    public void setAnioActual(int anio){
        anioActualVistaMes = anio;
    }
    public void verificarCambioDeAnioAvanzarMes(Month mesActual) {
        if(mesActual == Month.DECEMBER){

        }
    }
    public int getAnioActual(){
        return anioActualVistaMes;
    }
    public void agregarAniosEnLabel(int cantidad) {
        labelAnioActualVistaMes.setText(String.valueOf( getAnioActual() + cantidad) );
        setAnioActual(getAnioActual()+cantidad);
    }
    public void setLabelMesActualVistaMes(String mes){
        labelMesActualVistaMes.setText(mes);
    }
*/
}
