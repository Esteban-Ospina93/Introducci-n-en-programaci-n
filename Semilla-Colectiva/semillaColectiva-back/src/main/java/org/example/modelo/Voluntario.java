package org.example.modelo;

public class Voluntario extends Participante {

    public Voluntario() {
        super();
    }

    public Voluntario(Integer id, String nombre, String apellido, String telefono) {
        super(id, nombre, apellido, telefono);
    }

    public Voluntario(Integer id, String nombre, String apellido, String telefono, Usuario usuario ) {
        super(id, nombre, apellido, telefono, usuario);
    }

    @Override
    public void registrar() {
        System.out.println("✅ Voluntario registrado: " + nombre + " " + apellido);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("👤 Voluntario: " + nombre + ", Apellido: " + apellido);
    }

    @Override
    public void actualizar() {
    }

    @Override
    public void eliminar() {

    }
}