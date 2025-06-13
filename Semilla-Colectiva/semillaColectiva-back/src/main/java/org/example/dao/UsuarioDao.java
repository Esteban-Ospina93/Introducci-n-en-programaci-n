package org.example.dao;

import org.example.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends Usuario {
    //Realizar la conexión a la base de datos mySql
    //Credenciales
    private final String url = "jdbc:mysql://localhost:3306/db_semillacolectiva";
    private final String user = "root";
    private final String password = "Esteban1214717088*";
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    //Metodo para agregar usuarios
    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, contrasena, rol, fecha_registro) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmtQuery = conn.prepareStatement(sql)) {
            stmtQuery.setString(1, usuario.getNombre());
            stmtQuery.setString(2, usuario.getCorreo());
            stmtQuery.setString(3, usuario.getContrasena());
            stmtQuery.setString(4, usuario.getRol().name().toUpperCase());
            stmtQuery.setTimestamp(5, usuario.getFechaRegistro());
            stmtQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Meotodo para listar
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(Usuario.Rol.valueOf(rs.getString("rol").toUpperCase()));

                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Para listar
    public List<Usuario> buscarUsuarioPorNombreUsuario(String nombre) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setRol(Usuario.Rol.valueOf(rs.getString("rol").toUpperCase()));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));

                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Metodo para actualizar
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre=?, correo=?, contrasena=?, rol=?, fecha_registro=? WHERE id=?";

        try (
                Connection conn = conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getContrasena());
            stmt.setString(4, usuario.getRol().name().toUpperCase());
            stmt.setTimestamp(5, usuario.getFechaRegistro());
            stmt.setInt(6, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Metodo para eliminar usuarios
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (
                Connection conn = conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
