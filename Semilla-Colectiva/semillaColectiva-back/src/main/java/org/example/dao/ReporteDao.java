package org.example.dao;

import org.example.modelo.Reporte;
import org.example.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteDao {
    //Realizar la conexión a la base de datos mySql
    //Credenciales
    private final String url = "jdbc:mysql://localhost:3306/db_semillacolectiva";
    private final String user = "root";
    private final String password = "Esteban1214717088*";
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    //Metodo para agregar reportes
    public void agregarReporte(Reporte reporte) {
        String sql = "INSERT INTO reportes (titulo, descripcion, fecha_generacion, generado_por) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmtQuery = conn.prepareStatement(sql)) {
            stmtQuery.setString(1, reporte.getTitulo());
            stmtQuery.setString(2, reporte.getDescripcion());
            stmtQuery.setTimestamp(3, reporte.getFechaGeneracion());
            stmtQuery.setInt(4, reporte.getGeneradoPor().getId());
            stmtQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Meotodo para listar
    public List<Reporte> listarReportes() {
        List<Reporte> lista = new ArrayList<>();
        String sql = "SELECT * FROM reportes";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setId(rs.getInt("id"));
                reporte.setTitulo(rs.getString("titulo"));
                reporte.setDescripcion(rs.getString("descripcion"));
                reporte.setFechaGeneracion(rs.getTimestamp("fecha_generacion"));
                Usuario generadoPor = new Usuario();
                generadoPor.setId(rs.getInt("generado_por"));
                reporte.setGeneradoPor(generadoPor);
                lista.add(reporte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Para listar
    public List<Reporte> buscarReportePorTituloReporte(String titulo) {
        List<Reporte> lista = new ArrayList<>();
        String sql = "SELECT * FROM reportes WHERE titulo like ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + titulo.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setId(rs.getInt("id"));
                reporte.setTitulo(rs.getString("titulo"));
                reporte.setDescripcion(rs.getString("descripcion"));
                lista.add(reporte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Metodo para actualizar
    public void actualizarReporte(Reporte reporte) {
        String sql = "UPDATE reportes SET titulo=?, descripcion=?, fecha_generacion=?  WHERE id=?";

        try (
                Connection conn = conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reporte.getTitulo());
            stmt.setString(2, reporte.getDescripcion());
            stmt.setTimestamp(3, reporte.getFechaGeneracion());
            stmt.setInt(4, reporte.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Metodo para eliminar reportes
    public void eliminarReporte(int id) {
        String sql = "DELETE FROM reportes WHERE id=?";
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
