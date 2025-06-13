package org.example.dao;

import org.example.modelo.Voluntario;
import org.example.modelo.Participante;
import org.example.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDao extends Voluntario {
    //Realizar la conexión a la base de datos mySql
    //Credenciales
    private final String url = "jdbc:mysql://localhost:3306/db_semillacolectiva";
    private final String user = "root";
    private final String password = "Esteban1214717088*";
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    //Metodo para agregar voluntarios
    public void agregarVoluntario(Participante voluntario) {
        String sql = "INSERT INTO voluntarios (nombre, apellidos, telefono, usuario_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmtQuery = conn.prepareStatement(sql)) {
            stmtQuery.setString(1, voluntario.getNombre());
            stmtQuery.setString(2, voluntario.getApellido());
            stmtQuery.setString(3, voluntario.getTelefono());
            stmtQuery.setInt(4, voluntario.getUsuario().getId());
            stmtQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Meotodo para listar
    public List<Participante> listarVoluntario() {
        List<Participante> lista = new ArrayList<>();
        String sql = "SELECT * FROM voluntarios";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Participante voluntario = new Voluntario();
                voluntario.setId(rs.getInt("id"));
                voluntario.setNombre(rs.getString("nombre"));
                voluntario.setApellido(rs.getString("apellidos"));
                voluntario.setTelefono(rs.getString("telefono"));
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("usuario_id"));
                voluntario.setUsuario(usuario);

                lista.add(voluntario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Para listar
    public List<Voluntario> buscarVoluntarioPorNombreVoluntario(String nombre) {
        List<Voluntario> lista = new ArrayList<>();
        String sql = "SELECT * FROM voluntarios WHERE nombre = ?";

//        String sql = "SELECT v.id AS voluntario_id, v.nombre AS voluntario_nombre, v.apellidos, v.telefono, " +
//                "u.id AS usuario_id, u.nombre_usuario, u.correo, u.rol " +
//                "FROM voluntarios v " +
//                "INNER JOIN usuarios u ON v.usuario_id = u.id " +
//                "WHERE v.nombre = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Voluntario voluntario = new Voluntario();
                voluntario.setId(rs.getInt("id"));
                voluntario.setNombre(rs.getString("nombre"));
                voluntario.setApellido(rs.getString("apellidos"));
                voluntario.setTelefono(rs.getString("telefono"));
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("usuario_id"));
                voluntario.setUsuario(usuario);


                lista.add(voluntario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Metodo para actualizar
    public void actualizarVoluntario(Voluntario voluntario) {
        String sql = "UPDATE voluntarios SET nombre=?, apellidos=?, telefono=? WHERE id=?";

        try (
                Connection conn = conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, voluntario.getNombre());
            stmt.setString(2, voluntario.getApellido());
            stmt.setString(3, voluntario.getTelefono());
            stmt.setInt(4, voluntario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Metodo para eliminar árboles
    public void eliminarVoluntario(int id) {
        String sql = "DELETE FROM voluntarios WHERE id=?";
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


