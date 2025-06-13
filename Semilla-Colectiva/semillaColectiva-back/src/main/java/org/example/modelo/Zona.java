package org.example.modelo;

import java.math.BigDecimal;

public class Zona implements IRegistrable {
    private int id;
    private String nombre;
    private String descripcion;
    private BigDecimal latitud;
    private BigDecimal longitud;

    public Zona() {
    }

    public Zona(int id, String nombre, String descripcion, BigDecimal latitud, BigDecimal longitud) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    @Override
    public void registrar() {
        System.out.println("Zona registrada: " + nombre);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Zona: " + nombre + " - " + descripcion);
    }

    @Override
    public void actualizar() {

    }

    @Override
    public void eliminar() {

    }
}