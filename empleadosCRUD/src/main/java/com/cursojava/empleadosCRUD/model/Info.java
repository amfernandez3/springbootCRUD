package com.cursojava.empleadosCRUD.model;

public class Info {

    private String nombre;
    private String correo;
    private int anios;

    public Info(String nombre, String correo, int anios) {
        this.nombre = nombre;
        this.correo = correo;
        this.anios = anios;
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

    public int getAnios() {
        return anios;
    }

    public void setAnios(int anios) {
        this.anios = anios;
    }
}
