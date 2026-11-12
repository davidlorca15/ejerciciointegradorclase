package org.example.ejerciciointegradorclase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.ejerciciointegradorclase.dao.InventarioDAO;
import org.example.ejerciciointegradorclase.model.InventarioItem;
import org.example.ejerciciointegradorclase.navigation.NavigationController;

import java.time.LocalDate;
import java.util.List;

public class MainController {

    @FXML
    private TableView<InventarioItem> inventarioTable;

    @FXML
    private TableColumn<InventarioItem, Integer> idColumn;

    @FXML
    private TableColumn<InventarioItem, String> nombreColumn;

    @FXML
    private TableColumn<InventarioItem, LocalDate> fechaColumn;

    @FXML
    private TableColumn<InventarioItem, String> estadoColumn;

    @FXML
    private Label statusLabel;

    private final InventarioDAO inventarioDAO = new InventarioDAO();
    private final ObservableList<InventarioItem> items = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        inventarioTable.setItems(items);
        cargarDatos();
    }

    private void cargarDatos() {
        try {
            List<InventarioItem> datos = inventarioDAO.obtenerTodos();
            items.setAll(datos);
            statusLabel.setText("Registros cargados: " + datos.size());
        } catch (Exception e) {
            statusLabel.setText("Error al cargar los datos desde la base de datos.");
            e.printStackTrace();
        }
    }

    @FXML
    private void onNuevo() {
        NavigationController.getInstance().navigateTo(
                "/org/example/ejerciciointegradorclase/view/create-view.fxml",
                "InventarioApp - Nuevo registro"
        );
    }

    @FXML
    private void onCerrarSesion() {
        NavigationController.getInstance().navigateTo(
                "/org/example/ejerciciointegradorclase/view/login-view.fxml",
                "InventarioApp - Login"
        );
    }

    @FXML
    private void onRefrescar() {
        cargarDatos();
    }
}
