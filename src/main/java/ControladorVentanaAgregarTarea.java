import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ControladorVentanaAgregarTarea {
    private VentanaAgregarTarea ventanaAgregarTarea;
    private Calendario calendario;
    public ControladorVentanaAgregarTarea(Calendario calendario, VentanaAgregarTarea ventanaAgregarTarea){
        this.ventanaAgregarTarea = ventanaAgregarTarea;
        this.calendario = calendario;
    }

    public void iniciar() {
        // Se usa un filtro (y no setOnAction) porque el Dialog cierra la ventana en su propio handler,
        // que corre antes que onAction. Consumiendo el evento cuando los datos son inválidos,
        // el diálogo queda abierto y no se pierde lo que el usuario cargó.
        ventanaAgregarTarea.obtenerBotonConfirmar().addEventFilter(ActionEvent.ACTION, event -> {
            if (!agregarTarea()) {
                event.consume();
            }
        });
        ventanaAgregarTarea.obtenerCheckBox("diaCompletoCheckBox").setOnAction(evento ->deshabilitarCamposDeHora());
        ventanaAgregarTarea.obtenerCheckBox("alarmaCheckBox").setOnAction(evento -> deshabilitarCampoDeAlarma());
    }
    private void deshabilitarCampoDeAlarma() {
        ventanaAgregarTarea.obtenerTextField("minutosAlarmaTextField").setDisable(!ventanaAgregarTarea.obtenerCheckBox("alarmaCheckBox").isSelected());
    }
    private void deshabilitarCamposDeHora(){
        ventanaAgregarTarea.obtenerLocalTimePicker("horaVencimientoPicker").setDisable(ventanaAgregarTarea.obtenerCheckBox("diaCompletoCheckBox").isSelected());
    }
    /** Devuelve true si la tarea se creó; false si los datos no son válidos. */
    public boolean agregarTarea(){

        LocalDate fechaVencimiento = ventanaAgregarTarea.obtenerDatePicker("fechaVencimientoPicker").getValue();
        LocalTime horaVencimiento = getCustomLocalTimeSinSegundos(ventanaAgregarTarea.obtenerLocalTimePicker("horaVencimientoPicker").getLocalTime());
        TextField titulo = ventanaAgregarTarea.obtenerTextField("tituloTextField");
        TextArea descripcion = ventanaAgregarTarea.obtenerTextArea("descripcionTextArea");
        TextField intervalo = ventanaAgregarTarea.obtenerTextField("intervaloTextField");
        TextField minutosAntes = ventanaAgregarTarea.obtenerTextField("minutosAlarmaTextField");
        if(!comprobarCampos(intervalo, minutosAntes)){
            return false;
        }
        else{
            Tarea tarea;
            //Si es de dia completo
            if(ventanaAgregarTarea.obtenerCheckBox("diaCompletoCheckBox").isSelected()){
                tarea = new TareaDiaCompleto(titulo.getText(), descripcion.getText(),fechaVencimiento);
            }
            else{
                tarea = new TareaConFechaYHoraVencimiento(titulo.getText(), descripcion.getText(),
                        LocalDateTime.of(fechaVencimiento,horaVencimiento) );
            }
            if(ventanaAgregarTarea.obtenerCheckBox("alarmaCheckBox").isSelected()){
                int minutos = Integer.parseInt(ventanaAgregarTarea.obtenerTextField("minutosAlarmaTextField").getText());
                Alarma alarma = new Alarma(minutos);
                tarea.agregarAlarmaActividad(alarma);
            }
            calendario.agregarTarea(tarea);
            try (OutputStream os = new FileOutputStream("calendario.dat")) {
                calendario.guardarCalendario(os);
            } catch (Calendario.CalendarioNoGuardadoException e) {
                System.out.println("Error al guardar el calendario: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }
    public boolean comprobarCampos(TextField intervalo, TextField minutosAntes){
        boolean datosValidos = true;
        if (ventanaAgregarTarea.obtenerTextField("tituloTextField").getText().isEmpty()){
            ventanaAgregarTarea.mostrarMensajeError("Por favor, ingrese un título");
            datosValidos = false;
            return datosValidos;
        }
        else if (ventanaAgregarTarea.obtenerDatePicker("fechaVencimientoPicker").getValue() == null) {
            ventanaAgregarTarea.mostrarMensajeError("Por favor, elija la fecha de vencimiento");
            return false;
        }
        else if(ventanaAgregarTarea.obtenerCheckBox("alarmaCheckBox").isSelected() && minutosAntes.getText().isEmpty()){
            ventanaAgregarTarea.mostrarMensajeError("Por favor, ingrese los datos de la alarma");
            datosValidos = false;
            return datosValidos;
        }
        if (ventanaAgregarTarea.obtenerCheckBox("alarmaCheckBox").isSelected() && !esEnteroMayorOIgualA(minutosAntes.getText(), 0)) {
            ventanaAgregarTarea.mostrarMensajeError("Los minutos de la alarma deben ser un número entero (0 o más)");
            return false;
        }
        return datosValidos;
    }
    private boolean esEnteroMayorOIgualA(String texto, int minimo) {
        try {
            return Integer.parseInt(texto) >= minimo;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private LocalTime getCustomLocalTimeSinSegundos(LocalTime time) {
        return LocalTime.of(time.getHour(), time.getMinute());
    }
}
