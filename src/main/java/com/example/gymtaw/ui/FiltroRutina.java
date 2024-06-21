package com.example.gymtaw.ui;

import com.example.gymtaw.entity.TypeEntity;

import java.util.Set;

public class FiltroRutina {
    protected String nombre;

    protected Set<TypeEntity> tipos;

    protected Integer idEntrenador;

    public FiltroRutina() {

        this.nombre = "";
        this.tipos = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<TypeEntity> getTipos() {
        return tipos;
    }

    public void setTipos(Set<TypeEntity> tipos) {
        this.tipos = tipos;
    }

    public boolean estaVacioTipos(){
        return tipos==null || tipos.isEmpty();
    }

    public Integer getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Integer idEntrenador) {
        this.idEntrenador = idEntrenador;
    }
}
