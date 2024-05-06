package com.example.gymtaw.ui;

public class Filtro {
    protected String nombre;

    public Filtro() {
        this.nombre = "";
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public boolean estaVacio() {
        return nombre.isEmpty();
    }
}
