package com.universidad.tallercampus.operaciones;

import com.universidad.tallercampus.abstracciones.OperacionUnaria;
import com.universidad.tallercampus.core.Resultado;
import com.universidad.tallercampus.core.Operando;

public class RaizCuadrada extends OperacionUnaria {
    public RaizCuadrada() {
        super("Raíz Cuadrada");
    }

    @Override
    public Resultado ejecutar(Operando a) {
        if (a.getValor() < 0) {
            return Resultado.error("Error abstracto: Raíz cuadrada de número negativo.");
        }
        return Resultado.exito(Math.sqrt(a.getValor()));
    }
}
