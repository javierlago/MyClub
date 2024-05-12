package com.example.MyClub.models;

public class User {


    public User() {
    }

    private String name;
    private String apellido;
    private String apellidosegundo;

    private String categoria;

    private String telefono;
    private String email;
    private String password;
    private String rol;
    private Float peso;
    private Float talla;
    private String fechaNacimiento;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getApellidosegundo() {
        return apellidosegundo;
    }

    public void setApellidosegundo(String apellidosegundo) {
        this.apellidosegundo = apellidosegundo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getTalla() {
        return talla;
    }

    public void setTalla(Float talla) {
        this.talla = talla;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
