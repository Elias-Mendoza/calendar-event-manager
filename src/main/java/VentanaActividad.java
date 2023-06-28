import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class VentanaActividad extends Stage{
    public VentanaActividad(List<Evento> eventos, List<Tarea> tareas) {

        VBox root = new VBox();
        root.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        root.setPadding(new Insets(10));
        for(Evento evento : eventos){
            String datosEvento = "Evento:\n"+"Titulo: "+evento.getTitulo()+"\nDescripcion: "+evento.getDescripcion()
                    +"\nInicio: "+evento.getInicio()+"\nFin: "+evento.getFin();
            root.getChildren().add(crearPanelConBordes(datosEvento));
        }
        /*
        for(int i=0; i< eventos.size(); i++){
            root.getChildren().add(crearPanelConBordes("Evento:\n" + "Titulo: "+eventos.get(i).getTitulo() +"\n"+"Descripcion: "+eventos.get(i).getDescripcion()
                    + "\n" +"Inicio: " + eventos.get(i).getInicio() + "\n" + "Fin: " + eventos.get(i).getFin()));
        }
         */
        for(Tarea tarea : tareas){
            String datosTarea = "Tarea: \nTitulo: " +tarea.getTitulo() +"\nDescripcion: "+ tarea.getDescripcion() +"\nCompletada: " +
                        tarea.getEstaCompletada() + "\nFecha de vencimiento: " + tarea.getFechaYHoraVencimiento();
            root.getChildren().add((crearPanelConBordes(datosTarea)));
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
