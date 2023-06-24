import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Stack;

public class VistaPrincipal {

    /*public VistaPrincipal(Stage escenario, Calendario calendario) {
        try {

            escenaPrincipal = new Scene(loadFXML("vistaConstante"), 1280, 720);
            Scene escena2 = new Scene(loadFXML("vistaMes"));

            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("vistaConstante.fxml"));
            Pane contenedor = loader.load();
            rootVistaPrincipal = contenedor;


            labelMesActual = (Label) loader.getNamespace().get("mesActual");
            Month mesInicial = LocalDateTime.now().getMonth();
            String nombreMesInicial = mesInicial.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
            labelMesActual.setText(nombreMesInicial);

            anioActual = (Label) loader.getNamespace().get("anioActual");
            anioActual.setText(Integer.toString(LocalDateTime.now().getYear()));


            vistaCalendario = new StackPane();
            vistaCalendario.getChildren().addAll(escenaPrincipal.getRoot(), escena2.getRoot());

            // Configurar la escena
            escenaPrincipal = new Scene(contenedor);


            // Configurar la escena en el escenario y mostrarlo
            escenario.setScene(escenaPrincipal);
            escenario.setResizable(false);
            escenario.setTitle("Calendario");

            StackPane.setAlignment(escena2.getRoot(), Pos.BOTTOM_RIGHT);
            escenario.setScene(new Scene(vistaCalendario, 1280, 720));


            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    private static Scene escenaPrincipal;
    private Stage escenario;
    @FXML
    private Parent rootVistaPrincipal;
    @FXML
    private Button botonVistaPorDia;
    @FXML
    private Button botonVistaPorSemana;
    @FXML
    private Button botonVistaPorMes;
    @FXML
    private StackPane contenedorVistas;
    private VistaMes vistaMes;

    public VistaPrincipal(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaConstante.fxml"));
            Parent root = loader.load();
            rootVistaPrincipal = root;

            // Obtener referencias a los elementos de la vista
            botonVistaPorDia = (Button) loader.getNamespace().get("botonVistaPorDia");
            botonVistaPorSemana = (Button) loader.getNamespace().get("botonVistaPorSemana");
            botonVistaPorMes = (Button) loader.getNamespace().get("botonVistaPorMes");
            contenedorVistas = (StackPane) loader.getNamespace().get("contenedorVistas");

            Scene scene = new Scene(root);
            escenario.setScene(scene);
            escenario.setTitle("Calendario");
            escenario.setResizable(false);
        //    escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarVista(){
        escenario.show();
    }
    /*
    public void cambiarVista(String fxml) {
        // Lógica para mostrar la vista correspondiente en el contenedor de vistas
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Parent vista = fxmlLoader.load();
//            controladorMes = fxmlLoader.getController();
            contenedorVistas.getChildren().clear();
            contenedorVistas.getChildren().add(vista);
//            contenedorVistas.getChildren().setAll(vista);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */
    public void cambiarVista(Vista vista) {
        // Lógica para mostrar la vista correspondiente en el contenedor de vistas
            contenedorVistas.getChildren().clear();
            contenedorVistas.getChildren().add(vista.getRoot());
    }
    public void setVistas(VistaMes vistaMes){
        this.vistaMes = vistaMes;
    }

    public Button obtenerBoton(String id) {
        return (Button) rootVistaPrincipal.lookup("#" + id);
    }

    public void mostrarVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent vista = loader.load();

            contenedorVistas.getChildren().clear();
            contenedorVistas.getChildren().add(vista);
            StackPane.setAlignment(vista, Pos.BOTTOM_RIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void setRoot(String fxml) throws IOException {
        escenaPrincipal.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
