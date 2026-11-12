package org.example.ejerciciointegradorclase.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.ejerciciointegradorclase.dao.InventarioDAO;
import org.example.ejerciciointegradorclase.model.InventarioItem;
import org.example.ejerciciointegradorclase.navigation.NavigationController;

import java.time.LocalDate;


public class CreateController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextArea descripcionArea;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private Label messageLabel;

    private final InventarioDAO inventarioDAO = new InventarioDAO();

    @FXML
    public void initialize() {
        estadoCombo.setItems(FXCollections.observableArrayList("Activo", "Inactivo", "Pendiente"));
        estadoCombo.getSelectionModel().selectFirst();
        fechaPicker.setValue(LocalDate.now());
    }

    @FXML
    private void onGuardar() {
        String nombre = nombreField.getText() != null ? nombreField.getText().trim() : "";
        String descripcion = descripcionArea.getText() != null ? descripcionArea.getText().trim() : "";
        LocalDate fecha = fechaPicker.getValue();
        String estado = estadoCombo.getValue();

        if (nombre.isEmpty()) {
            messageLabel.setText("El nombre es obligatorio.");
            return;
        }
        if (fecha == null) {
            messageLabel.setText("La fecha es obligatoria.");
            return;
        }
        if (estado == null || estado.trim().isEmpty()) {
            messageLabel.setText("El estado es obligatorio.");
            return;
        }

        try {
            InventarioItem item = new InventarioItem(nombre, descripcion, fecha, estado);
            inventarioDAO.insertar(item);

            // Volver al listado tras guardar correctamente.
            NavigationController.getInstance().navigateTo(
                    "/org/example/ejerciciointegradorclase/view/main-view.fxml",
                    "InventarioApp - Listado"
            );
        } catch (Exception e) {
            messageLabel.setText("Error al guardar el registro en la base de datos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void onVolver() {
        NavigationController.getInstance().navigateTo(
                "/org/example/ejerciciointegradorclase/view/main-view.fxml",
                "InventarioApp - Listado"
        );
    }
}
