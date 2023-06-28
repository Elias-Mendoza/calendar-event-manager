import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class ControladorMes {
    private VistaMes vistaMes;
    private Calendario calendario;
    private LocalDate fechaActual;

    public ControladorMes() {
    }
    public ControladorMes(Calendario calendario, VistaMes vistaMes){
        this.vistaMes = vistaMes;
        this.calendario = calendario;
        this.fechaActual = LocalDate.now();
    }

    public void iniciar() {
        vistaMes.obtenerBoton("botonSiguienteVistaMes").setOnAction(event -> plusMes(1));
        vistaMes.obtenerBoton("botonAnteriorVistaMes").setOnAction(event -> plusMes(-1));
        mostrarNumerosDelMes(vistaMes.getVistaPorMes());
    }
    private void mostrarNumerosDelMes(GridPane vistaPorMes){
        vistaMes.getNumerosDiasVistaMes().getChildren().clear();
        actualizarNumerosDelMes();
    }

    private void plusMes(int cantidadMeses) {
        vistaMes.getNumerosDiasVistaMes().getChildren().clear();
        fechaActual = fechaActual.plusMonths(cantidadMeses);
        String mesAnterior = fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
        vistaMes.mostrarAnio(fechaActual.getYear());
        vistaMes.mostrarMes(mesAnterior);
        actualizarNumerosDelMes();
    }

    private void actualizarNumerosDelMes() {
        int comienzo = calcularComienzo();
        int numero = 1;
        LocalDate fechaCelda = fechaActual.withDayOfMonth(1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (comienzo > 0) {
                    comienzo -= 1;
                } else if (numero <= fechaActual.lengthOfMonth()) {

                    List<Evento> eventos;
                    List<Tarea> tareas;
                    if(calendario.hayActividadesEnFecha(fechaCelda)){
                        Button botonActividad = new Button("HAY ACTIVIDADES");
                        vistaMes.getNumerosDiasVistaMes().add(botonActividad, j, i);
                        GridPane.setValignment(botonActividad, VPos.CENTER);
                        GridPane.setHalignment(botonActividad, HPos.CENTER);
                        eventos = calendario.obtenerEventosEnFecha(fechaCelda);
                        tareas = calendario.obtenerTareasEnFecha(fechaCelda);

                        botonActividad.setOnAction(event -> {
                            VentanaActividad ventanaActividad = new VentanaActividad(eventos, tareas);
                            ventanaActividad.show();
                        });
                    }
                    Label labelNumeroMes = new Label(String.valueOf(numero));
                    vistaMes.getNumerosDiasVistaMes().add(labelNumeroMes, j, i);
                    GridPane.setValignment(labelNumeroMes, Pos.TOP_LEFT.getVpos());
                    numero += 1;
                    fechaCelda = fechaCelda.plusDays(1);
                }

            }
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


