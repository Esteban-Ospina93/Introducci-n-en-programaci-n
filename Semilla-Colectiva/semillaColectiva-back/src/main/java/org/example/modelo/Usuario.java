package org.example.modelo;

import java.sql.Timestamp;

public class Usuario implements IRegistrable {
    private Integer id;
    private String nombre;
    private String correo;
    private String contrasena;
    private Rol rol;
    private Timestamp fechaRegistro;
    public enum Rol {
        ADMIN,
        VOLUNTARIO,
        PROFESIONAL,
        INSTITUCIONES
    }

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String contrasena, Rol rol, Timestamp fechaRegistro) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario(Integer id, String nombre, Rol rol, Timestamp fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario(Integer id, String nombre, String correo, String contrasena, Rol rol, Timestamp fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public void registrar() {

    }

    @Override
    public void actualizar() {

    }

    @Override
    public void eliminar() {

    }

    @Override
    public void mostrarInformacion() {

    }
}