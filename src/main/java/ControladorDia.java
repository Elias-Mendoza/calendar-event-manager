import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.TextStyle;
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
    }
    private void plusDias(int cantidadDias) {
        fechaActual = fechaActual.plusDays(cantidadDias);
        int numeroDia = fechaActual.getDayOfMonth();
        String mes = fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
        vistaDia.mostrarAnio(fechaActual.getYear());
        vistaDia.mostrarDiaYMes(numeroDia, mes);
    }

}