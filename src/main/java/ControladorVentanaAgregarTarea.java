import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        ventanaAgregarTarea.obtenerBotonConfirmar().setOnAction(event -> agregarTarea());
        ventanaAgregarTarea.obtenerCheckBox("diaCompletoCheckBox").setOnAction(evento ->deshabilitarCamposDeHora());
        ventanaAgregarTarea.obtenerCheckBox("alarmaCheckBox").setOnAction(evento -> deshabilitarCampoDeAlarma());
    }
    private void deshabilitarCampoDeAlarma() {
        ventanaAgregarTarea.obtenerTextField("minutosAlarmaTextField").setDisable(!ventanaAgregarTarea.obtenerCheckBox("alarmaCheckBox").isSelected());
    }
    private void deshabilitarCamposDeHora(){
        ventanaAgregarTarea.obtenerLocalTimePicker("horaVencimientoPicker").setDisable(ventanaAgregarTarea.obtenerCheckBox("diaCompletoCheckBox").isSelected());
    }
    public void agregarTarea(){

        LocalDate fechaVencimiento = ventanaAgregarTarea.obtenerDatePicker("fechaVencimientoPicker").getValue();
        LocalTime horaVencimiento = ventanaAgregarTarea.obtenerLocalTimePicker("horaVencimientoPicker").getLocalTime();
        TextField titulo = ventanaAgregarTarea.obtenerTextField("tituloTextField");
        TextArea descripcion = ventanaAgregarTarea.obtenerTextArea("descripcionTextArea");
        TextField intervalo = ventanaAgregarTarea.obtenerTextField("intervaloTextField");
        TextField minutosAntes = ventanaAgregarTarea.obtenerTextField("minutosAlarmaTextField");
        if(!comprobarCampos(intervalo, minutosAntes)){
            return;
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
        }
    }
    public boolean comprobarCampos(TextField intervalo, TextField minutosAntes){
        boolean datosValidos = true;
        if (ventanaAgregarTarea.obtenerTextField("tituloTextField").getText().isEmpty()){
            ventanaAgregarTarea.mostrarMensajeError("Por favor, ingrese un título");
            datosValidos = false;
            return datosValidos;
        }
        else if(ventanaAgregarTarea.obtenerCheckBox("alarmaCheckBox").isSelected() && minutosAntes.getText().isEmpty()){
            ventanaAgregarTarea.mostrarMensajeError("Por favor, ingrese los datos de la alarma");
            datosValidos = false;
            return datosValidos;
        }
        return datosValidos;
    }
}
