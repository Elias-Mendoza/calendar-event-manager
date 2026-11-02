import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class ControladorSemana {

    Calendario calendario;
    VistaSemana vistaSemana;
    LocalDate fechaActual;
    public ControladorSemana() {
    }
    public ControladorSemana(Calendario calendario, VistaSemana vistaSemana) {
        this.calendario = calendario;
        this.vistaSemana = vistaSemana;
        this.fechaActual = LocalDate.now();
    }
    public void iniciar() {
        vistaSemana.obtenerBoton("botonSiguienteVistaSemana").setOnAction(actionEvent -> plusSemanas(1));
        vistaSemana.obtenerBoton("botonAnteriorVistaSemana").setOnAction(actionEvent -> plusSemanas(-1));
        limpiarContenidoActividades();
        mostrarNumerosDeLaSemana();
    }
    private void mostrarNumerosDeLaSemana(){

        actualizarVistaDeLaSemana();
    }
    private void actualizarVistaDeLaSemana() {
        vistaSemana.getNumerosDiasVistaSemana().getChildren().clear();
        DayOfWeek primerDiaSemana = DayOfWeek.SUNDAY; // Las columnas de la vista empiezan en Domingo
        LocalDate fechaCelda = fechaActual.with(TemporalAdjusters.previousOrSame(primerDiaSemana));
        for (int i = 0; i < 7; i++) {
            // El número sale de la misma fecha de la celda que se usa para buscar actividades
            Label numero = new Label(String.valueOf(fechaCelda.getDayOfMonth()));
            if (fechaCelda.isEqual(LocalDate.now())) {
                numero.getStyleClass().add("hoy");
            }
            vistaSemana.getNumerosDiasVistaSemana().add(numero, i, 0);
            verificarActividadesEnFechaCelda(i, 0, fechaCelda);
            fechaCelda = fechaCelda.plusDays(1);
        }
    }

    private void verificarActividadesEnFechaCelda(int fila, int columna, LocalDate fechaCelda) {
        List<Evento> eventos;
        List<Tarea> tareas;
        if(calendario.hayActividadesEnFecha(fechaCelda)) {
            eventos = calendario.obtenerEventosEnFecha(fechaCelda);
            tareas = calendario.obtenerTareasEnFecha(fechaCelda);
            vistaSemana.mostrarActividadesEnVistaSemana(fila, columna, eventos, tareas);
        }
    }

    public void plusSemanas(int cantidadSemanas){
        vistaSemana.getNumerosDiasVistaSemana().getChildren().clear();
        //vistaSemana.getVistaPorSemana().getChildren().clear();
        limpiarContenidoActividades();
        fechaActual = fechaActual.plusDays(cantidadSemanas*7);
        vistaSemana.mostrarMesActual(fechaActual.getMonth().getDisplayName(TextStyle.FULL, Locale.of("es", "ES")).toUpperCase());
        vistaSemana.mostrarAnioActual(fechaActual.getYear());

        actualizarVistaDeLaSemana();

    }

    private void limpiarContenidoActividades(){
        for (Node columna : vistaSemana.getVistaPorSemana().getChildren()) {
            if (columna instanceof VBox) {
                VBox vBox = (VBox) columna;
                vBox.getChildren().clear();
            }
        }
    }


}