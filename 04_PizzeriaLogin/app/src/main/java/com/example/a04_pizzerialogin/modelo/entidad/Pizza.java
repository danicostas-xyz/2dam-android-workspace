package com.example.a04_pizzerialogin.modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;

public class Pizza implements Serializable {
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

    public String getStringListaIngredientes() {
        if (listaIngredientes.isEmpty()) {
            return ""; // Devuelve una cadena vacía si no hay ingredientes
        }

        StringBuilder lista = new StringBuilder();

        for (int i = 0; i < listaIngredientes.size(); i++) {
            lista.append(listaIngredientes.get(i));
            if (i < listaIngredientes.size() - 1) {
                lista.append(", ");
            }
        }

        lista.append("."); // Añadir un punto al final

        return lista.toString();
    }

    public void setListaIngredientes(ArrayList<String> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

}
