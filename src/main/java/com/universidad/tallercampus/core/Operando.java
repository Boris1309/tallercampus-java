package com.universidad.tallercampus.core;

/**
 * Entidad / Value Object del Dominio que encapsula los números enviados a operar.
 * Garantiza mediante sus propias reglas internas que los valores sean válidos (no NaN, ni Infinito).
 * Esto permite aislar y proteger el motor completo en lugar de pasar tipos primitivos desnudos (int, double).
 */
public class Operando {
    private final double valor;

    public Operando(double valor) {
        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new IllegalArgumentException("Violación de Regla de Negocio: El operando no es un número real válido.");
        }
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
