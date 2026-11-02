import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


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
            EstiloUI.aplicar(scene);
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
            marcarBotonActivo(vista);
    }

    // Resalta en la barra lateral el botón de la vista que se está mostrando
    private void marcarBotonActivo(Vista vista) {
        Button[] botones = {botonVistaPorDia, botonVistaPorSemana, botonVistaPorMes};
        for (Button boton : botones) {
            if (boton != null) {
                boton.getStyleClass().remove("activo");
            }
        }
        Button activo = null;
        if (vista instanceof VistaDia) {
            activo = botonVistaPorDia;
        } else if (vista instanceof VistaSemana) {
            activo = botonVistaPorSemana;
        } else if (vista instanceof VistaMes) {
            activo = botonVistaPorMes;
        }
        if (activo != null) {
            activo.getStyleClass().add("activo");
        }
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

    // Cantidad de avisos abiertos, para apilarlos sin que se tapen entre sí
    private static int notificacionesAbiertas = 0;
    private static final double ANCHO_NOTIFICACION = 340;
    private static final double ALTO_NOTIFICACION = 150;
    private static final double MARGEN_NOTIFICACION = 16;

    public void mostrarNotificacion(String mensaje) {
        Label encabezado = new Label("ALARMA  ·  " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        encabezado.getStyleClass().add("encabezado-notificacion");

        Label alarmaActivadaLabel = new Label(mensaje == null || mensaje.isBlank() ? "¡Alarma activada!" : mensaje);
        alarmaActivadaLabel.getStyleClass().add("titulo-notificacion");
        alarmaActivadaLabel.setWrapText(true);

        Label mensajeLabel = new Label("Es momento de tu actividad.");
        mensajeLabel.getStyleClass().add("mensaje-notificacion");
        mensajeLabel.setWrapText(true);

        Stage notificacionStage = new Stage(StageStyle.UNDECORATED);
        notificacionStage.setTitle("Notificación");
        notificacionStage.setAlwaysOnTop(true);

        Button cerrar = new Button("×");
        cerrar.getStyleClass().add("cerrar-notificacion");
        cerrar.setOnAction(e -> notificacionStage.close());

        Button entendido = new Button("Entendido");
        entendido.getStyleClass().add("boton-notificacion");
        entendido.setDefaultButton(true);
        entendido.setOnAction(e -> notificacionStage.close());

        Region separador = new Region();
        HBox.setHgrow(separador, Priority.ALWAYS);
        HBox filaSuperior = new HBox(encabezado, separador, cerrar);
        filaSuperior.setAlignment(Pos.CENTER_LEFT);

        HBox filaInferior = new HBox(entendido);
        filaInferior.setAlignment(Pos.CENTER_RIGHT);

        Region espacio = new Region();
        VBox.setVgrow(espacio, Priority.ALWAYS);
        VBox vbox = new VBox(filaSuperior, alarmaActivadaLabel, mensajeLabel, espacio, filaInferior);
        vbox.getStyleClass().add("notificacion");

        Scene escenaNotificacion = new Scene(vbox, ANCHO_NOTIFICACION, ALTO_NOTIFICACION);
        EstiloUI.aplicar(escenaNotificacion);
        notificacionStage.setScene(escenaNotificacion);

        // Esquina inferior derecha de la pantalla, apilando si hay varias
        Rectangle2D pantalla = Screen.getPrimary().getVisualBounds();
        notificacionStage.setX(pantalla.getMaxX() - ANCHO_NOTIFICACION - MARGEN_NOTIFICACION);
        notificacionStage.setY(pantalla.getMaxY() - (ALTO_NOTIFICACION + MARGEN_NOTIFICACION) * (notificacionesAbiertas + 1));
        notificacionesAbiertas++;
        notificacionStage.setOnHidden(e -> notificacionesAbiertas = Math.max(0, notificacionesAbiertas - 1));

        notificacionStage.show();
    }
}
