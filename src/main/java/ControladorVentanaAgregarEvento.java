import javafx.scene.control.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class ControladorVentanaAgregarEvento {
    private VentanaAgregarEvento ventanaAgregarEvento;
    private Calendario calendario;
    public ControladorVentanaAgregarEvento(Calendario calendario, VentanaAgregarEvento ventanaAgregarEvento){
        this.ventanaAgregarEvento = ventanaAgregarEvento;
        this.calendario = calendario;
    }

    public void iniciar() {
        //ventanaAgregarEvento.obtenerBoton("botonConfirmar").setOnAction(event -> comprobarDatos());
        ventanaAgregarEvento.obtenerBotonConfirmar().setOnAction(event -> agregarEvento());
        ventanaAgregarEvento.obtenerCheckBox("diaCompletoCheckBox").setOnAction(evento ->deshabilitarCamposDeHora());
        ventanaAgregarEvento.obtenerCheckBox("repetirDiariamenteCheckBox").setOnAction(evento -> deshabilitarCampoDeRepeticion());
        ventanaAgregarEvento.obtenerCheckBox("alarmaCheckBox").setOnAction(evento -> deshabilitarCampoDeAlarma());
    }
    private void deshabilitarCampoDeAlarma() {
        ventanaAgregarEvento.obtenerTextField("minutosAlarmaTextField").setDisable(!ventanaAgregarEvento.obtenerCheckBox("alarmaCheckBox").isSelected());
    }

    private void deshabilitarCampoDeRepeticion() {
        ventanaAgregarEvento.obtenerTextField("intervaloTextField").setDisable(!ventanaAgregarEvento.obtenerCheckBox("repetirDiariamenteCheckBox").isSelected());
    }
    private void deshabilitarCamposDeHora(){
        ventanaAgregarEvento.obtenerLocalTimePicker("horaInicioPicker").setDisable(ventanaAgregarEvento.obtenerCheckBox("diaCompletoCheckBox").isSelected());
        ventanaAgregarEvento.obtenerLocalTimePicker("horaFinPicker").setDisable(ventanaAgregarEvento.obtenerCheckBox("diaCompletoCheckBox").isSelected());
    }
    public void agregarEvento(){

        LocalDate fechaInicio = ventanaAgregarEvento.obtenerDatePicker("fechaInicioPicker").getValue();
        LocalDate fechaFin = ventanaAgregarEvento.obtenerDatePicker("fechaFinPicker").getValue();
        LocalTime horaInicio = ventanaAgregarEvento.obtenerLocalTimePicker("horaInicioPicker").getLocalTime();
        LocalTime horaFin = ventanaAgregarEvento.obtenerLocalTimePicker("horaFinPicker").getLocalTime();
        TextField titulo = ventanaAgregarEvento.obtenerTextField("tituloTextField");
        TextArea descripcion = ventanaAgregarEvento.obtenerTextArea("descripcionTextArea");
        TextField intervalo = ventanaAgregarEvento.obtenerTextField("intervaloTextField");
        TextField minutosAntes = ventanaAgregarEvento.obtenerTextField("minutosAlarmaTextField");
        if( !comprobarFechas(fechaInicio, fechaFin, horaInicio, horaFin) || !comprobarCampos(intervalo, minutosAntes)){
            System.out.println("Los datos estan mal");
            return;
        }
        else{
            Evento evento;
            //Si es de dia completo
            if(ventanaAgregarEvento.obtenerCheckBox("diaCompletoCheckBox").isSelected()){
                evento = new EventoDiaCompleto(titulo.getText(), descripcion.getText(),fechaInicio, fechaFin);
            }
            else{
                evento = new EventoConFechaYHora(titulo.getText(), descripcion.getText(),
                        LocalDateTime.of(fechaInicio,horaInicio), LocalDateTime.of(fechaFin, horaFin));
            }
            if(ventanaAgregarEvento.obtenerCheckBox("repetirDiariamenteCheckBox").isSelected()){
                FrecuenciaDiaria frecuencia =  new FrecuenciaDiaria(Integer.parseInt( intervalo.getText()) );
                RepeticionInfinita repeticion = new RepeticionInfinita(frecuencia);
                evento.agregarRepeticion(repeticion);
            }
            if(ventanaAgregarEvento.obtenerCheckBox("alarmaCheckBox").isSelected()){
                int minutos = Integer.parseInt(ventanaAgregarEvento.obtenerTextField("minutosAlarmaTextField").getText());
                Alarma alarma = new Alarma(minutos);
                evento.agregarAlarmaActividad(alarma);
            }
            calendario.agregarEvento(evento);
            try (OutputStream os = new FileOutputStream("calendario.dat")) {
                calendario.guardarCalendario(os);
            } catch (Calendario.CalendarioNoGuardadoException e) {
                System.out.println("Error al guardar el calendario: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean comprobarFechas(LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin) {
        boolean datosValidos = true;
        if (fechaInicio == null || fechaFin == null || horaInicio == null || horaFin == null) {
            ventanaAgregarEvento.mostrarMensajeError("Por favor, ingrese bien los datos");
            datosValidos = false;
            return datosValidos;
        }
        LocalDateTime fechaYHoraInicio = LocalDateTime.of(fechaInicio, horaInicio);
        LocalDateTime fechaYHoraFin = LocalDateTime.of(fechaFin, horaFin);
        if (fechaYHoraInicio.isAfter(fechaYHoraFin)) {
            ventanaAgregarEvento.mostrarMensajeError("La fecha de inicio debe ser anterior a la fecha de fin.");
            datosValidos = false;
            return datosValidos;
        }
        return datosValidos;
    }
    public boolean comprobarCampos(TextField intervalo, TextField minutosAntes){
        boolean datosValidos = true;
        if (ventanaAgregarEvento.obtenerTextField("tituloTextField").getText().isEmpty()){
            ventanaAgregarEvento.mostrarMensajeError("Por favor, ingrese un título");
            datosValidos = false;
            return datosValidos;
        }
        System.out.println("El titulo del evento es: " + ventanaAgregarEvento.obtenerTextField("tituloTextField").getText());
        if (ventanaAgregarEvento.obtenerCheckBox("repetirDiariamenteCheckBox").isSelected() && intervalo.getText().isEmpty()) {
            ventanaAgregarEvento.mostrarMensajeError("Por favor, ingrese el intervalo de repeticion");
            datosValidos = false;
            return datosValidos;
        }
        else if(ventanaAgregarEvento.obtenerCheckBox("alarmaCheckBox").isSelected() && minutosAntes.getText().isEmpty()){
            ventanaAgregarEvento.mostrarMensajeError("Por favor, ingrese los datos de la alarma");
            datosValidos = false;
            return datosValidos;
        }
        return datosValidos;
    }
}
