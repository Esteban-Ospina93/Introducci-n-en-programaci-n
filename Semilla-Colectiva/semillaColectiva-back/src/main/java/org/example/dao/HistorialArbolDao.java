package org.example.dao;

import org.example.modelo.Arbol;
import org.example.modelo.HistorialArbol;
import org.example.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialArbolDao {
    //Realizar la conexión a la base de datos mySql
    //Credenciales
    private final String url = "jdbc:mysql://localhost:3306/db_semillacolectiva";
    private final String user = "root";
    private final String password = "Esteban1214717088*";
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //Metodo para el historial de arboles
    public void registrarCambioEstado(HistorialArbol historial) {
        String sql = "INSERT INTO historial_arboles (arbol_id, fecha_cambio, estado_anterior, estado_nuevo, usuario_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, historial.getArbol().getId());
            stmt.setTimestamp(2, historial.getFechaCambio());
            stmt.setString(3, historial.getEstadoAnterior());
            stmt.setString(4, historial.getEstadoNuevo());
            stmt.setInt(5, historial.getUsuario().getId());

            stmt.executeUpdate();
            System.out.println("✔ Historial registrado correctamente.");

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar historial: " + e.getMessage());
        }
    }
    public List<HistorialArbol> obtenerHistorialPorArbolId(int arbolId) {
        List<HistorialArbol> lista = new ArrayList<>();

        String sql = """
        SELECT h.id, h.fecha_cambio, h.estado_anterior, h.estado_nuevo, 
               u.id AS usuario_id, u.nombre
        FROM historial_arboles h
        JOIN usuarios u ON h.usuario_id = u.id
        WHERE h.arbol_id = ?
        ORDER BY h.fecha_cambio DESC
    """;

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, arbolId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistorialArbol h = new HistorialArbol();
                h.setId(rs.getInt("id"));
                h.setFechaCambio(rs.getTimestamp("fecha_cambio"));
                h.setEstadoAnterior(rs.getString("estado_anterior"));
                h.setEstadoNuevo(rs.getString("estado_nuevo"));

                Usuario u = new Usuario();
                u.setId(rs.getInt("usuario_id"));
                u.setNombre(rs.getString("nombre"));
                h.setUsuario(u);

                lista.add(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
