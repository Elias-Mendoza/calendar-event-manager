import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalTimePicker;

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

/*
    public VentanaAgregarEvento(Calendario calendario) {
        super(calendario, "Crear Evento");

        // Crear el nuevo TextField
        TextField textFieldExtra = new TextField();

        // Obtener el contenido del diálogo
        GridPane contenido = (GridPane) getRoot();
        Dialog<Actividad> dialogo = getDialogo();
        //GridPane contenido = (GridPane) getDialogPane();
        dialogo.getDialogPane().setContent(contenido);

        // Agregar el nuevo TextField al contenido del diálogo
        contenido.re
        contenido.addRow(8, new Label("Campo adicional:"), textFieldExtra);

        //setRoot(super.rootVistaVentanaAgregarElemento);
        // Personalizar la ventana de agregar evento si es necesario
        dialogo.show();
    }*/

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
        alarmaCheckBox = new CheckBox("Agregar Alarma");
        minutosAlarmaTextField = new TextField();


        // Configurar el contenido del diálogo
        GridPane contenido = new GridPane();
        contenido.setHgap(10);
        contenido.setVgap(10);
        contenido.addRow(0, new Label("Título:"), tituloTextField);
        contenido.addRow(1, new Label("Fecha de inicio:"), fechaInicioPicker);
        contenido.addRow(2, new Label("Fecha de fin:"), fechaFinPicker);
        contenido.addRow(3, diaCompletoCheckBox);
        contenido.addRow(4, new Label("Hora de inicio:"), horaInicioPicker);
        contenido.addRow(5, new Label("Hora de fin:"), horaFinPicker);
        contenido.addRow(6, new Label("Descripción:"), descripcionTextArea);
        contenido.addRow(7, repetirDiariamenteCheckBox);
        contenido.addRow(8, new Label("Intervalo:"), intervaloTextField);
        contenido.addRow(9, alarmaCheckBox);
        contenido.addRow(10, new Label("Minutos antes del inicio para la alarma:"), minutosAlarmaTextField);
        dialogo.getDialogPane().setContent(contenido);

        // Agregar botones de confirmación y cancelación
        ButtonType confirmarBoton = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialogo.getDialogPane().getButtonTypes().addAll(confirmarBoton, ButtonType.CANCEL);

        // Obtener el botón correspondiente al ButtonType confirmarBoton
        botonConfirmar = (Button) dialogo.getDialogPane().lookupButton(confirmarBoton);
        botonConfirmar.setId("botonConfirmar");

        intervaloTextField.setDisable(true);
        minutosAlarmaTextField.setDisable(true);

        /*
        // Configurar la habilitación/deshabilitación de los campos de horas de inicio y fin según la selección de "Día completo"
        diaCompletoCheckBox.setOnAction(e -> {
            horaInicioPicker.setDisable(diaCompletoCheckBox.isSelected());
            horaFinPicker.setDisable(diaCompletoCheckBox.isSelected());
        });
        // Configurar la habilitación/deshabilitación del campo de intervalo según la selección de "Repetir diariamente"
        repetirDiariamenteCheckBox.setOnAction(e -> {
            intervaloTextField.setDisable(!repetirDiariamenteCheckBox.isSelected());
        });
        // Configurar la habilitación/deshabilitación del campo de "minutos antes" según la selección de "Activar alarma"
        alarmaCheckBox.setOnAction(event -> {
            minutosAlarmaTextField.setDisable(!alarmaCheckBox.isSelected());
        });
        */


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
}
