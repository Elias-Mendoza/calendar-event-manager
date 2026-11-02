import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimePicker;

import java.io.Serializable;

public class VentanaAgregarEvento extends Vista{

    private Stage escenario;
    private Calendario calendario;
    private Button botonConfirmar;
    private Parent rootVistaVentanaAgregarEvento;
    private DatePicker fechaInicioPicker;
    private DatePicker fechaFinPicker;
    private LocalTimePicker horaInicioPicker;
    private LocalTimePicker horaFinPicker;
    private TextField tituloTextField;
    private TextArea descripcionTextArea;
    private TextField intervaloTextField;
    private CheckBox diaCompletoCheckBox;
    private CheckBox repetirDiariamenteCheckBox;
    private CheckBox alarmaCheckBox;
    private TextField minutosAlarmaTextField;

    VentanaAgregarEvento(Calendario calendario){
        this.calendario = calendario;
        // Crear el diálogo
        Dialog<Tarea> dialogo = new Dialog<>();
        dialogo.setTitle("Crear Evento");

        // Crear los campos de entrada
        tituloTextField = new TextField();
        fechaInicioPicker = new DatePicker();
        fechaFinPicker = new DatePicker();
        diaCompletoCheckBox = new CheckBox("Día completo");
        repetirDiariamenteCheckBox = new CheckBox("Repetir diariamente");
        intervaloTextField = new TextField();
        horaInicioPicker = new LocalTimePicker();
        horaFinPicker = new LocalTimePicker();
        descripcionTextArea = new TextArea();
        alarmaCheckBox = new CheckBox("Activar alarma");
        minutosAlarmaTextField = new TextField();


        // Configurar el contenido del diálogo (solo disposición visual; los controles y sus ids no cambian)
        tituloTextField.setPromptText("Ej: Reunión de equipo");
        descripcionTextArea.setPromptText("Opcional");
        descripcionTextArea.setPrefRowCount(2);
        descripcionTextArea.setWrapText(true);
        fechaInicioPicker.setPromptText("Elegí una fecha");
        fechaFinPicker.setPromptText("Elegí una fecha");
        intervaloTextField.setPromptText("1");
        minutosAlarmaTextField.setPromptText("15");

        VBox contenido = EstiloUI.formulario(
                EstiloUI.seccion("Detalles"),
                EstiloUI.campo("Título", tituloTextField),
                EstiloUI.campo("Descripción", descripcionTextArea),
                EstiloUI.seccion("Cuándo"),
                EstiloUI.fila(EstiloUI.campo("Fecha de inicio", fechaInicioPicker),
                              EstiloUI.campo("Fecha de fin", fechaFinPicker)),
                diaCompletoCheckBox,
                EstiloUI.fila(EstiloUI.campo("Hora de inicio", horaInicioPicker),
                              EstiloUI.campo("Hora de fin", horaFinPicker)),
                EstiloUI.seccion("Repetición y alarma"),
                EstiloUI.opcionEnLinea(repetirDiariamenteCheckBox, intervaloTextField, "cada", "días"),
                EstiloUI.opcionEnLinea(alarmaCheckBox, minutosAlarmaTextField, null, "minutos antes")
        );
        dialogo.setHeaderText("Nuevo evento");
        dialogo.getDialogPane().setContent(contenido);
        EstiloUI.aplicar(dialogo.getDialogPane());

        // Agregar botones de confirmación y cancelación
        ButtonType confirmarBoton = new ButtonType("Crear evento", ButtonBar.ButtonData.OK_DONE);
        dialogo.getDialogPane().getButtonTypes().addAll(confirmarBoton, ButtonType.CANCEL);

        // Obtener el botón correspondiente al ButtonType confirmarBoton
        botonConfirmar = (Button) dialogo.getDialogPane().lookupButton(confirmarBoton);
        botonConfirmar.setId("botonConfirmar");

        intervaloTextField.setDisable(true);
        minutosAlarmaTextField.setDisable(true);

        rootVistaVentanaAgregarEvento = contenido;
        setearIds();
        setRoot(rootVistaVentanaAgregarEvento);

        dialogo.show();
    }

    private void setearIds() {
        rootVistaVentanaAgregarEvento.setId("rootVistaVentanaAgregarEvento");
        tituloTextField.setId("tituloTextField");
        fechaInicioPicker.setId("fechaInicioPicker");
        fechaFinPicker.setId("fechaFinPicker");
        diaCompletoCheckBox.setId("diaCompletoCheckBox");
        repetirDiariamenteCheckBox.setId("repetirDiariamenteCheckBox");
        intervaloTextField.setId("intervaloTextField");
        horaInicioPicker.setId("horaInicioPicker");
        horaFinPicker.setId("horaFinPicker");
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
