import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.io.Serializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import jfxtras.scene.control.LocalTimePicker;

public abstract class Vista{
    private Parent rootVista;
    public Button obtenerBoton(String id) {
        return (Button) rootVista.lookup("#" + id);
    }
    public DatePicker obtenerDatePicker(String id){
        return (DatePicker) rootVista.lookup("#" + id);
    }
    public LocalTimePicker obtenerLocalTimePicker(String id){
        return (LocalTimePicker) rootVista.lookup("#" + id);
    }
    public TextField obtenerTextField(String id){
        return (TextField) rootVista.lookup("#" + id);
    }
    public CheckBox obtenerCheckBox(String id){
        return (CheckBox) rootVista.lookup("#" + id);
    }
    public Node getRoot() {
        return rootVista;
    }
    protected void setRoot(Parent rootVista) {
        this.rootVista = rootVista;
    }
    public TextArea obtenerTextArea(String id) {
        return (TextArea) rootVista.lookup("#" + id);
    }
    public void mostrarMensajeError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        EstiloUI.aplicar(alerta.getDialogPane());
        alerta.showAndWait();
    }
    public BorderPane crearPanelConBordes(String content) {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("panel-con-bordes");

        Label label = new Label(content);
        label.setWrapText(true);
        label.setAlignment(Pos.TOP_LEFT); // Alinea el texto en la esquina superior izquierda

        borderPane.setCenter(label);

        return borderPane;
    }
}
