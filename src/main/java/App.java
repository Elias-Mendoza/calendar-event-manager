import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class App extends Application {
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
   }

    public static void main(String[] args) {
        launch(args);
    }
}




