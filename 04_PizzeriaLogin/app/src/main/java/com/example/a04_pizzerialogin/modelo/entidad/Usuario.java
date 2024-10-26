package com.example.a04_pizzerialogin.modelo.entidad;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private String nombre;
    private String pass;
    private Pizza pizza;
    private String direccion;

    public Usuario() {

    }

    public Usuario(String direccion, String pass, String nombre) {
        this.nombre = nombre;
        this.pass = pass;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) && Objects.equals(pass, usuario.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, pass);
    }
}
