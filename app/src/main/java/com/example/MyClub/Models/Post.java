package com.example.MyClub.Models;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    String titulo;
    String descripcion;

    Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    ArrayList<Ejercicio> ejercicios = new ArrayList<Ejercicio>();

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
