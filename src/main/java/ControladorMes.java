import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ControladorMes {
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


    public void iniciar() {
        obtenerBoton("botonSiguienteVistaMes").setOnAction(event -> avanzarMes());
        obtenerBoton("botonAnteriorVistaMes").setOnAction(event -> retrocederMes());
    }

    private void retrocederMes() {
        System.out.println("Se retrocedió un mes ");
    }

    private void avanzarMes() {
        System.out.println("Se avanzó un mes.");
    }

    private Button obtenerBoton(String id) {
        return (Button) rootVistaMes.lookup("#" + id);
    }
    public void funcion(){
        System.out.println("FUNCION DE PRUEBA EJECUTÁNDOSE");
        vistaPorMes.add(new Label("PRUEBA"), 0, 2);

    }


}
