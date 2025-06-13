package org.example.modelo;

import java.sql.Timestamp;

public class HistorialArbol {
    private Integer id;
    private Arbol arbol;
    private Timestamp fechaCambio;
    private String estadoAnterior;
    private String estadoNuevo;
    private Usuario usuario;

    public HistorialArbol() {
    }

    public HistorialArbol(Integer id, Arbol arbol, Timestamp fechaCambio, String estadoAnterior, String estadoNuevo, Usuario usuario) {
        this.id = id;
        this.arbol = arbol;
        this.fechaCambio = fechaCambio;
        this.estadoAnterior = estadoAnterior;
        this.estadoNuevo = estadoNuevo;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

    public Timestamp getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Timestamp fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(String estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}