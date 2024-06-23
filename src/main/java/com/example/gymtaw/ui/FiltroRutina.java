// David Zarzavilla Borrego 50%, David Molina Lopez 50%
package com.example.gymtaw.ui;

import com.example.gymtaw.dto.Type;

import java.util.Set;

public class FiltroRutina {
    protected String nombre;

    protected Set<Integer> tipos;

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

    public Set<Integer> getTipos() {
        return tipos;
    }

    public void setTipos(Set<Integer> tipos) {
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
