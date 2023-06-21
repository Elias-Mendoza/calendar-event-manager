import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class ControladorSemana{

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
    Calendario calendario;
    VistaSemana vistaSemana;
    public ControladorSemana() {
    }
    public ControladorSemana(Calendario calendario, VistaSemana vistaSemana) {
        this.calendario = calendario;
        this.vistaSemana = vistaSemana;
    }
    public ControladorSemana(VistaSemana vistaSemana) {
        this.vistaSemana = vistaSemana;
    }

    public void ini() {
        vistaSemana.obtenerBoton("botonSiguienteVistaSemana").setOnAction(actionEvent -> avanzarSemana());
        vistaSemana.obtenerBoton("botonAnteriorVistaSemana").setOnAction(actionEvent -> retrocederSemana());
    }
    private void avanzarSemana() {
    }
    private void retrocederSemana() {

    }


}