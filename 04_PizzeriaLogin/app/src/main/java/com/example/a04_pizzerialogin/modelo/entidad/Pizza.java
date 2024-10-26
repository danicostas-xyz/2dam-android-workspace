package com.example.a04_pizzerialogin.modelo.entidad;

import java.util.ArrayList;

public class Pizza {
    private TamanoPizza tamanoPizza;
    private ArrayList<String> listaIngredientes;
    private int precio;

    public Pizza() {
    }

    public TamanoPizza getTamanoPizza() {
        return tamanoPizza;
    }

    public void setTamanoPizza(TamanoPizza tamanoPizza) {
        this.tamanoPizza = tamanoPizza;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ArrayList<String> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(ArrayList<String> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

}
