package com.example.gymtaw.ui;

public class Filtro {
    private int idRol;
    private String nombre;
    private String apellido;
    private String dni;

    public Filtro(){

    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int nombreRol) {
        this.idRol = nombreRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean noFilter(){
        return nombre == null && apellido == null && idRol == 0 && dni == null;
    }
}
