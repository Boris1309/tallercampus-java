package com.universidad.tallercampus.core;

import com.universidad.tallercampus.abstracciones.OperacionBinaria;
import com.universidad.tallercampus.abstracciones.OperacionUnaria;

public class Calculadora {
    public Resultado calcular(OperacionBinaria operacion, Operando a, Operando b) {
        return operacion.ejecutar(a, b);
    }

    public Resultado calcular(OperacionUnaria operacion, Operando a) {
        return operacion.ejecutar(a);
    }
}
