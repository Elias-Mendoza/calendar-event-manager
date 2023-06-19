import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.Month;

public class ControladorPrincipal {

    private Calendario calendario;
    private VistaPrincipal vista;

    public ControladorPrincipal() {

    }
    public ControladorPrincipal(Calendario calendario, VistaPrincipal vista) {
        this.calendario = calendario;
        this.vista = vista;
    }
    public void iniciar (){

        vista.obtenerBoton("botonVistaPorDia").setOnAction(event -> vista.mostrarVista("vistaDia.fxml"));
        vista.obtenerBoton("botonVistaPorSemana").setOnAction(event -> vista.mostrarVista("vistaSemana.fxml"));
        vista.obtenerBoton("botonVistaPorMes").setOnAction(event -> vista.mostrarVista("vistaMes.fxml"));
        /*
        vista.obtenerBoton("botonSiguiente").setOnAction(actionEvent -> avanzarMes());
        vista.obtenerBoton("botonAnterior").setOnAction(actionEvent -> retrocederMes());
        vista.obtenerBoton("botonVistaPorDia").setOnAction(actionEvent -> {
            try {
                mostrarVistaPorDia();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        vista.obtenerBoton("botonVistaPorSemana").setOnAction(actionEvent -> mostrarVistaPorSemana());
        vista.obtenerBoton("botonVistaPorMes").setOnAction(actionEvent -> mostrarVistaPorMes());

         */
    }

    private void avanzarMes() {
        Month mesSiguiente = vista.getMesActual().plus(1);
        vista.setMesActual(mesSiguiente);
        vista.setNombreMes(new Label(mesSiguiente.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase()));
    }


    private void retrocederMes(){

    }
    private void mostrarVistaPorDia() throws IOException {
        // Lógica para mostrar la vista por día.
        // Por ejemplo, puedes modificar el contenido del VBox vistaPorDia.
    //    vista.mostrarEvento(calendario.obtenerEventos().get(0));
        try {
            vista.setRoot("vistaSemana");
            System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        }catch (IOException e){
            e.getMessage();
        }

    }

    private void mostrarVistaPorSemana() {
        // Lógica para mostrar la vista por semana.
        // Por ejemplo, puedes modificar el contenido del GridPane vistaPorSemana.
        vista.mostrarNodo(vista.getVistaPorSemana());
    }

    private void mostrarVistaPorMes() {
        // Lógica para mostrar la vista por mes.
        // Por ejemplo, puedes modificar el contenido del GridPane VistaPorMes.
        vista.mostrarNodo(vista.getVistaPorMes());
//        String textoAMostrar = calendario.obtenerEventos().get(0).getTitulo();
//        Label labelTextoAMostrar = new Label(textoAMostrar);
//        vista.mostrarEvento(labelTextoAMostrar);
    }
}
