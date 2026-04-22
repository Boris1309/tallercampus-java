package com.universidad.tallercampus.abstracciones;

import com.universidad.tallercampus.core.Resultado;
import com.universidad.tallercampus.core.Operando;

public abstract class OperacionUnaria {
    protected String nombre;
    
    public OperacionUnaria(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public abstract Resultado ejecutar(Operando a);
}
