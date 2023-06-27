import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

public class ControladorSemana{

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
        mostrarNumerosDeLaSemana();

    }
    private void mostrarNumerosDeLaSemana(){
        actualizarNumerosDeLaSemana();
    }


    private void actualizarNumerosDeLaSemana() {
        vistaSemana.getNumerosDiasVistaSemana().getChildren().clear();
        int numeroDia = obtenerPrimerDiaSemanaActual(); // Obtener el primer día de la semana
        int diaSemana = fechaActual.getDayOfWeek().getValue(); // Obtener el día de la semana (1: Domingo, 2: Lunes, ..., 7: Sábado)

        for (int i = 0; i < 7; i++) {
            Label numero = new Label(String.valueOf(numeroDia));
            vistaSemana.getNumerosDiasVistaSemana().add(numero, i, 0);

            numeroDia++;
            if (numeroDia > fechaActual.lengthOfMonth()) {
                numeroDia = 1; // Reiniciar el número del día si supera la longitud del mes actual
            }
        }
    }
    private int obtenerPrimerDiaSemanaActual() {
        DayOfWeek primerDiaSemana = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        int primerDiaSemanaActual = fechaActual.with(TemporalAdjusters.previousOrSame(primerDiaSemana)).getDayOfMonth();
        return primerDiaSemanaActual;
    }
    private int obtenerPrimerDiaSemanaActual2() {;
        DayOfWeek primerDiaSemana = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        LocalDate primerDiaSemanaActual = fechaActual.with(TemporalAdjusters.previousOrSame(primerDiaSemana));
        return primerDiaSemanaActual.getDayOfMonth();
    }
    private int obtenerNumeroPrimerDiaSemanaActual() {
        DayOfWeek primerDiaSemana = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        int primerDiaSemanaActual = fechaActual.with(TemporalAdjusters.previousOrSame(primerDiaSemana)).getDayOfWeek().getValue();
        return primerDiaSemanaActual;
    }

    public void plusSemanas(int cantidadSemanas){
        vistaSemana.getNumerosDiasVistaSemana().getChildren().clear();
        fechaActual = fechaActual.plusDays(cantidadSemanas*7);
        vistaSemana.mostrarMesActual(fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase());
        vistaSemana.mostrarAnioActual(fechaActual.getYear());
        actualizarNumerosDeLaSemana();
    }


}