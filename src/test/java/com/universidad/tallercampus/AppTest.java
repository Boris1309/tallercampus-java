package com.universidad.tallercampus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.universidad.tallercampus.core.Calculadora;
import com.universidad.tallercampus.core.Operando;
import com.universidad.tallercampus.core.Resultado;
import com.universidad.tallercampus.operaciones.Division;
import com.universidad.tallercampus.operaciones.LogaritmoNatural;
import com.universidad.tallercampus.operaciones.RaizCuadrada;
import com.universidad.tallercampus.operaciones.Resta;
import com.universidad.tallercampus.operaciones.Suma;

public class AppTest {

    @Test
    public void testSuma() {
        Calculadora calc = new Calculadora();
        Resultado resultado = calc.calcular(new Suma(), new Operando(5), new Operando(3));
        assertTrue(resultado.isExitoso());
        assertEquals(8.0, resultado.getValor(), "5 + 3 debería ser 8.0");
    }

    @Test
    public void testResta() {
        Calculadora calc = new Calculadora();
        Resultado resultado = calc.calcular(new Resta(), new Operando(5), new Operando(3));
        assertTrue(resultado.isExitoso());
        assertEquals(2.0, resultado.getValor(), "5 - 3 debería ser 2.0");
    }

    @Test
    public void testDivision() {
        Calculadora calc = new Calculadora();
        Resultado resultado = calc.calcular(new Division(), new Operando(10), new Operando(2));
        assertTrue(resultado.isExitoso());
        assertEquals(5.0, resultado.getValor(), "10 / 2 debería ser 5.0");
    }

    @Test
    public void testDivisionPorCero() {
        Calculadora calc = new Calculadora();
        // Vemos el poder de Liskov: en lugar de Throw ArithmeticException, devuelve un error.
        Resultado resultado = calc.calcular(new Division(), new Operando(10), new Operando(0));
        assertFalse(resultado.isExitoso(), "Debería reportarse como fallido en lugar de causar crash");
    }

    @Test
    public void testRaizCuadrada() {
        Calculadora calc = new Calculadora();
        Resultado resultado = calc.calcular(new RaizCuadrada(), new Operando(9));
        assertTrue(resultado.isExitoso());
        assertEquals(3.0, resultado.getValor(), "La raíz de 9 debería ser 3.0");
    }

    @Test
    public void testRaizCuadradaNegativa() {
        Calculadora calc = new Calculadora();
        Resultado resultado = calc.calcular(new RaizCuadrada(), new Operando(-9));
        assertFalse(resultado.isExitoso());
    }

    @Test
    public void testLogaritmoNatural() {
        Calculadora calc = new Calculadora();
        Resultado resultado = calc.calcular(new LogaritmoNatural(), new Operando(1));
        assertTrue(resultado.isExitoso());
        assertEquals(0.0, resultado.getValor(), "El ln(1) debería ser 0.0");
    }
}
