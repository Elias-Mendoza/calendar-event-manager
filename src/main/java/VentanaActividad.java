import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class VentanaActividad extends Stage{
    public VentanaActividad(List<Evento> eventos, List<Tarea> tareas) {

        VBox root = new VBox();
        root.getStyleClass().add("ventana-actividades");

        Label titulo = new Label("Actividades del día");
        titulo.getStyleClass().add("titulo-ventana");
        root.getChildren().add(titulo);

        for(Evento evento : eventos){
            root.getChildren().add(EstiloUI.tarjetaEvento(evento));
        }
        for(Tarea tarea : tareas){
            root.getChildren().add(EstiloUI.tarjetaTarea(tarea));
        }

        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.getStyleClass().add("scroll-actividades");
        // Que el fondo cubra toda la ventana aunque haya pocas actividades
        root.minHeightProperty().bind(scroll.heightProperty());

        Scene scene = new Scene(scroll, 360, 420);
        EstiloUI.aplicar(scene);
        setTitle("Actividades");
        setScene(scene);
    }
}
