import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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
        alarmaCheckBox = new CheckBox("Activar alarma");
        minutosAlarmaTextField = new TextField();


        // Configurar el contenido del diálogo (solo disposición visual; los controles y sus ids no cambian)
        tituloTextField.setPromptText("Ej: Entregar el TP");
        descripcionTextArea.setPromptText("Opcional");
        descripcionTextArea.setPrefRowCount(2);
        descripcionTextArea.setWrapText(true);
        fechaVencimientoPicker.setPromptText("Elegí una fecha");
        minutosAlarmaTextField.setPromptText("15");

        VBox contenido = EstiloUI.formulario(
                EstiloUI.seccion("Detalles"),
                EstiloUI.campo("Título", tituloTextField),
                EstiloUI.campo("Descripción", descripcionTextArea),
                EstiloUI.seccion("Vencimiento"),
                EstiloUI.fila(EstiloUI.campo("Fecha", fechaVencimientoPicker),
                              EstiloUI.campo("Hora", horaVencimientoPicker)),
                diaCompletoCheckBox,
                EstiloUI.seccion("Alarma"),
                EstiloUI.opcionEnLinea(alarmaCheckBox, minutosAlarmaTextField, null, "minutos antes")
        );
        dialogo.setHeaderText("Nueva tarea");
        dialogo.getDialogPane().setContent(contenido);
        EstiloUI.aplicar(dialogo.getDialogPane());

        // Agregar botones de confirmación y cancelación
        ButtonType confirmarBoton = new ButtonType("Crear tarea", ButtonBar.ButtonData.OK_DONE);
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
