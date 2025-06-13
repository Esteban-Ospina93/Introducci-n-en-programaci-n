package org.example.menu;

import java.util.ArrayList;
import java.util.Scanner;

import org.example.modelo.*;
import org.example.dao.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

public class MenuGestor {

    private final Scanner sc;
    private final ArbolDao arbolDao;
    private final UsuarioDao usuarioDao;
    private final VoluntarioDao voluntarioDao;
    private final ReporteDao reporteDao;
    private final ZonaDao zonaDao;
    private final HistorialArbolDao historialArbolDao;

    public MenuGestor(Scanner sc) {
        this.sc = sc;
        this.arbolDao = new ArbolDao();
        this.usuarioDao = new UsuarioDao();
        this.voluntarioDao = new VoluntarioDao();
        this.reporteDao = new ReporteDao();
        this.zonaDao = new ZonaDao();
        this.historialArbolDao = new HistorialArbolDao();
    }

    public void mostrarMenuPrincipal() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar Árboles");
            System.out.println("2. Gestionar Usuarios");
            System.out.println("3. Gestionar Voluntarios");
            System.out.println("4. Gestionar Reportes");
            System.out.println("5. Gestionar Zonas");
            System.out.println("6. Consultar Historial de Árboles");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> menuArbol();
                case 2 -> menuUsuario();
                case 3 -> menuVoluntario();
                case 4 -> menuReporte();
                case 5 -> menuZona();
                case 6 -> menuHistorialArbol();
                case 7 -> {
                    System.out.println("¡Hasta luego!");
                    salir = true;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    //Métodos vacíos llenados por la persona
    private void menuArbol() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE ÁRBOLES ---");
            System.out.println("1. Agregar");
            System.out.println("2. Consultar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar");
            System.out.println("6. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del árbol: ");
                    String nombre = sc.nextLine();
                    System.out.println("Ciclo de vida: ");
                    Arbol.Etapa etapa = Arbol.Etapa.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("En qué estado se encuentra: ");
                    Arbol.Estado estado = Arbol.Estado.valueOf(sc.nextLine().toUpperCase());
                    Timestamp fechaRegistro = Timestamp.valueOf(LocalDateTime.now());
                    arbolDao.agregarArbol(new Arbol(nombre, etapa, estado, fechaRegistro));
                }
                case 2 -> {
                    System.out.print("Nombre a consultar: ");
                    String nombre = sc.nextLine();
                    arbolDao.buscarArbolPorNombreArbol(nombre).forEach(a ->
                            System.out.println("ID: " + a.getId() + " | Nombre: " + a.getNombreArbol() +
                                    " | Etapa: " + a.getEtapa() + " | Estado: " + a.getEstado() + " | Fecha: " + a.getFechaRegistro()));
                }
                case 3 -> {
                    System.out.print("ID del árbol: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Nueva etapa: ");
                    Arbol.Etapa etapa = Arbol.Etapa.valueOf(sc.nextLine().toUpperCase());
                    System.out.print("Nuevo estado: ");
                    Arbol.Estado estado = Arbol.Estado.valueOf(sc.nextLine().toUpperCase());
                    Timestamp fechaRegistro = Timestamp.valueOf(LocalDateTime.now());
                    arbolDao.actualizarArbol(new Arbol(id, nombre, etapa, estado, fechaRegistro));
                }
                case 4 -> {
                    System.out.print("ID a eliminar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    arbolDao.eliminarArbol(id);
                }
                case 5 -> arbolDao.listarArbol().forEach(a ->
                        System.out.println("ID: " + a.getId() + " | Nombre: " + a.getNombreArbol() + " | Etapa: " + a.getEtapa() +
                                " | Estado: " + a.getEstado() + " | Fecha: " + a.getFechaRegistro()));
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    }

    public void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Consultar por nombre");
            System.out.println("3. Actualizar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Listar todos los usuarios");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String contrasena = sc.nextLine();
                    System.out.print("Rol (ADMIN/PROFESIONAL): ");
                    Usuario.Rol rol = Usuario.Rol.valueOf(sc.nextLine().toUpperCase());
                    Timestamp fechaRegistro = Timestamp.valueOf(LocalDateTime.now());

                    Usuario nuevo = new Usuario(nombre, correo, contrasena, rol, fechaRegistro);
                    usuarioDao.agregarUsuario(nuevo);
                    System.out.println("✔ Usuario agregado correctamente.");
                }
                case 2 -> {
                    System.out.print("🔍 Nombre del usuario a buscar: ");
                    String nombre = sc.nextLine();
                    List<Usuario> encontrados = usuarioDao.buscarUsuarioPorNombreUsuario(nombre);
                    if (encontrados.isEmpty()) {
                        System.out.println("❌ Usuario no encontrado.");
                    } else {
                        encontrados.forEach(u ->
                                System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombre() +
                                        " | Rol: " + u.getRol() + " | Registro: " + u.getFechaRegistro()));
                    }
                }
                case 3 -> {
                    System.out.print("ID del usuario a actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevo correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Nueva contraseña: ");
                    String contrasena = sc.nextLine();
                    System.out.print("Nuevo rol (ADMIN/USUARIO): ");
                    Usuario.Rol rol = Usuario.Rol.valueOf(sc.nextLine().toUpperCase());
                    Timestamp fechaRegistro = Timestamp.valueOf(LocalDateTime.now());

                    Usuario actualizado = new Usuario(id, nombre, correo, contrasena, rol, fechaRegistro);
                    usuarioDao.actualizarUsuario(actualizado);
                    System.out.println("✔ Usuario actualizado correctamente.");
                }
                case 4 -> {
                    System.out.print("ID del usuario a eliminar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    usuarioDao.eliminarUsuario(id);
                    System.out.println("🗑 Usuario eliminado correctamente.");
                }
                case 5 -> {
                    List<Usuario> usuarios = usuarioDao.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("📭 No hay usuarios registrados.");
                    } else {
                        usuarios.forEach(u ->
                                System.out.println("ID: " + u.getId() + " | Nombre: " + u.getNombre() +
                                        " | Correo: " + u.getCorreo() + " | Rol: " + u.getRol()));
                    }
                }
                case 6 -> System.out.println("↩ Volviendo al menú principal...");
                default -> System.out.println("⚠ Opción inválida.");
            }
        } while (opcion != 6);
    }


    private void menuVoluntario() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE VOLUNTARIOS ---");
            System.out.println("1. Agregar Voluntario");
            System.out.println("2. Consultar por nombre");
            System.out.println("3. Actualizar Voluntario");
            System.out.println("4. Eliminar Voluntario");
            System.out.println("5. Listar todos los voluntarios");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("ID del usuario relacionado: ");
                    int idUsuario = sc.nextInt();
                    sc.nextLine();

                    Usuario usuario = new Usuario();
                    usuario.setId(idUsuario);

                    Participante voluntario = new Voluntario();
                    voluntario.setNombre(nombre);
                    voluntario.setApellido(apellidos);
                    voluntario.setTelefono(telefono);
                    voluntario.setUsuario(usuario);

                    voluntarioDao.agregarVoluntario(voluntario);
                    System.out.println("✔ Voluntario agregado correctamente.");
                }
                case 2 -> {
                    System.out.print("🔍 Nombre del voluntario: ");
                    String nombreBuscar = sc.nextLine();
                    List<Voluntario> encontrados = voluntarioDao.buscarVoluntarioPorNombreVoluntario(nombreBuscar);
                    if (encontrados.isEmpty()) {
                        System.out.println("❌ Voluntario no encontrado.");
                    } else {
                        encontrados.forEach(v ->
                                System.out.println("id: " + v.getId() + " | Nombre: " + v.getNombre()
                                        + " | Apellidos " + v.getApellido() + " | Télefono: " + v.getTelefono()
                                        + " | Usuario_id: " + v.getUsuario().getId()));
                    }
                }
                case 3 -> {
                    System.out.print("ID del voluntario a actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevos apellidos: ");
                    String apellidos = sc.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String telefono = sc.nextLine();

                    Voluntario voluntario = new Voluntario();
                    voluntario.setId(id);
                    voluntario.setNombre(nombre);
                    voluntario.setApellido(apellidos);
                    voluntario.setTelefono(telefono);

                    voluntarioDao.actualizarVoluntario(voluntario);
                    System.out.println("✔ Voluntario actualizado correctamente.");
                }
                case 4 -> {
                    System.out.print("ID del voluntario a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    voluntarioDao.eliminarVoluntario(idEliminar);
                    System.out.println("🗑 Voluntario eliminado correctamente.");
                }
                case 5 -> {
                    List<Participante> lista = voluntarioDao.listarVoluntario();
                    if (lista.isEmpty()) {
                        System.out.println("📭 No hay voluntarios registrados.");
                    } else {
                        for (Participante v : lista) {
                            System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombre()
                                    + " " + v.getApellido() + " | Teléfono: " + v.getTelefono()
                                    + " | ID Usuario: " + v.getUsuario().getId());
                        }
                    }
                }
                case 6 -> System.out.println("↩ Volviendo al menú principal...");
                default -> System.out.println("⚠ Opción inválida.");
            }
        } while (opcion != 6);
    }

    private void menuReporte() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE REPORTES ---");
            System.out.println("1. Agregar Reporte");
            System.out.println("2. Consultar por título");
            System.out.println("3. Actualizar Reporte");
            System.out.println("4. Eliminar Reporte");
            System.out.println("5. Listar todos los reportes");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();
                    System.out.print("ID del usuario que genera el reporte: ");
                    int idUsuario = sc.nextInt();
                    sc.nextLine();

                    Usuario usuario = new Usuario();
                    usuario.setId(idUsuario);

                    Reporte reporte = new Reporte();
                    reporte.setTitulo(titulo);
                    reporte.setDescripcion(descripcion);
                    reporte.setFechaGeneracion(new Timestamp(System.currentTimeMillis()));
                    reporte.setGeneradoPor(usuario);

                    reporteDao.agregarReporte(reporte);
                    System.out.println("✔ Reporte agregado correctamente.");
                }
                case 2 -> {
                    System.out.print("🔍 Título del reporte: ");
                    String tituloBuscar = sc.nextLine();
                    List<Reporte> encontrados = reporteDao.buscarReportePorTituloReporte(tituloBuscar);
                    if (encontrados.isEmpty()) {
                        System.out.println("❌ No se encontró ningún reporte con ese título.");
                    } else {
                        encontrados.forEach(r ->
                                System.out.println("ID: " + r.getId() + " | Título: " + r.getTitulo()
                                        + " | Descripción: " + r.getDescripcion()
                                        + " | Fecha: " + r.getFechaGeneracion()));
                    }
                }
                case 3 -> {
                    System.out.print("ID del reporte a actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Nueva descripción: ");
                    String descripcion = sc.nextLine();

                    Reporte reporte = new Reporte();
                    reporte.setId(id);
                    reporte.setTitulo(titulo);
                    reporte.setDescripcion(descripcion);
                    reporte.setFechaGeneracion(new Timestamp(System.currentTimeMillis()));

                    reporteDao.actualizarReporte(reporte);
                    System.out.println("✔ Reporte actualizado correctamente.");
                }
                case 4 -> {
                    System.out.print("ID del reporte a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    reporteDao.eliminarReporte(idEliminar);
                    System.out.println("🗑 Reporte eliminado correctamente.");
                }
                case 5 -> {
                    List<Reporte> lista = reporteDao.listarReportes();
                    if (lista.isEmpty()) {
                        System.out.println("📭 No hay reportes registrados.");
                    } else {
                        for (Reporte r : lista) {
                            System.out.println("ID: " + r.getId() + " | Título: " + r.getTitulo()
                                    + " | Descripción: " + r.getDescripcion()
                                    + " | Fecha: " + r.getFechaGeneracion()
                                    + " | Generado por (usuario ID): " + r.getGeneradoPor().getId());
                        }
                    }
                }
                case 6 -> System.out.println("↩ Volviendo al menú principal...");
                default -> System.out.println("⚠ Opción inválida.");
            }
        } while (opcion != 6);
    }

    private void menuZona() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE ZONAS ---");
            System.out.println("1. Agregar Zona");
            System.out.println("2. Consultar por nombre");
            System.out.println("3. Actualizar Zona");
            System.out.println("4. Eliminar Zona");
            System.out.println("5. Listar todas las zonas");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    try {
                        System.out.print("Latitud (formato DMS, ej: 4°09'36.2\"S): ");
                        String latDms = sc.nextLine();
                        BigDecimal latitud = ZonaDao.ConvertirCoordenadas.convertirDmsADecimal(latDms);

                        System.out.print("Longitud (formato DMS, ej: 69°56'26.0\"0): ");
                        String lonDms = sc.nextLine();
                        BigDecimal longitud = ZonaDao.ConvertirCoordenadas.convertirDmsADecimal(lonDms);

                        Zona zona = new Zona();
                        zona.setNombre(nombre);
                        zona.setDescripcion(descripcion);
                        zona.setLatitud(latitud);
                        zona.setLongitud(longitud);

                        zonaDao.agregarZona(zona);
                        System.out.println("✔ Zona agregada correctamente.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Error al convertir coordenadas: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("🔍 Nombre de la zona: ");
                    String nombreBuscar = sc.nextLine();
                    List<Zona> encontrados = zonaDao.buscarZonaPorNombreZona(nombreBuscar);
                    if (encontrados.isEmpty()) {
                        System.out.println("❌ No se encontró ninguna zona con ese nombre.");
                    } else {
                        for (Zona z : encontrados) {
                            System.out.println("ID: " + z.getId() + " | Nombre: " + z.getNombre()
                                    + " | Descripción: " + z.getDescripcion()
                                    + " | Lat: " + z.getLatitud()
                                    + " | Long: " + z.getLongitud());
                        }
                    }
                }
                case 3 -> {
                    System.out.print("ID de la zona a actualizar: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nuevo nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nueva descripción: ");
                    String descripcion = sc.nextLine();
                    System.out.print("Nueva latitud: ");
                    BigDecimal latitud = sc.nextBigDecimal();
                    System.out.print("Nueva longitud: ");
                    BigDecimal longitud = sc.nextBigDecimal();
                    sc.nextLine();

                    Zona zona = new Zona();
                    zona.setId(id);
                    zona.setNombre(nombre);
                    zona.setDescripcion(descripcion);
                    zona.setLatitud(latitud);
                    zona.setLongitud(longitud);

                    zonaDao.actualizarZona(zona);
                    System.out.println("✔ Zona actualizada correctamente.");
                }
                case 4 -> {
                    System.out.print("ID de la zona a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    zonaDao.eliminarZona(idEliminar);
                    System.out.println("🗑 Zona eliminada correctamente.");
                }
                case 5 -> {
                    List<Zona> lista = zonaDao.listarZonas();
                    if (lista.isEmpty()) {
                        System.out.println("📭 No hay zonas registradas.");
                    } else {
                        for (Zona z : lista) {
                            System.out.println("ID: " + z.getId() + " | Nombre: " + z.getNombre()
                                    + " | Descripción: " + z.getDescripcion()
                                    + " | Lat: " + z.getLatitud()
                                    + " | Long: " + z.getLongitud());
                        }
                    }
                }
                case 6 -> System.out.println("↩ Volviendo al menú principal...");
                default -> System.out.println("⚠ Opción inválida.");
            }
        } while (opcion != 6);
    }

    private void menuHistorialArbol() {
        Scanner sc = new Scanner(System.in);
        HistorialArbolDao dao = new HistorialArbolDao();

        System.out.print("Ingrese el ID del árbol: ");
        int arbolId = sc.nextInt();

        List<HistorialArbol> historial = dao.obtenerHistorialPorArbolId(arbolId);

        if (historial.isEmpty()) {
            System.out.println("No se encontró historial.");
        } else {
            for (HistorialArbol h : historial) {
                System.out.println("Fecha: " + h.getFechaCambio());
                System.out.println("Estado anterior: " + h.getEstadoAnterior());
                System.out.println("Estado nuevo: " + h.getEstadoNuevo());
                System.out.println("Usuario: " + h.getUsuario().getNombre());
                System.out.println("----");
            }
        }
    }
    private void mostrarHistorialPorArbol() {
        System.out.print("Ingrese el ID del árbol: ");
        int arbolId = sc.nextInt();
        sc.nextLine();

        List<HistorialArbol> historial = historialArbolDao.obtenerHistorialPorArbolId(arbolId);

        if (historial.isEmpty()) {
            System.out.println("📭 No se encontraron registros de historial para ese árbol.");
        } else {
            System.out.println("\n📚 Historial del árbol ID " + arbolId + ":");
            for (HistorialArbol h : historial) {
                System.out.println("- Fecha: " + h.getFechaCambio()
                        + " | De: " + h.getEstadoAnterior()
                        + " → A: " + h.getEstadoNuevo()
                        + " | Registrado por: " + h.getUsuario().getNombre());
            }
        }
    }
}


