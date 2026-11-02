import javafx.scene.control.Label;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class ControladorDia{
    private Calendario calendario;
    private VistaDia vistaDia;
    private LocalDate fechaActual;

    public ControladorDia() {
    }
    public ControladorDia(Calendario calendario, VistaDia vistaDia) {
        this.calendario = calendario;
        this.vistaDia = vistaDia;
        this.fechaActual = LocalDate.now();
    }
    public void iniciar() {
        vistaDia.obtenerBoton("botonSiguienteVistaDia").setOnAction(event -> plusDias(1));
        vistaDia.obtenerBoton("botonAnteriorVistaDia").setOnAction(event -> plusDias(-1));
        mostrarNumerosDeLaSemana();
    }
    private void mostrarNumerosDeLaSemana(){
        vistaDia.getVistaPorDia().getChildren().clear();
        actualizarNumerosDeLaSemana();
        mostrarActividadesEnVistaDia();
    }
    private void plusDias(int cantidadDias) {
        vistaDia.getVistaPorDia().getChildren().clear();
        fechaActual = fechaActual.plusDays(cantidadDias);
        int numeroDia = fechaActual.getDayOfMonth();
        String mes = fechaActual.getMonth().getDisplayName(TextStyle.FULL, Locale.of("es", "ES")).toUpperCase();
        vistaDia.mostrarAnio(fechaActual.getYear());
        vistaDia.mostrarDiaYMes(numeroDia, mes);
        actualizarNumerosDeLaSemana();
        mostrarActividadesEnVistaDia();
    }
    private void actualizarNumerosDeLaSemana() {
        vistaDia.getNumerosDiasVistaDia().getChildren().clear();
        DayOfWeek primerDiaSemana = DayOfWeek.SUNDAY; // Establecer el primer día de la semana (por ejemplo, Domingo)
        LocalDate fechaCelda = fechaActual.with(TemporalAdjusters.previousOrSame(primerDiaSemana));

        for (int i = 0; i < 7; i++) {
            int numeroDia = fechaCelda.getDayOfMonth();
            Label numero = new Label(String.valueOf(numeroDia));

            if(fechaCelda.isEqual(LocalDate.now())){
                numero.setStyle("-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 5px; -fx-padding: 3px;");
            }
            if (fechaCelda.isEqual(fechaActual)) {
                numero.setStyle("-fx-border-color: blue; -fx-border-width: 3px; -fx-border-radius: 5px; -fx-padding: 1px;");
            }
            vistaDia.getNumerosDiasVistaDia().add(numero, i, 0);
            fechaCelda = fechaCelda.plusDays(1);
        }
    }
    private int obtenerPrimerDiaSemanaActual() {
        DayOfWeek primerDiaSemana = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        int primerDiaSemanaActual = fechaActual.with(TemporalAdjusters.previousOrSame(primerDiaSemana)).getDayOfMonth();
        return primerDiaSemanaActual;
    }
    private void mostrarActividadesEnVistaDia(){
        List<Evento> eventos;
        List<Tarea> tareas;
        if(calendario.hayActividadesEnFecha(fechaActual)){
            eventos = calendario.obtenerEventosEnFecha(fechaActual);
            tareas = calendario.obtenerTareasEnFecha(fechaActual);
            vistaDia.mostrarActividadesEnVistaDia(eventos, tareas);
        }
    }

}