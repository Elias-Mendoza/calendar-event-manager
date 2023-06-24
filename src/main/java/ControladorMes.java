import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ControladorMes {
    private VistaMes vistaMes;
    private Calendario calendario;
    private LocalDate fechaActual;
    List<Label> listaNumerosMes;


    public ControladorMes() {
        // Constructor sin argumentos
    }
    public ControladorMes(Calendario calendario, VistaMes vistaMes){
        this.vistaMes = vistaMes;
        this.calendario = calendario;
        this.fechaActual = LocalDate.now();
        listaNumerosMes = new ArrayList<Label>();
    }

    public void iniciar() {
        vistaMes.obtenerBoton("botonSiguienteVistaMes").setOnAction(event -> plusMes(1));
        vistaMes.obtenerBoton("botonAnteriorVistaMes").setOnAction(event -> plusMes(-1));
        mostrarNumerosDelMes(vistaMes.getVistaPorMes());
    }
    private void mostrarNumerosDelMes(GridPane vistaPorMes){
        actualizarNumerosDelMes();
    }

    private void plusMes(int cantidadMeses) {
        vistaMes.getNumerosDiasVistaMes().getChildren().clear();
        fechaActual = fechaActual.plusMonths(cantidadMeses);
        String mesAnterior = fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
        vistaMes.mostrarAnio(fechaActual.getYear());
        vistaMes.mostrarMes(mesAnterior);
        actualizarNumerosDelMes();
        //vistaMes.getNumerosDiasVistaMes().add(setCenter(new Button("boton")), 4,4);
    }

    private void actualizarNumerosDelMes() {
        int comienzo = calcularComienzo();
        int numero = 1;
        LocalDate fechaCelda = fechaActual.withDayOfMonth(1);
        for(int i = 0; i<8; i++){
            for(int j=0; j<7; j++){
                if(comienzo > 0){
                    comienzo -= 1;
                }
                else if(numero <= fechaActual.lengthOfMonth()) {
                    if(hayActividades(fechaCelda)){
                        vistaMes.getNumerosDiasVistaMes().add(new Button(""), j, i);
                    }
                    Label labelNumeroMes = new Label(String.valueOf(numero));
                    //vistaMes.getNumerosDiasVistaMes().add(new Label(String.valueOf(numero)), j, i);
                    vistaMes.getNumerosDiasVistaMes().add(labelNumeroMes, j, i);
                    GridPane.setValignment(labelNumeroMes, Pos.TOP_LEFT.getVpos());
                    numero += 1;
                    fechaCelda.plusDays(1);
                }
            }
        }
    }
    private boolean hayActividades(LocalDate fecha){
        return true;
    }

    private void eliminarLabels(int cantidad) {
        int numLabels = vistaMes.getVistaPorMes().getChildren().size();
        // Verificar si la cantidad es mayor a la cantidad de Labels agregados
        if (cantidad > numLabels) {
            cantidad = numLabels;
        }
        // Eliminar los últimos Labels agregados
        for (int i = numLabels - 1; i >= numLabels - cantidad; i--) {
            vistaMes.getVistaPorMes().getChildren().remove(i);
        }
    }

    private int calcularComienzo() {
        int comienzo = switch (fechaActual.withDayOfMonth(1).getDayOfWeek()) {
            case SUNDAY -> 0;
            case MONDAY -> 1;
            case TUESDAY -> 2;
            case WEDNESDAY -> 3;
            case THURSDAY -> 4;
            case FRIDAY -> 5;
            case SATURDAY -> 6;
        };
        return comienzo;
    }

}
