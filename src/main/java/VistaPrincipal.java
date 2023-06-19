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

    @FXML
    private Parent rootVistaPrincipal; // El nodo raíz del archivo FXML

    private TextField campoEvento;

    @FXML
    private Label anioActual;
    @FXML
    private Label labelMesActual;
    @FXML
    private BorderPane panelSuperior;
    @FXML
    private VBox vistaPorDia;
    @FXML
    private GridPane vistaPorSemana;
    @FXML
    private GridPane vistaPorMes;
    @FXML
    private Button botonVistaPorDia;
    @FXML
    private Button botonVistaPorSemana;
    @FXML
    private Button botonVistaPorMes;
    private static Scene escenaPrincipal;
    private Month mesActual;
    private static StackPane vistaCalendario;


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
    private Stage escenario;
    private StackPane contenedorVistas;

    public VistaPrincipal(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaConstante.fxml"));
            Parent root = loader.load();
            rootVistaPrincipal = root;

            // Obtener referencias a los elementos de la vista
            labelMesActual = (Label) loader.getNamespace().get("mesActual");
            contenedorVistas = (StackPane) loader.getNamespace().get("contenedorVistas");
            Button botonVistaPorDia = (Button) loader.getNamespace().get("botonVistaPorDia");
            Button botonVistaPorSemana = (Button) loader.getNamespace().get("botonVistaPorSemana");
            Button botonVistaPorMes = (Button) loader.getNamespace().get("botonVistaPorMes");


            Scene scene = new Scene(root);
            escenario.setScene(scene);
            escenario.setTitle("Vista Principal");
            escenario.setResizable(false);
            escenario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Button obtenerBoton(String id) {
        return (Button) rootVistaPrincipal.lookup("#" + id);
    }
    public Label obtenerLabel(String id) {
        return (Label) rootVistaPrincipal.lookup("#" + id);
    }

    // Método para mostrar un nodo en el StackPane
    public static void mostrarNodo(Node nodo) {
        //stackPane.getChildren().clear(); // Limpiar el StackPane
        removerUltimoNodo(vistaCalendario);
        vistaCalendario.getChildren().add(nodo); // Agregar el nodo al StackPane
    }
    public static void removerUltimoNodo(StackPane stackPane) {
        ObservableList<Node> nodos = stackPane.getChildren();
        if (!nodos.isEmpty()) {
            nodos.remove(nodos.size() - 1);
        }
    }
    public void mostrarEvento(Label texto, int fila, int columna) {
        vistaPorMes.add(texto, fila, columna);
    }

    public Button getBotonVistaDia() {
        return botonVistaPorDia;
    }
    public Button getBotonVistaSemana(){
        return botonVistaPorSemana;
    }
    public Button getBotonVistaMes() {
        return botonVistaPorMes;
    }
    public VBox getVistaPorDia(){
        return vistaPorDia;
    }
    public GridPane getVistaPorSemana(){
        return vistaPorSemana;
    }
    public GridPane getVistaPorMes(){
        return vistaPorMes;
    }
    public Month getMesActual(){
        return mesActual;
    }
    public void setNombreMes(Label mes) {
        obtenerLabel("mesActual").setText(mes.getText());
    }

    public void setLabelMesActual(Month mesSiguiente) {
        String nombreMes = mesSiguiente.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
        Label labelMesActual = obtenerLabel("mesActual");
        labelMesActual.setText(nombreMes);
    }

    public void setMesActual(Month mesSiguiente) {
        mesActual = mesSiguiente;
    }
    public Scene getEscena(){
        return escenaPrincipal;
    }
}
