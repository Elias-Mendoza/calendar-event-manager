import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import javafx.scene.control.Label;


public class VistaMes extends Vista{

    private static Scene escenaPrincipal;
    private Stage escenario;
    private ControladorMes controladorMes;

    public void setControlador(ControladorMes controlador) {
        this.controladorMes = controlador;
    }

    public VistaMes(Stage primaryStage, Calendario calendario) {
        this.escenario = primaryStage;
        super.setMesActual(LocalDate.now().getMonth());
        super.setDiaActual(LocalDate.now().getDayOfMonth());
        super.setAnioActual(LocalDate.now().getYear());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vistaMes.fxml"));
            Parent root = loader.load();
            /*
            rootVistaMes = root;
            labelMesActualVistaMes = (Label) loader.getNamespace().get("labelMesActualVistaMes");
            botonSiguienteVistaMes = (Button) loader.getNamespace().get("botonSiguienteVistaMes");
            System.out.println("ESTE ES EL CONSTRUCTOR DE VISTAMES");

            labelMesActualVistaMes.setText(LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase());
          //  labelAnioActualVistaMes.setText(String.valueOf(LocalDate.now().getYear()));
            mesActualVistaMes = LocalDate.now().getMonth();
            anioActualVistaMes = LocalDate.now().getYear();

             */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void mostrarCaca(){
        obtenerBoton("botonAnteriorVistaMes").setText("CACA");
    }
    /*

    public Button obtenerBoton(String id) {
        return (Button) rootVistaMes.lookup("#" + id);
    }

    public void setMesActual(Month mes) {
        mesActualVistaMes = mes;
    }
    public void setAnioActual(int anio){
        anioActualVistaMes = anio;
    }
    public void verificarCambioDeAnioAvanzarMes(Month mesActual) {
        if(mesActual == Month.DECEMBER){

        }
    }
    public int getAnioActual(){
        return anioActualVistaMes;
    }
    public void agregarAniosEnLabel(int cantidad) {
        labelAnioActualVistaMes.setText(String.valueOf( getAnioActual() + cantidad) );
        setAnioActual(getAnioActual()+cantidad);
    }
    public void setLabelMesActualVistaMes(String mes){
        labelMesActualVistaMes.setText(mes);
    }
*/
}
