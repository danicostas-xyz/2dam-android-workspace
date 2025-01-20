package xyz.danicostas.a06_recyclerview.entidad;

import java.io.Serializable;

public class Videojuego implements Serializable {
    private int id;
    private String nombre;
    private String compania;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Videojuego() {
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", compania='" + compania + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
