import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.Serializable;

public class ControladorPrincipal{
    private Calendario calendario;
    private VistaPrincipal vistaPrincipal;
    private VistaDia vistaDia;
    private VistaSemana vistaSemana;
    private VistaMes vistaMes;
    private ControladorDia controladorDia;
    private ControladorSemana controladorSemana;
    private ControladorMes controladorMes;

    public ControladorPrincipal() {
    }
    public ControladorPrincipal(Calendario calendario, VistaPrincipal vistaPrincipal) {
        this.calendario = calendario;
        this.vistaPrincipal = vistaPrincipal;
        calendario.addObserver(vistaPrincipal);
    }
    public void setControladores(ControladorDia controladorDia, ControladorSemana controladorSemana, ControladorMes controladorMes) {
        this.controladorDia = controladorDia;
        this.controladorSemana = controladorSemana;
        this.controladorMes = controladorMes;
    }
    public void setVistas(VistaDia vistaDia, VistaSemana vistaSemana, VistaMes vistaMes){
        this.vistaDia = vistaDia;
        this.vistaSemana = vistaSemana;
        this.vistaMes = vistaMes;
    }

    public void iniciar() {
        vistaPrincipal.obtenerBoton("botonVistaPorDia").setOnAction(event -> cambiarVistaADia());
        vistaPrincipal.obtenerBoton("botonVistaPorSemana").setOnAction(event -> cambiarVistaASemana());
        vistaPrincipal.obtenerBoton("botonVistaPorMes").setOnAction(event -> cambiarVistaAMes());
        vistaPrincipal.obtenerBoton("botonAgregarEvento").setOnAction(event -> abrirVentanaAgregarEvento() );
        vistaPrincipal.obtenerBoton("botonAgregarTarea").setOnAction(event -> abrirVentanaAgregarTarea() );
        iniciarVerificacionAlarma();
    }

    private void abrirVentanaAgregarEvento(){
        VentanaAgregarEvento ventanaAgregarEvento = new VentanaAgregarEvento(calendario);
        ControladorVentanaAgregarEvento controladorVentanaAgregarEvento = new ControladorVentanaAgregarEvento(calendario, ventanaAgregarEvento);
        controladorVentanaAgregarEvento.iniciar();
    }
    private void abrirVentanaAgregarTarea(){
        VentanaAgregarTarea ventanaAgregarTarea = new VentanaAgregarTarea(calendario);
        ControladorVentanaAgregarTarea controladorVentanaAgregarTarea = new ControladorVentanaAgregarTarea(calendario, ventanaAgregarTarea);
        controladorVentanaAgregarTarea.iniciar();
    }

    private void cambiarVistaADia() {
        vistaPrincipal.cambiarVista(vistaDia);
        controladorDia.iniciar();
    }
    public void cambiarVistaASemana(){
        vistaPrincipal.cambiarVista(vistaSemana);
        controladorSemana.iniciar();
    }
    public void cambiarVistaAMes(){
        vistaPrincipal.cambiarVista(vistaMes);
        controladorMes.iniciar();
    }
    public void verificarAlarma() {
        calendario.verificarAlarma();
    }
    public void iniciarVerificacionAlarma() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> verificarAlarma()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
