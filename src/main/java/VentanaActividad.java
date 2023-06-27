import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class VentanaActividad extends Stage {
    public VentanaActividad() {
        // Crear el contenido de la ventana secundaria
        StackPane root = new StackPane();
        root.getChildren().add(new Label("¡Ventana de actividades!"));

        // Crear la escena y establecerla en la ventana
        Scene scene = new Scene(root, 200, 200);
        setScene(scene);
    }
}
