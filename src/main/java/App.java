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
        Calendario calendario = new Calendario();
        //CARGAMOS EL MODELO
        InputStream is = new FileInputStream("calendario.dat");
        calendario = Calendario.cargarCalendario(is);
        //FIN CARGAR EL MODELO

        //CARGAMOS LA VISTA
        VistaPrincipal vistaPrincipal = new VistaPrincipal(primaryStage, calendario);
        VistaDia vistaDia = new VistaDia(primaryStage, calendario);
        VistaSemana vistaSemana = new VistaSemana(primaryStage, calendario);
        VistaMes vistaMes = new VistaMes(primaryStage, calendario);

        //CARGAMOS EL CONTROLADOR
        ControladorPrincipal controladorPrincipal = new ControladorPrincipal(calendario, vistaPrincipal);
        ControladorDia controladorDia = new ControladorDia(calendario, vistaDia);
        ControladorSemana controladorSemana = new ControladorSemana(calendario, vistaSemana);
        ControladorMes controladorMes = new ControladorMes(calendario, vistaMes);

        controladorPrincipal.setVistas(vistaDia, vistaSemana, vistaMes);
        controladorPrincipal.setControladores(controladorDia, controladorSemana, controladorMes);

        vistaPrincipal.mostrarVista();
        vistaPrincipal.cambiarVista(vistaMes);
        controladorPrincipal.iniciar();
        controladorMes.iniciar();

    }


    public static void main(String[] args) {
        launch(args);
    }
}




