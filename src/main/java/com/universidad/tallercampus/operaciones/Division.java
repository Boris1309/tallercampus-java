package com.universidad.tallercampus.operaciones;

import com.universidad.tallercampus.abstracciones.OperacionBinaria;
import com.universidad.tallercampus.core.Resultado;
import com.universidad.tallercampus.core.Operando;

public class Division extends OperacionBinaria {
    public Division() {
        super("División");
    }

    @Override
    public Resultado ejecutar(Operando a, Operando b) {
        if (b.getValor() == 0) {
            return Resultado.error("Error abstracto: División por cero es lógicamente incalculable en reales.");
        }
        return Resultado.exito(a.getValor() / b.getValor());
    }
}
