import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.Month;

public class ControladorPrincipal {
    @FXML
    private Button botonVistaPorDia;
    @FXML
    private Button botonVistaPorSemana;
    @FXML
    private Button botonVistaPorMes;
    @FXML
    private Pane rootVistaPrincipal;
    @FXML
    private StackPane contenedorVistas;
    private Calendario calendario;
    private VistaPrincipal vista;

    private ControladorDia controladorDia;
    private ControladorSemana controladorSemana;
    private ControladorMes controladorMes;

    public ControladorPrincipal() {

    }
    public void setControladores(ControladorDia cv2, ControladorSemana cv3, ControladorMes cv4) {
        controladorDia = cv2;
        controladorSemana = cv3;
        controladorMes = cv4;
    }

    public void iniciar() {
        /*
        obtenerBoton("botonVistaPorDia").setOnAction(event -> mostrarVista("vistaDia.fxml"));
        obtenerBoton("botonVistaPorSemana").setOnAction(event -> mostrarVista("vistaSemana.fxml"));
        obtenerBoton("botonVistaPorMes").setOnAction(event -> mostrarVista("vistaMes.fxml"));
         */

    }
    private Button obtenerBoton(String id) {
        return (Button) rootVistaPrincipal.lookup("#" + id);
    }
    private void mostrarVista(String fxml) {
        // Lógica para mostrar la vista correspondiente en el contenedor de vistas
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent vista = fxmlLoader.load();
            controladorMes = fxmlLoader.getController();
            contenedorVistas.getChildren().setAll(vista);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ControladorPrincipal(Calendario calendario, VistaPrincipal vista) {
        this.calendario = calendario;
        this.vista = vista;
    }

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


    private void retrocederMes(){

    }
}
