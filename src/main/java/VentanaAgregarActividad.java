import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimePicker;

public abstract class VentanaAgregarActividad extends Vista {
    protected Stage escenario;
    protected Calendario calendario;
    protected Button botonConfirmar;
    protected Parent rootVistaVentanaAgregarElemento;
    protected DatePicker fechaPicker;
    protected LocalTimePicker horaPicker;
    protected TextField tituloTextField;
    protected TextArea descripcionTextArea;
    protected TextField intervaloTextField;
    protected CheckBox diaCompletoCheckBox;
    protected CheckBox repetirDiariamenteCheckBox;
    protected CheckBox alarmaCheckBox;
    protected TextField minutosAlarmaTextField;
    private Dialog<Actividad> dialogo;

    public VentanaAgregarActividad(Calendario calendario, String titulo) {
        this.calendario = calendario;
        // Crear el diálogo
        Dialog<Actividad> dialogo = new Dialog<>();
        dialogo.setTitle(titulo);

        // Crear los campos de entrada
        tituloTextField = new TextField();
        fechaPicker = new DatePicker();
        diaCompletoCheckBox = new CheckBox("Día completo");
        repetirDiariamenteCheckBox = new CheckBox("Repetir diariamente");
        intervaloTextField = new TextField();
        horaPicker = new LocalTimePicker();
        descripcionTextArea = new TextArea();
        alarmaCheckBox = new CheckBox("Agregar Alarma");
        minutosAlarmaTextField = new TextField();

        // Configurar el contenido del diálogo
        GridPane contenido = new GridPane();
        contenido.setHgap(10);
        contenido.setVgap(10);
        contenido.addRow(0, new Label("Título:"), tituloTextField);
        contenido.addRow(1, new Label("Fecha:"), fechaPicker);
        contenido.addRow(2, diaCompletoCheckBox);
        contenido.addRow(3, new Label("Hora:"), horaPicker);
        contenido.addRow(4, new Label("Descripción:"), descripcionTextArea);
        contenido.addRow(5, repetirDiariamenteCheckBox);
        contenido.addRow(6, new Label("Intervalo:"), intervaloTextField);
        contenido.addRow(7, alarmaCheckBox);
        contenido.addRow(8, new Label("Minutos antes del inicio para la alarma:"), minutosAlarmaTextField);
        dialogo.getDialogPane().setContent(contenido);

        // Agregar botones de confirmación y cancelación
        ButtonType confirmarBoton = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialogo.getDialogPane().getButtonTypes().addAll(confirmarBoton, ButtonType.CANCEL);

        // Obtener el botón correspondiente al ButtonType confirmarBoton
        botonConfirmar = (Button) dialogo.getDialogPane().lookupButton(confirmarBoton);
        botonConfirmar.setId("botonConfirmar");

        intervaloTextField.setDisable(true);
        minutosAlarmaTextField.setDisable(true);

        this.dialogo = dialogo;

        rootVistaVentanaAgregarElemento = contenido;
        setearIds();
        setRoot(rootVistaVentanaAgregarElemento);

        //dialogo.show();
    }

    private void setearIds() {
        rootVistaVentanaAgregarElemento.setId("rootVistaVentanaAgregarElemento");
        tituloTextField.setId("tituloTextField");
        fechaPicker.setId("fechaPicker");
        diaCompletoCheckBox.setId("diaCompletoCheckBox");
        repetirDiariamenteCheckBox.setId("repetirDiariamenteCheckBox");
        intervaloTextField.setId("intervaloTextField");
        horaPicker.setId("horaPicker");
        descripcionTextArea.setId("descripcionTextArea");
        alarmaCheckBox.setId("alarmaCheckBox");
        minutosAlarmaTextField.setId("minutosAlarmaTextField");
    }

    public void mostrarVista() {
        escenario.show();
    }

    public void mostrarMensajeError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public Button obtenerBotonConfirmar() {
        return botonConfirmar;
    }
    public Dialog<Actividad> getDialogo(){
        return dialogo;
    }
}
