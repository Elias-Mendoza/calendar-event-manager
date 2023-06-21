import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControladorDia{
    @FXML
    private Parent rootVistaDia;
    @FXML
    private Button botonSiguienteVistaDia;
    @FXML
    private Button botonAnteriorVistaDia;
    @FXML
    private Label labelMesActualVistaDia;
    @FXML
    private Label labelAnioActualVistaDia;

    Calendario calendario;
    VistaDia vistaDia;
    public ControladorDia() {
    }
    public ControladorDia(Calendario calendario, VistaDia vistaDia) {
        this.vistaDia = vistaDia;
        this.calendario = calendario;
    }

    public ControladorDia(VistaDia vistaDia) {
        this.vistaDia = vistaDia;
    }
    public void iniciar() {

    }

    private void avanzarDia() {

    }
    private void retrocederDia() {

    }

}