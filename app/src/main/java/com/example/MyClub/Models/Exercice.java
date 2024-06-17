package com.example.MyClub.Models;

public class Exercice {
    String nombre, descripcionUnidades, intensidad;
    int serie, unidades, postId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionUnidades() {
        return descripcionUnidades;
    }

    public void setDescripcionUnidades(String descripcionUnidades) {
        this.descripcionUnidades = descripcionUnidades;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public int getSeries() {
        return serie;
    }

    public void setSeries(int serie) {
        this.serie = serie;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }


}
