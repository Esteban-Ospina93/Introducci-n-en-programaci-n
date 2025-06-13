package org.example.dao;

import org.example.modelo.Zona;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZonaDao {
    //Realizar la conexión a la base de datos mySql
    //Credenciales
    private final String url = "jdbc:mysql://localhost:3306/db_semillacolectiva";
    private final String user = "root";
    private final String password = "Esteban1214717088*";
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //Metodo para agregar zonas
    public void agregarZona(Zona zona) {
        String sql = "INSERT INTO zonas (nombre, descripcion, latitud, longitud ) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmtQuery = conn.prepareStatement(sql)) {
            stmtQuery.setString(1, zona.getNombre());
            stmtQuery.setString(2, zona.getDescripcion());
            stmtQuery.setBigDecimal(3, zona.getLatitud());
            stmtQuery.setBigDecimal(4, zona.getLongitud());
            stmtQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Meotodo para listar
    public List<Zona> listarZonas() {
        List<Zona> lista = new ArrayList<>();
        String sql = "SELECT * FROM zonas";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Zona zona = new Zona();
                zona.setId(rs.getInt("id"));
                zona.setNombre(rs.getString("nombre"));
                zona.setDescripcion(rs.getString("descripcion"));
                zona.setLatitud(rs.getBigDecimal("latitud"));
                zona.setLongitud(rs.getBigDecimal("longitud"));

                lista.add(zona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Para listar
    public List<Zona> buscarZonaPorNombreZona(String nombre) {
        List<Zona> lista = new ArrayList<>();
        String sql = "SELECT * FROM zonas WHERE nombre = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Zona zona = new Zona();
                zona.setId(rs.getInt("id"));
                zona.setNombre(rs.getString("nombre"));
                zona.setDescripcion(rs.getString("descripcion"));
                zona.setLatitud(rs.getBigDecimal("latitud"));
                zona.setLongitud(rs.getBigDecimal("longitud"));

                lista.add(zona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    //Metodo para actualizar
    public void actualizarZona(Zona zona) {
        String sql = "UPDATE zonas SET nombre=?, descripcion=?, latitud=?, longitud=? WHERE id=?";

        try (
                Connection conn = conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, zona.getNombre());
            stmt.setString(2, zona.getDescripcion());
            stmt.setBigDecimal(3, zona.getLatitud());
            stmt.setBigDecimal(4, zona.getLongitud());
            stmt.setInt(5, zona.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Metodo para eliminar zonas
    public void eliminarZona(int id) {
        String sql = "DELETE FROM zonas WHERE id=?";
        try (
                Connection conn = conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Conversor de grados a decimal
    public class ConvertirCoordenadas {

        private static final Pattern DMS_PATTERN = Pattern.compile(
                "(\\d+)°(\\d+)'(\\d+(\\.\\d+)?)\"?([NSEO])",
                Pattern.CASE_INSENSITIVE
        );

        public static BigDecimal convertirDmsADecimal(String dms) {
            Matcher matcher = DMS_PATTERN.matcher(dms.trim());

            if (!matcher.matches()) {
                throw new IllegalArgumentException("Formato DMS inválido: " + dms);
            }

            int grados = Integer.parseInt(matcher.group(1));
            int minutos = Integer.parseInt(matcher.group(2));
            double segundos = Double.parseDouble(matcher.group(3));
            String direccion = matcher.group(5).toUpperCase();

            double decimal = grados + (minutos / 60.0) + (segundos / 3600.0);
            if (direccion.equals("S") || direccion.equals("0")) {
                decimal *= -1;
            }
            return BigDecimal.valueOf(decimal).setScale(6, BigDecimal.ROUND_HALF_UP); //El ROUND sirve para redondear números
        }
    }
}
