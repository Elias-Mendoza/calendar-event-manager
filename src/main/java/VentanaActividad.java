import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class VentanaActividad extends Stage {
    public VentanaActividad(List<Evento> eventos, List<Tarea> tareas) {

        VBox root = new VBox();
        root.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        root.setPadding(new Insets(10));
        for(int i=0; i< eventos.size(); i++){
            root.getChildren().add(crearPanelConBordes("Evento:\n" + "Titulo: "+eventos.get(i).getTitulo() +"\n"+"Descripcion: "+eventos.get(i).getDescripcion()
                    + "\n" +"Inicio: " + eventos.get(i).getInicio() + "\n" + "Fin: " + eventos.get(i).getFin()));
        }
        for(int i=0; i< tareas.size(); i++){
            root.getChildren().add(crearPanelConBordes("Tarea: \n" +"Titulo: " +tareas.get(i).getTitulo() +"\n"+"Descripcion: "+ tareas.get(i).getDescripcion() + "\n" +
                    "Fecha de vencimiento: " + tareas.get(i).getFechaTarea() + "\n" + "Completada: " + tareas.get(i).getEstaCompletada() ));

        }
        Scene scene = new Scene(root, 300, 400);
        setScene(scene);
    }
    private BorderPane crearPanelConBordes(String content) {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        Label label = new Label(content);
        borderPane.setCenter(label);

        return borderPane;
    }
}
