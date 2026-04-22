package com.universidad.tallercampus.operaciones;

import com.universidad.tallercampus.abstracciones.OperacionBinaria;
import com.universidad.tallercampus.core.Resultado;
import com.universidad.tallercampus.core.Operando;

public class Multiplicacion extends OperacionBinaria {
    public Multiplicacion() {
        super("Multiplicación");
    }

    @Override
    public Resultado ejecutar(Operando a, Operando b) {
        return Resultado.exito(a.getValor() * b.getValor());
    }
}
