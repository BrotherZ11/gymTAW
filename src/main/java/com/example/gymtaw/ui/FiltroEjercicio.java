package com.example.gymtaw.ui;

import lombok.Getter;
import lombok.Setter;

public class FiltroEjercicio {
    protected String nombre;
    @Getter @Setter
    private String descripcion;
    @Getter@Setter
    private String URL;
    @Getter @Setter
    private Integer idTipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
