import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimePicker;

import java.io.Serializable;

public class VentanaAgregarTarea extends Vista{
    private Stage escenario;
    private Calendario calendario;
    private Button botonConfirmar;
    private Parent rootVistaVentanaAgregarTarea;
    private DatePicker fechaVencimientoPicker;
    private LocalTimePicker horaVencimientoPicker;
    private TextField tituloTextField;
    private TextArea descripcionTextArea;
    private CheckBox diaCompletoCheckBox;
    private CheckBox alarmaCheckBox;
    private TextField minutosAlarmaTextField;
    VentanaAgregarTarea(Calendario calendario){
        this.calendario = calendario;
        // Crear el diálogo
        Dialog<Tarea> dialogo = new Dialog<>();
        dialogo.setTitle("Crear Tarea");

        // Crear los campos de entrada
        tituloTextField = new TextField();
        fechaVencimientoPicker = new DatePicker();
        diaCompletoCheckBox = new CheckBox("Día completo");
        horaVencimientoPicker = new LocalTimePicker();
        descripcionTextArea = new TextArea();
        alarmaCheckBox = new CheckBox("Agregar Alarma");
        minutosAlarmaTextField = new TextField();


        // Configurar el contenido del diálogo
        GridPane contenido = new GridPane();
        contenido.setHgap(10);
        contenido.setVgap(10);
        contenido.addRow(0, new Label("Título:"), tituloTextField);
        contenido.addRow(1, new Label("Fecha de vencimiento:"), fechaVencimientoPicker);
        contenido.addRow(2, diaCompletoCheckBox);
        contenido.addRow(3, new Label("Hora de vencimiento:"), horaVencimientoPicker);
        contenido.addRow(4, new Label("Descripción:"), descripcionTextArea);
        contenido.addRow(5, alarmaCheckBox);
        contenido.addRow(6, new Label("Minutos antes del inicio para la alarma:"), minutosAlarmaTextField);
        dialogo.getDialogPane().setContent(contenido);

        // Agregar botones de confirmación y cancelación
        ButtonType confirmarBoton = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialogo.getDialogPane().getButtonTypes().addAll(confirmarBoton, ButtonType.CANCEL);

        // Obtener el botón correspondiente al ButtonType confirmarBoton
        botonConfirmar = (Button) dialogo.getDialogPane().lookupButton(confirmarBoton);
        botonConfirmar.setId("botonConfirmar");

        minutosAlarmaTextField.setDisable(true);

        rootVistaVentanaAgregarTarea = contenido;
        setearIds();
        setRoot(rootVistaVentanaAgregarTarea);

        dialogo.show();
    }
    private void setearIds() {
        rootVistaVentanaAgregarTarea.setId("rootVistaVentanaAgregarTarea");
        tituloTextField.setId("tituloTextField");
        fechaVencimientoPicker.setId("fechaVencimientoPicker");
        diaCompletoCheckBox.setId("diaCompletoCheckBox");


        horaVencimientoPicker.setId("horaVencimientoPicker");
        descripcionTextArea.setId("descripcionTextArea");
        alarmaCheckBox.setId("alarmaCheckBox");
        minutosAlarmaTextField.setId("minutosAlarmaTextField");
    }
    public void mostrarVista(){
        escenario.show();
    }


    public Button obtenerBotonConfirmar() {
        return botonConfirmar;
    }
}
