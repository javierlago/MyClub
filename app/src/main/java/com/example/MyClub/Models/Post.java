package com.example.MyClub.Models;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    String titulo;
    String descripcion;

    Date fecha;
    ArrayList<Exercice> ejercicios = new ArrayList<Exercice>();

    public Post(String titulo, String descripcion, Date fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = new Date();
    }

    public Post(String titulo, ArrayList<Exercice> ejercicios, Date fecha) {
        this.titulo = titulo;
        this.ejercicios = ejercicios;
        this.fecha = new Date();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


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


    public ArrayList<Exercice> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Exercice> exercices) {
        this.ejercicios = exercices;
    }




}
