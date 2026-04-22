package com.universidad.tallercampus.core;

public class Resultado {
    private final boolean exitoso;
    private final double valor;
    private final String mensajeError;

    private Resultado(boolean exitoso, double valor, String mensajeError) {
        this.exitoso = exitoso;
        this.valor = valor;
        this.mensajeError = mensajeError;
    }

    public static Resultado exito(double valor) {
        return new Resultado(true, valor, null);
    }

    public static Resultado error(String mensajeError) {
        return new Resultado(false, 0.0, mensajeError);
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public double getValor() {
        return valor;
    }

    public String getMensajeError() {
        return mensajeError;
    }
}
