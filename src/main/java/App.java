import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class App extends Application {
    /*
   @Override
   public void start(Stage primaryStage) throws Exception {

       Calendario calendario = new Calendario();
       //CARGAMOS EL MODELO
       InputStream is = new FileInputStream("calendario.dat");
       calendario = Calendario.cargarCalendario(is);

       //CARGAMOS LA VISTA
       VistaPrincipal vista = new VistaPrincipal(primaryStage, calendario);

       //CARGAMOS EL CONTROLADOR
       ControladorPrincipal controlador = new ControladorPrincipal(calendario, vista);

       controlador.iniciar();
   }*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar FXML de la vista fija
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vistaConstante.fxml"));
        Parent vistaFija = fxmlLoader.load();

        // Obtener el controlador de la vista fija
        ControladorPrincipal controladorPrincipal = fxmlLoader.getController();

        // Crear una escena con la vista fija y mostrarla en la ventana principal
        Scene scene = new Scene(vistaFija);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Cargar FXML de las otras vistas
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("vistaDia.fxml"));
        Parent vistaDia = fxmlLoader2.load();
        FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("vistaSemana.fxml"));
        Parent vistaSemana = fxmlLoader3.load();
        FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("vistaMes.fxml"));
        Parent vistaMes = fxmlLoader4.load();

        // Crear instancias de los otros controladores
        ControladorDia controladorDia = new ControladorDia();
        ControladorSemana controladorSemana = new ControladorSemana();
        ControladorMes controladorMes = new ControladorMes();

        // Pasar las referencias a los otros controladores al controlador de la vista fija
        controladorPrincipal.setControladores(controladorDia, controladorSemana, controladorMes);

        // Inicializar el controlador principal
        controladorPrincipal.iniciar();
        controladorDia.iniciar();

    }


    public static void main(String[] args) {
        launch(args);
    }
}




