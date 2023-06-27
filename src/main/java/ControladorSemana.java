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
    public ControladorSemana(VistaSemana vistaSemana) {
        this.vistaSemana = vistaSemana;
    }

    public void iniciar() {
        vistaSemana.obtenerBoton("botonSiguienteVistaSemana").setOnAction(actionEvent -> plusSemanas(1));
        vistaSemana.obtenerBoton("botonAnteriorVistaSemana").setOnAction(actionEvent -> plusSemanas(-1));
        mostrarNumerosDeLaSemana(vistaSemana.getVistaPorSemana());
    }
    private void mostrarNumerosDeLaSemana(GridPane vistaPorSemana){
        actualizarNumerosDeLaSemana();
    }


    private void actualizarNumerosDeLaSemana() {
        int diaSemana = fechaActual.getDayOfWeek().getValue(); // Obtener el día de la semana (1: Domingo, 2: Lunes, ..., 7: Sábado)
        System.out.println("fechaActual es: " + fechaActual);
        int diasRestantes = 8 - diaSemana; // Calcular los días restantes hasta el próximo domingo
        System.out.println("diaSemana vale: " + diaSemana);
        System.out.println("diasRestantes vale: 8 - " + diaSemana + " = " + diasRestantes);
        int numeroDiasSemana = Math.min(diasRestantes, 7); // Tomar el mínimo entre los días restantes y 7
        int numeroSemana = obtenerPrimerDiaSemanaActual();
        int numeroPrimerDiaSemanaActual = obtenerNumeroPrimerDiaSemanaActual();
        System.out.println("numeroPrimerDiaSemanaActual es: " + numeroPrimerDiaSemanaActual);

        System.out.println("La semana actual tiene " + numeroDiasSemana + " días.");
        for(int i = numeroPrimerDiaSemanaActual-1; i < numeroDiasSemana; i++){
            Label numero = new Label(String.valueOf(numeroSemana));
            vistaSemana.getNumerosDiasVistaSemana().add(numero, i, 0);
            numeroSemana++;
        }
    }
    private int obtenerPrimerDiaSemanaActual() {;
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