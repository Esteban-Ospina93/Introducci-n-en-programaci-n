package org.example.modelo;

import java.sql.Timestamp;

public class Arbol implements IRegistrable {
    private Integer id;
    private String nombreArbol;
    private Timestamp fechaRegistro;
    private Zona zona;
    private Voluntario voluntario;
    public enum Etapa {
        GERMINACION,
        CRECIMIENTO,
        MADUREZ,
        SENESCENCIA,
    };
    private Etapa etapa;
    public enum Estado {
        SALUDABLE,
        ENFERMO,
        RIESGO,
        TRANSPLANTADO
    }
    private Estado estado;

    public Arbol() {
    }

    public Arbol(Integer id, String nombreArbol, Etapa etapa, Estado estado, Timestamp fechaRegistro) {
        this.id = id;
        this.nombreArbol = nombreArbol;
        this.fechaRegistro = fechaRegistro;
        this.etapa = etapa;
        this.estado = estado;
    }

    public Arbol(String nombreArbol, Etapa etapa, Estado estado, Timestamp fechaRegistro) {
        this.nombreArbol = nombreArbol;
        this.etapa = etapa;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Arbol(Integer id, String nombreArbol, Timestamp fechaRegistro, Zona zona, Voluntario voluntario, Estado estado) {
        this.id = id;
        this.nombreArbol = nombreArbol;
        this.fechaRegistro = fechaRegistro;
        this.zona = zona;
        this.voluntario = voluntario;
        this.estado =estado;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreArbol() {
        return nombreArbol;
    }

    public void setNombreArbol(String nombreArbol) {
        this.nombreArbol = nombreArbol;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public void registrar() {
        System.out.println("🌱 Árbol registrado: " + nombreArbol + " en zona " + zona.getNombre());
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("🌳 Árbol: " + nombreArbol + ", Plantado por: " + voluntario.getNombre());
    }

    @Override
    public void actualizar() {

    }

    @Override
    public void eliminar() {

    }

}