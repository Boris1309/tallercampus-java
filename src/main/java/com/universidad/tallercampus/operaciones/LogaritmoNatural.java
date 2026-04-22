package com.universidad.tallercampus.operaciones;

import com.universidad.tallercampus.abstracciones.OperacionUnaria;
import com.universidad.tallercampus.core.Resultado;
import com.universidad.tallercampus.core.Operando;

public class LogaritmoNatural extends OperacionUnaria {
    public LogaritmoNatural() {
        super("Logaritmo Natural");
    }

    @Override
    public Resultado ejecutar(Operando a) {
        if (a.getValor() <= 0) {
            return Resultado.error("Error abstracto: Condición para el logaritmo matemático insatisfecha.");
        }
        return Resultado.exito(Math.log(a.getValor()));
    }
}
