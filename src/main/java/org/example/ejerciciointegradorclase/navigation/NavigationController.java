package org.example.ejerciciointegradorclase.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Controlador de navegación centralizado.
 * Gestiona el cambio de escenas sobre un único Stage para evitar múltiples ventanas.
 */
public class NavigationController {

    private static NavigationController instance;
    private Stage primaryStage;

    private NavigationController() {
    }

    public static NavigationController getInstance() {
        if (instance == null) {
            instance = new NavigationController();
        }
        return instance;
    }

    /**
     * Debe llamarse una vez desde la clase principal (Application.start).
     */
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Carga un FXML desde resources y lo establece como escena actual del Stage principal.
     *
     * @param fxmlPath ruta relativa dentro de resources, por ejemplo "/org/example/ejerciciointegradorclase/view/login-view.fxml"
     * @param title    título de la ventana
     */
    public void navigateTo(String fxmlPath, String title) {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage no inicializado. Llama a setPrimaryStage() en start().");
        }

        try {
            URL resource = getClass().getResource(fxmlPath);
            if (resource == null) {
                throw new IllegalArgumentException("No se encontró el recurso FXML: " + fxmlPath);
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la vista: " + fxmlPath, e);
        }
    }
}
