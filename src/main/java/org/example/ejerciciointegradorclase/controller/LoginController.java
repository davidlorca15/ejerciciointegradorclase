package org.example.ejerciciointegradorclase.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.ejerciciointegradorclase.dao.InventarioDAO;
import org.example.ejerciciointegradorclase.navigation.NavigationController;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private final InventarioDAO inventarioDAO = new InventarioDAO();

    @FXML
    private void onLogin() {
        String username = usernameField.getText() != null ? usernameField.getText().trim() : "";
        String password = passwordField.getText() != null ? passwordField.getText().trim() : "";

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Introduce usuario y contraseña.");
            return;
        }

        try {
            boolean valido = inventarioDAO.validarCredenciales(username, password);
            if (valido) {
                NavigationController.getInstance().navigateTo(
                        "/org/example/ejerciciointegradorclase/view/main-view.fxml",
                        "InventarioApp - Listado"
                );
            } else {
                messageLabel.setText("Credenciales incorrectas.");
            }
        } catch (Exception e) {
            messageLabel.setText("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }
}
