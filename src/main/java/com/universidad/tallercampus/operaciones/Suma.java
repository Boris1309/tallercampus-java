package com.universidad.tallercampus.operaciones;

import com.universidad.tallercampus.abstracciones.OperacionBinaria;
import com.universidad.tallercampus.core.Resultado;
import com.universidad.tallercampus.core.Operando;

public class Suma extends OperacionBinaria {
    public Suma() {
        super("Suma");
    }

    @Override
    public Resultado ejecutar(Operando a, Operando b) {
        return Resultado.exito(a.getValor() + b.getValor());
    }
}
