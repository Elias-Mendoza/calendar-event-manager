import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class VistaDia extends Vista{
    @FXML
    private Parent rootVistaDia;
    @FXML
    private Button botonSiguienteVistaDia;
    @FXML
    private Button botonAnteriorVistaDia;
    @FXML
    private Label labelDiaYMesActualVistaDia;
    @FXML
    private Label labelAnioActualVistaDia;
    @FXML
    private VBox vistaPorDia;
    @FXML
    private GridPane numerosDiasVistaDia;
    private Stage escenario;
    private Calendario calendario;

    public VistaDia(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;
        this.calendario = calendario;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaDia.fxml"));
            Parent root = loader.load();
            rootVistaDia = root;

            super.setRoot(rootVistaDia);

            labelAnioActualVistaDia = (Label) loader.getNamespace().get("labelAnioActualVistaDia");
            labelDiaYMesActualVistaDia = (Label) loader.getNamespace().get("labelDiaYMesActualVistaDia");
            botonSiguienteVistaDia = (Button) loader.getNamespace().get("botonAnteriorVistaDia");
            botonSiguienteVistaDia = (Button) loader.getNamespace().get("botonSiguienteVistaDia");
            vistaPorDia = (VBox) loader.getNamespace().get("vistaPorDia");
            numerosDiasVistaDia = (GridPane) loader.getNamespace().get("numerosDiasVistaDia");

            labelDiaYMesActualVistaDia.setText(LocalDate.now().getDayOfMonth() + " de " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.of("es", "ES")).toUpperCase());
            labelAnioActualVistaDia.setText(String.valueOf(LocalDate.now().getYear()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarDiaYMes(int numeroFecha, String nombreMes){
        labelDiaYMesActualVistaDia.setText(String.valueOf(numeroFecha) + " de " + nombreMes);
    }
    public Node getRoot() {
        return rootVistaDia;
    }

    public void mostrarAnio(int year) {
        labelAnioActualVistaDia.setText(String.valueOf(year));
    }

    public GridPane getNumerosDiasVistaDia() {
        return numerosDiasVistaDia;
    }

    public void mostrarActividadesEnVistaDia(List<Evento> eventos, List<Tarea> tareas) {

        vistaPorDia.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        vistaPorDia.setPadding(new Insets(10));
        for(Evento evento : eventos){
            vistaPorDia.getChildren().add(crearPanelConBordes("Evento:\n" + "Titulo: "+evento.getTitulo() +"\n"+"Descripcion: "+evento.getDescripcion()
                    + "\n" +"Inicio: " + evento.getInicio() + "\n" + "Fin: " + evento.getFin()));
        }
        for(Tarea tarea: tareas){
            vistaPorDia.getChildren().add(crearPanelConBordes("Tarea: \n" +"Titulo: " +tarea.getTitulo() +"\n"+"Descripcion: "+ tarea.getDescripcion() + "\n" +
                    "Fecha de vencimiento: " + tarea.getFechaTarea() + "\n" + "Completada: " + tarea.getEstaCompletada() ));
        }
    }
    public VBox getVistaPorDia() {
        return vistaPorDia;
    }

}
