import com.sun.javafx.sg.prism.GrowableDataBuffer;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class ControladorMes {
    private VistaMes vistaMes;
    private Calendario calendario;
    private LocalDate fechaActual;
    List<Label> listaNumerosMes;


    public ControladorMes() {
        // Constructor sin argumentos
    }
    public ControladorMes(Calendario calendario, VistaMes vistaMes){
        this.vistaMes = vistaMes;
        this.calendario = calendario;
        this.fechaActual = LocalDate.now();
        listaNumerosMes = new ArrayList<Label>();
    }

    public void iniciar() {
        vistaMes.obtenerBoton("botonSiguienteVistaMes").setOnAction(event -> plusMes(1));
        vistaMes.obtenerBoton("botonAnteriorVistaMes").setOnAction(event -> plusMes(-1));
        mostrarNumerosDelMes(vistaMes.getVistaPorMes());
    }
    private void mostrarNumerosDelMes(GridPane vistaPorMes){
        actualizarNumerosDelMes();
    }

    private void plusMes(int cantidadMeses) {
        vistaMes.getNumerosDiasVistaMes().getChildren().clear();
        fechaActual = fechaActual.plusMonths(cantidadMeses);
        String mesAnterior = fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
        vistaMes.mostrarAnio(fechaActual.getYear());
        vistaMes.mostrarMes(mesAnterior);
        actualizarNumerosDelMes();
    }

    private void actualizarNumerosDelMes() {
        int comienzo = calcularComienzo();
        int numero = 1;
        LocalDate fechaCelda = fechaActual.withDayOfMonth(1);
        for(int i = 0; i<8; i++){
            for(int j=0; j<7; j++){
                if(comienzo > 0){
                    comienzo -= 1;
                }
                else if(numero <= fechaActual.lengthOfMonth()) {
                    if(hayActividades(fechaCelda)){
                        Button botonActividad = new Button("HAY ACTIVIDADES");
                        vistaMes.getNumerosDiasVistaMes().add(botonActividad, j, i);
                        GridPane.setValignment(botonActividad, VPos.CENTER);
                        GridPane.setHalignment(botonActividad, HPos.CENTER);

                        botonActividad.setOnAction(event -> {
                            VentanaActividad ventanaActividad = new VentanaActividad();
                            ventanaActividad.show();
                        });

                        //botonActividad.setOnAction(event -> agregarActividad());
                    }
                    Label labelNumeroMes = new Label(String.valueOf(numero));
                    //vistaMes.getNumerosDiasVistaMes().add(new Label(String.valueOf(numero)), j, i);
                    vistaMes.getNumerosDiasVistaMes().add(labelNumeroMes, j, i);
                    GridPane.setValignment(labelNumeroMes, Pos.TOP_LEFT.getVpos());
                    numero += 1;
                    fechaCelda = fechaCelda.plusDays(1);
                }

            }
        }
    }
    public void agregarActividad(){
        {
            // Crear el diálogo
            Dialog<Tarea> dialogo = new Dialog<>();
            dialogo.setTitle("Crear Evento");

            // Crear los campos de entrada
            TextField tituloTextField = new TextField();
            DatePicker fechaInicioPicker = new DatePicker();
            DatePicker fechaFinPicker = new DatePicker();
            CheckBox repetirDiariamenteCheckBox = new CheckBox("Repetir diariamente");
            TextField intervaloTextField = new TextField();

            // Configurar el contenido del diálogo
            GridPane contenido = new GridPane();
            contenido.setHgap(10);
            contenido.setVgap(10);
            contenido.addRow(0, new Label("Título:"), tituloTextField);
            contenido.addRow(1, new Label("Fecha de inicio:"), fechaInicioPicker);
            contenido.addRow(2, new Label("Fecha de fin:"), fechaFinPicker);
            contenido.addRow(3, repetirDiariamenteCheckBox);
            contenido.addRow(4, new Label("Intervalo:"), intervaloTextField);
            dialogo.getDialogPane().setContent(contenido);

            // Agregar botones de confirmación y cancelación
            ButtonType confirmarButton = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
            dialogo.getDialogPane().getButtonTypes().addAll(confirmarButton, ButtonType.CANCEL);

            dialogo.show();

            /*
            // Validar y procesar la entrada cuando se confirma el diálogo
            dialogo.setResultConverter(button -> {
                if (button == confirmarButton) {
                    // Validar los valores ingresados
                    // ...

                    // Crear la tarea o evento con los valores ingresados
                    Tarea nuevaTarea = new Tarea(tituloTextField.getText(), fechaInicioPicker.getValue(), fechaFinPicker.getValue());

                    // Agregar alarmas según las opciones seleccionadas
                    if (repetirDiariamenteCheckBox.isSelected()) {
                        int intervalo = Integer.parseInt(intervaloTextField.getText());
                        nuevaTarea.agregarAlarmaDiaria(intervalo);
                    } else {
                        nuevaTarea.agregarAlarmaNotificacion();
                    }

                    // Retornar la tarea creada
                    return nuevaTarea;
                }
                return null;
            });

            // Mostrar el diálogo y obtener el resultado
            Optional<Tarea> resultado = dialogo.showAndWait();
            resultado.ifPresent(tarea -> {
                // Aquí puedes realizar las operaciones adicionales con la tarea creada
                // ...
            });

             */
        }
    }
    private boolean hayActividades(LocalDate fechaCelda){
        for (int i = 0; i < calendario.obtenerEventos().size(); i++){
            if(calendario.obtenerEventos().get(i).siguienteRepeticionAFecha(fechaCelda.atStartOfDay().plusSeconds(-1)) != null){
                if(calendario.obtenerEventos().get(i).siguienteRepeticionAFecha(fechaCelda.atStartOfDay().plusSeconds(-1)).toLocalDate().isEqual(fechaCelda) ){
                    return true;
                }
            }
        }
        for (int i = 0; i < calendario.obtenerTareas().size(); i++){
            if(calendario.obtenerTareas().get(i).getFechaTarea().isEqual(fechaCelda)){
                return true;
            }
        }
        return false;
    }
    private int calcularComienzo() {
        int comienzo = switch (fechaActual.withDayOfMonth(1).getDayOfWeek()) {
            case SUNDAY -> 0;
            case MONDAY -> 1;
            case TUESDAY -> 2;
            case WEDNESDAY -> 3;
            case THURSDAY -> 4;
            case FRIDAY -> 5;
            case SATURDAY -> 6;
        };
        return comienzo;
    }

}
