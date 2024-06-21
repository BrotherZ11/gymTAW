package com.example.gymtaw.ui;

public class FiltroRutina {
    protected String nombre;

    public FiltroRutina() {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean estaVacio(){
        return nombre.isEmpty();
    }
}
