package pe.edu.upeu.venta.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import pe.edu.upeu.venta.service.UsuaService;

@Component
public class LoginController {

    // Declaramos una variable de tipo UsuaService para manejar la lógica del login
    private final UsuaService usuaService;

    // Constructor: se inyecta automáticamente el servicio (Inyección de Dependencias)
    public LoginController(UsuaService usuaService) {
        this.usuaService = usuaService;
    }

    // Con @FXML conectamos los elementos gráficos del archivo Login.fxml
    @FXML
    private TextField txtUsuario; // Caja de texto donde el usuario escribe su nombre/usuario
    @FXML
    private PasswordField pfClave; // Caja de texto para la contraseña (oculta los caracteres)

    // Método que se ejecuta cuando el usuario hace clic en el botón "Entrar"
    @FXML
    protected void entrarLogin() {
        // Obtenemos el texto ingresado en la caja de usuario y le quitamos espacios extras
        String usuario = txtUsuario.getText().trim();
        // Obtenemos la contraseña escrita en la caja de contraseña
        String clave =  pfClave.getText();

        // Validamos si los campos están vacíos
        if (usuario.isEmpty() || clave.isEmpty()) {
            showAlert("Error", "Ingrese usuario y contraseña", Alert.AlertType.WARNING);
            return; // Detiene la ejecución del método aquí
        }

        // Llamamos al servicio para autenticar al usuario y contraseña
        boolean ok = usuaService.authenticate(usuario, clave);

        // Si las credenciales son correctas
        if (ok) {
            showAlert("Éxito", "Bienvenido al Sistema de Ventas hora de Ganar para la comidita",
                    Alert.AlertType.INFORMATION);

        } else {
            // Si las credenciales son incorrectas
            showAlert("Error", "Tu usuario o contraseña pueden estar incorrectos", Alert.AlertType.ERROR);
        }
    }

    // Método privado para mostrar mensajes emergentes en pantalla
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert a = new Alert(type); // Se crea una alerta del tipo recibido (INFO, ERROR, WARNING)
        a.setTitle(title); // Se le pone el título a la alerta
        a.setHeaderText(null); // No se usa cabecera
        a.setContentText(message); // Texto principal de la alerta
        a.showAndWait(); // Muestra la alerta y espera a que el usuario la cierre
    }
}
