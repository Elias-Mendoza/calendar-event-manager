import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.Month;

public class ControladorPrincipal {
    private Calendario calendario;
    private VistaPrincipal vistaPrincipal;
    private VistaDia vistaDia;
    private VistaSemana vistaSemana;
    private VistaMes vistaMes;
    private ControladorDia controladorDia;
    private ControladorSemana controladorSemana;
    private ControladorMes controladorMes;

    public ControladorPrincipal() {
    }
    public ControladorPrincipal(Calendario calendario, VistaPrincipal vistaPrincipal) {
        this.calendario = calendario;
        this.vistaPrincipal = vistaPrincipal;
    }
    public void setControladores(ControladorDia controladorDia, ControladorSemana controladorSemana, ControladorMes controladorMes) {
        this.controladorDia = controladorDia;
        this.controladorSemana = controladorSemana;
        this.controladorMes = controladorMes;
    }
    public void setVistas(VistaDia vistaDia, VistaSemana vistaSemana, VistaMes vistaMes){
        this.vistaDia = vistaDia;
        this.vistaSemana = vistaSemana;
        this.vistaMes = vistaMes;
    }

    public void iniciar() {
        vistaPrincipal.obtenerBoton("botonVistaPorDia").setOnAction(event -> cambiarVistaADia());
        vistaPrincipal.obtenerBoton("botonVistaPorSemana").setOnAction(event -> cambiarVistaASemana());
        vistaPrincipal.obtenerBoton("botonVistaPorMes").setOnAction(event -> cambiarVistaAMes());
        //vistaPrincipal.obtenerBoton("botonAgregarEvento").setOnAction(event -> vistaPrincipal.abrirVentanaAgregarEvento() );
        vistaPrincipal.obtenerBoton("botonAgregarEvento").setOnAction(event -> abrirVentanaAgregarEvento() );
        //vistaPrincipal.obtenerBoton("botonAgregarTarea").setOnAction(event -> vistaPrincipal.abrirVentanaAgregarTarea() );
        vistaPrincipal.obtenerBoton("botonAgregarTarea").setOnAction(event -> abrirVentanaAgregarTarea() );
    }

    private void abrirVentanaAgregarEvento(){
        VentanaAgregarEvento ventanaAgregarEvento = new VentanaAgregarEvento(calendario);
        ControladorVentanaAgregarEvento controladorVentanaAgregarEvento = new ControladorVentanaAgregarEvento(calendario, ventanaAgregarEvento);
        //ventanaAgregarEvento.mostrarVista();
        controladorVentanaAgregarEvento.iniciar();
    }
    private void abrirVentanaAgregarTarea(){
        VentanaAgregarTarea ventanaAgregarTarea = new VentanaAgregarTarea(calendario);
    }

    private void agregarTarea(){

    }

    private void cambiarVistaADia() {
        vistaPrincipal.cambiarVista(vistaDia);
        controladorDia.iniciar();
    }
    public void cambiarVistaASemana(){
        vistaPrincipal.cambiarVista(vistaSemana);
        controladorSemana.iniciar();
    }
    public void cambiarVistaAMes(){
        vistaPrincipal.cambiarVista(vistaMes);
        controladorMes.iniciar();
    }


/*
    public void mostrarVistaDia(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vistaDia.fxml"));
            Parent vista = fxmlLoader.load();
            controladorDia = fxmlLoader.getController();
            contenedorVistas.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarVistaSemana(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vistaSemana.fxml"));
            Parent vista = fxmlLoader.load();
            controladorSemana = fxmlLoader.getController();
            contenedorVistas.getChildren().clear();
            contenedorVistas.getChildren().add(vista);
            controladorSemana.ini();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Método para manejar el evento del botón que muestra la vista 2
    public void mostrarVistaMes(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vistaMes.fxml"));
            Parent vista = fxmlLoader.load();
            controladorMes = fxmlLoader.getController();
            contenedorVistas.getChildren().clear();
            contenedorVistas.getChildren().add(vista);
            controladorMes.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

}
