import javafx.scene.Node;
import javafx.scene.Parent;

import java.awt.*;
import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.Month;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class Vista {
    private LocalDate fechaActual;
    private Parent rootVista;
    public Button obtenerBoton(String id) {
        return (Button) rootVista.lookup("#" + id);
    }
    public Node getRoot() {
        return rootVista;
    }
    protected void setRoot(Parent rootVista) {
        this.rootVista = rootVista;
    }
}
