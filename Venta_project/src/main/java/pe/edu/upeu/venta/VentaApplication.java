package pe.edu.upeu.venta;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VentaApplication {
    public static void main(String[] args) {
        // Launch JavaFX application which will bootstrap Spring
        javafx.application.Application.launch(Application.class, args);
    }
}
