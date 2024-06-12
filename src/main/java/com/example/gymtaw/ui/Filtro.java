package com.example.gymtaw.ui;

import java.util.List;

public class Filtro {
    private String nombreRol;
    private List<String> roles;

    public Filtro(){}

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
