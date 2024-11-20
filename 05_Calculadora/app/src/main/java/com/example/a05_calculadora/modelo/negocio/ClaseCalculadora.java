package com.example.a05_calculadora.modelo.negocio;

public class ClaseCalculadora {

    static ClaseCalculadora claseCalculadora = null;
    private ClaseCalculadora() {}

    public static ClaseCalculadora getInstance() {
        return (claseCalculadora == null) ? new ClaseCalculadora() : claseCalculadora;
    }

    // Suma de dos números
    public double sumar(double numero1, double numero2) {
        return numero1 + numero2;
    }

    // Resta de dos números
    public double restar(double numero1, double numero2) {
        return numero1 - numero2;
    }

    // Multiplicación de dos números
    public double multiplicar(double numero1, double numero2) {
        return numero1 * numero2;
    }

    // División de dos números
    public double dividir(double numero1, double numero2) {
        if (numero2 == 0) {
            throw new ArithmeticException("Error: No se puede dividir entre cero.");
        }
        return numero1 / numero2;
    }

    // Potencia (base elevada al exponente)
    public double potencia(double base, double exponente) {
        return Math.pow(base, exponente);
    }

    // Raíz cuadrada
    public double raizCuadrada(double numero) {
        if (numero < 0) {
            throw new ArithmeticException("Error: No se puede calcular la raíz cuadrada de un número negativo.");
        }
        return Math.sqrt(numero);
    }

    // Módulo (resto de la división)
    public double modulo(double numero1, double numero2) {
        if (numero2 == 0) {
            throw new ArithmeticException("Error: No se puede calcular el módulo con divisor cero.");
        }
        return numero1 % numero2;
    }

    // Factorial de un número (sólo para números enteros no negativos)
    public long factorial(int numero) {
        if (numero < 0) {
            throw new ArithmeticException("Error: No se puede calcular el factorial de un número negativo.");
        }
        long resultado = 1;
        for (int i = 1; i <= numero; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // Conversión de radianes a grados
    public double radianesAGrados(double radianes) {
        return Math.toDegrees(radianes);
    }

    // Conversión de grados a radianes
    public double gradosARadianes(double grados) {
        return Math.toRadians(grados);
    }

}




