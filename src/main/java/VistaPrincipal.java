import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;


public class VistaPrincipal implements Observer, Serializable{

    private static Scene escenaPrincipal;
    private transient Stage escenario;
    private Calendario calendario;
    @FXML
    private transient Parent rootVistaPrincipal;
    @FXML
    private transient Button botonVistaPorDia;
    @FXML
    private transient Button botonVistaPorSemana;
    @FXML
    private transient Button botonVistaPorMes;
    @FXML
    private transient StackPane contenedorVistas;
    @FXML
    private transient Button botonAgregarEvento;
    @FXML
    private transient Button botonAgregarTarea;
    private VistaMes vistaMes;

    public VistaPrincipal(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;
        this.calendario = calendario;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaConstante.fxml"));
            Parent root = loader.load();
            rootVistaPrincipal = root;

            // Obtener referencias a los elementos de la vista
            botonVistaPorDia = (Button) loader.getNamespace().get("botonVistaPorDia");
            botonVistaPorSemana = (Button) loader.getNamespace().get("botonVistaPorSemana");
            botonVistaPorMes = (Button) loader.getNamespace().get("botonVistaPorMes");
            contenedorVistas = (StackPane) loader.getNamespace().get("contenedorVistas");
            botonAgregarEvento = (Button) loader.getNamespace().get(("botonAgregarEvento"));
            botonAgregarTarea = (Button) loader.getNamespace().get(("botonAgregarTarea"));

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

    static void setRoot(String fxml) throws IOException {
        escenaPrincipal.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @Override
    public void update(String mensaje) {
        Platform.runLater(() -> mostrarNotificacion(mensaje));
    }

    public void mostrarNotificacion(String mensaje) {
        Label alarmaActivadaLabel = new Label("¡Alarma activada!");
        Label mensajeLabel = new Label(mensaje);
        VBox vbox = new VBox(alarmaActivadaLabel);
        vbox.getChildren().add(mensajeLabel);
        vbox.setAlignment(Pos.CENTER);

        Stage notificacionStage = new Stage();
        notificacionStage.setTitle("Notificación");
        notificacionStage.setScene(new Scene(vbox, 300, 300));
        notificacionStage.show();
    }
}
