package org.example.modelo;

import java.sql.Timestamp;

public class Reporte {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Timestamp fechaGeneracion;
    private Usuario generadoPor;

    public Reporte() {}

    public Reporte(Integer id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Reporte(Integer id, String titulo, String descripcion, Timestamp fechaGeneracion, Usuario generadoPor) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaGeneracion = fechaGeneracion;
        this.generadoPor = generadoPor;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Timestamp fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Usuario getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(Usuario generadoPor) {
        this.generadoPor = generadoPor;
    }
}

