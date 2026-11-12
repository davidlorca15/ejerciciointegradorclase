package org.example.ejerciciointegradorclase;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.ejerciciointegradorclase.dao.InventarioDAO;
import org.example.ejerciciointegradorclase.navigation.NavigationController;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        InventarioDAO inventarioDAO = new InventarioDAO();
        inventarioDAO.inicializarBaseDeDatos();


        NavigationController navigationController = NavigationController.getInstance();
        navigationController.setPrimaryStage(stage);

        stage.setMinWidth(700);
        stage.setMinHeight(450);

        navigationController.navigateTo(
                "/org/example/ejerciciointegradorclase/view/login-view.fxml",
                "InventarioApp - Login"
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
