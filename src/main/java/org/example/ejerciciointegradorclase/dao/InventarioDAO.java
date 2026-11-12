package org.example.ejerciciointegradorclase.dao;

import org.example.ejerciciointegradorclase.model.InventarioItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    private static final String URL = "jdbc:h2:./inventario_db;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    /**
     * Obtiene una conexión JDBC usando PreparedStatement/ResultSet en las operaciones.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void inicializarBaseDeDatos() {
        String crearUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(50) NOT NULL UNIQUE, "
                + "password VARCHAR(100) NOT NULL"
                + ")";

        String crearInventario = "CREATE TABLE IF NOT EXISTS inventario ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nombre VARCHAR(100) NOT NULL, "
                + "descripcion VARCHAR(255), "
                + "fecha DATE NOT NULL, "
                + "estado VARCHAR(30) NOT NULL"
                + ")";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {

            st.execute(crearUsuarios);
            st.execute(crearInventario);


            if (!existeUsuario("admin")) {
                insertarUsuario(conn, "admin", "admin");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al inicializar la base de datos", e);
        }
    }

    private boolean existeUsuario(String username) throws SQLException {
        String sql = "SELECT 1 FROM usuarios WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private void insertarUsuario(Connection conn, String username, String password) throws SQLException {
        String sql = "INSERT INTO usuarios (username, password) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        }
    }

    public boolean validarCredenciales(String username, String password) {
        String sql = "SELECT 1 FROM usuarios WHERE username = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al validar credenciales", e);
        }
    }


    public List<InventarioItem> obtenerTodos() {
        String sql = "SELECT id, nombre, descripcion, fecha, estado FROM inventario ORDER BY id DESC";
        List<InventarioItem> items = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Date fechaSql = rs.getDate("fecha");
                LocalDate fecha = fechaSql != null ? fechaSql.toLocalDate() : null;
                String estado = rs.getString("estado");

                items.add(new InventarioItem(id, nombre, descripcion, fecha, estado));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar el inventario", e);
        }

        return items;
    }


    public void insertar(InventarioItem item) {
        String sql = "INSERT INTO inventario (nombre, descripcion, fecha, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getNombre());
            ps.setString(2, item.getDescripcion());
            ps.setDate(3, Date.valueOf(item.getFecha()));
            ps.setString(4, item.getEstado());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el registro de inventario", e);
        }
    }
}
