package org.example.dao;

import org.example.modelo.Arbol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArbolDao extends Arbol {
    //Realizar la conexión a la base de datos mySql
    //Credenciales
    private final String url = "jdbc:mysql://localhost:3306/db_semillacolectiva";
    private final String user = "root";
    private final String password = "Esteban1214717088*";
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    //Metodo para agregar árboles
    public void agregarArbol(Arbol arbol) {
        String sql = "INSERT INTO arboles (nombre_arbol, etapa, estado, fecha_registro) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmtQuery = conn.prepareStatement(sql)) {
            stmtQuery.setString(1, arbol.getNombreArbol());
            stmtQuery.setString(2, arbol.getEtapa().name().toUpperCase());
            stmtQuery.setString(3, arbol.getEstado().name().toUpperCase());
            stmtQuery.setTimestamp(4, arbol.getFechaRegistro());
            stmtQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Meotodo para listar
    public List<Arbol> listarArbol() {
        List<Arbol> lista = new ArrayList<>();
        String sql = "SELECT * FROM arboles";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Arbol arbol = new Arbol();
                arbol.setId(rs.getInt("id"));
                arbol.setNombreArbol(rs.getString("nombre_arbol"));
                arbol.setEtapa(Etapa.valueOf(rs.getString("etapa").toUpperCase()));
                arbol.setEstado(Estado.valueOf(rs.getString("estado").toUpperCase()));
                arbol.setFechaRegistro(rs.getTimestamp("fecha_registro"));

                lista.add(arbol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Para listar
    public List<Arbol> buscarArbolPorNombreArbol(String nombreArbol) {
        List<Arbol> lista = new ArrayList<>();
        String sql = "SELECT * FROM arboles WHERE nombre_arbol = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreArbol);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Arbol arbol = new Arbol();
                arbol.setId(rs.getInt("id"));
                arbol.setNombreArbol(rs.getString("nombre_arbol"));
                arbol.setEtapa(Etapa.valueOf(rs.getString("etapa").toUpperCase()));
                arbol.setEstado(Estado.valueOf(rs.getString("estado").toUpperCase()));
                arbol.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                lista.add(arbol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Metodo para actualizar
    public void actualizarArbol(Arbol arbol) {
        String sql = "UPDATE arboles SET nombre_arbol=?, etapa=?, estado=?, fecha_registro=? WHERE id=?";

        try (
                Connection conn = conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, arbol.getNombreArbol());
            stmt.setString(2, arbol.getEtapa().name().toUpperCase());
            stmt.setString(3, arbol.getEstado().name().toUpperCase());
            stmt.setTimestamp(4, arbol.getFechaRegistro());
            stmt.setInt(5, arbol.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Metodo para eliminar árboles
    public void eliminarArbol(int id) {
        String sql = "DELETE FROM arboles WHERE id=?";
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
