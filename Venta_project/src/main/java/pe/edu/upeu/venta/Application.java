package pe.edu.upeu.venta;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class Application extends javafx.application.Application {

    private ConfigurableApplicationContext springContext;
    private Parent rootNode;

@Override
    public void init() throws Exception {
        springContext = new SpringApplicationBuilder(VentaApplication.class).run();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/u_c_login.fxml"));
        loader.setControllerFactory(springContext::getBean);
        rootNode = loader.load();
    }

@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login - Sistema de Ventas");
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }

@Override
    public void stop() throws Exception {
        springContext.close();
    }
}
