package com.example.a05_calculadora.modelo.negocio;

public class ClaseCalculadora {

    static ClaseCalculadora claseCalculadora = null;
    private ClaseCalculadora() {}

    public ClaseCalculadora getInstance() {
        return (claseCalculadora == null) ? new ClaseCalculadora() : claseCalculadora;
    }

}
