import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidades de presentación: hoja de estilos y tarjetas de actividades.
 * No modifica el modelo; solo lee sus datos para mostrarlos.
 */
public final class EstiloUI {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    private EstiloUI() {
    }

    public static String hojaDeEstilos() {
        URL url = EstiloUI.class.getResource("estilos.css");
        return url == null ? null : url.toExternalForm();
    }

    public static void aplicar(Scene escena) {
        String hoja = hojaDeEstilos();
        if (escena != null && hoja != null && !escena.getStylesheets().contains(hoja)) {
            escena.getStylesheets().add(hoja);
        }
    }

    public static void aplicar(DialogPane panel) {
        String hoja = hojaDeEstilos();
        if (panel != null && hoja != null && !panel.getStylesheets().contains(hoja)) {
            panel.getStylesheets().add(hoja);
        }
    }

    // ---------- Helpers de maquetación para formularios ----------

    /** Contenedor vertical de un formulario de diálogo. */
    public static VBox formulario(Node... hijos) {
        VBox caja = new VBox(hijos);
        caja.getStyleClass().add("formulario");
        caja.setPrefWidth(500);
        return caja;
    }

    /** Título de una sección del formulario. */
    public static Label seccion(String texto) {
        Label label = new Label(texto.toUpperCase());
        label.getStyleClass().add("seccion-formulario");
        return label;
    }

    /** Etiqueta arriba y control abajo, ocupando todo el ancho disponible. */
    public static VBox campo(String etiqueta, Node control) {
        Label label = new Label(etiqueta);
        label.getStyleClass().add("etiqueta-campo");
        label.disableProperty().bind(control.disableProperty());
        if (control instanceof Region) {
            ((Region) control).setMaxWidth(Double.MAX_VALUE);
        }
        VBox caja = new VBox(label, control);
        caja.getStyleClass().add("campo");
        return caja;
    }

    /** Varios campos lado a lado, repartiendo el ancho por igual. */
    public static HBox fila(Node... nodos) {
        HBox fila = new HBox(nodos);
        fila.getStyleClass().add("fila-formulario");
        for (Node nodo : nodos) {
            HBox.setHgrow(nodo, Priority.ALWAYS);
            if (nodo instanceof Region) {
                ((Region) nodo).setMaxWidth(Double.MAX_VALUE);
                ((Region) nodo).setPrefWidth(0);
            }
        }
        return fila;
    }

    /** Opción en una sola línea: check + campo corto + textos, alineados. */
    public static HBox opcionEnLinea(CheckBox check, TextField campoCorto, String antes, String despues) {
        HBox linea = new HBox();
        linea.getStyleClass().add("opcion-en-linea");
        linea.setAlignment(Pos.CENTER_LEFT);
        linea.getChildren().add(check);
        Region separador = new Region();
        HBox.setHgrow(separador, Priority.ALWAYS);
        linea.getChildren().add(separador);
        if (antes != null) {
            linea.getChildren().add(textoQueSigue(antes, campoCorto));
        }
        campoCorto.setPrefColumnCount(4);
        campoCorto.setMaxWidth(70);
        linea.getChildren().add(campoCorto);
        if (despues != null) {
            linea.getChildren().add(textoQueSigue(despues, campoCorto));
        }
        return linea;
    }

    private static Label textoQueSigue(String texto, Node control) {
        Label label = new Label(texto);
        label.getStyleClass().add("texto-opcion");
        label.disableProperty().bind(control.disableProperty());
        return label;
    }

    public static VBox tarjetaEvento(Evento evento) {
        VBox tarjeta = nuevaTarjeta("EVENTO", evento.getTitulo(), evento.getDescripcion());
        tarjeta.getChildren().add(detalle(rangoEvento(evento)));
        return tarjeta;
    }

    public static VBox tarjetaTarea(Tarea tarea) {
        VBox tarjeta = nuevaTarjeta("TAREA", tarea.getTitulo(), tarea.getDescripcion());
        tarjeta.getStyleClass().add("tarea");
        if (tarea.getEstaCompletada()) {
            tarjeta.getStyleClass().add("completada");
        }
        tarjeta.getChildren().add(detalle("Vence: " + vencimientoTarea(tarea)));
        tarjeta.getChildren().add(detalle(tarea.getEstaCompletada() ? "Completada" : "Pendiente"));
        return tarjeta;
    }

    private static VBox nuevaTarjeta(String tipo, String titulo, String descripcion) {
        VBox tarjeta = new VBox();
        tarjeta.getStyleClass().add("tarjeta");

        Label etiquetaTipo = new Label(tipo);
        etiquetaTipo.getStyleClass().add("tipo");

        Label etiquetaTitulo = new Label(textoOVacio(titulo, "(Sin título)"));
        etiquetaTitulo.getStyleClass().add("titulo");
        etiquetaTitulo.setWrapText(true);

        tarjeta.getChildren().addAll(etiquetaTipo, etiquetaTitulo);
        if (descripcion != null && !descripcion.isBlank()) {
            tarjeta.getChildren().add(detalle(descripcion));
        }
        return tarjeta;
    }

    private static Label detalle(String texto) {
        Label label = new Label(texto);
        label.getStyleClass().add("detalle");
        label.setWrapText(true);
        return label;
    }

    private static String rangoEvento(Evento evento) {
        LocalDateTime inicio = evento.getInicio();
        LocalDateTime fin = evento.getFin();
        if (inicio == null) {
            return "";
        }
        if (evento instanceof EventoDiaCompleto) {
            LocalDate diaInicio = inicio.toLocalDate();
            LocalDate diaFin = fin == null ? diaInicio : fin.toLocalDate();
            if (!diaFin.isAfter(diaInicio)) {
                return "Todo el día · " + diaInicio.format(FORMATO_FECHA);
            }
            return "Todo el día · " + diaInicio.format(FORMATO_FECHA) + " – " + diaFin.format(FORMATO_FECHA);
        }
        if (fin == null) {
            return inicio.format(FORMATO_FECHA_HORA);
        }
        if (inicio.toLocalDate().equals(fin.toLocalDate())) {
            return inicio.format(FORMATO_FECHA) + " · " + inicio.format(FORMATO_HORA) + " – " + fin.format(FORMATO_HORA);
        }
        return inicio.format(FORMATO_FECHA_HORA) + " – " + fin.format(FORMATO_FECHA_HORA);
    }

    private static String vencimientoTarea(Tarea tarea) {
        if (tarea instanceof TareaDiaCompleto) {
            LocalDate fecha = tarea.getFechaTarea();
            return fecha == null ? "-" : fecha.format(FORMATO_FECHA) + " (todo el día)";
        }
        LocalDateTime fechaHora = tarea.getFechaYHoraVencimiento();
        if (fechaHora != null) {
            return fechaHora.format(FORMATO_FECHA_HORA);
        }
        LocalDate fecha = tarea.getFechaTarea();
        return fecha == null ? "-" : fecha.format(FORMATO_FECHA);
    }

    private static String textoOVacio(String texto, String porDefecto) {
        return texto == null || texto.isBlank() ? porDefecto : texto;
    }
}
