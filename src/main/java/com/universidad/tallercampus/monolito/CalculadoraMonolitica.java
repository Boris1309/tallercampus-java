package com.universidad.tallercampus.monolito;

/**
 * Esta es la versión inicial de la calculadora.
 * Un "Anti-patrón" monolítico donde todas las responsabilidades,
 * operaciones matemáticas, y validación de errores estaban 
 * centralizadas en una sola clase gigante y usando estructuras switch/case. 
 * Esta clase se deja como evidencia del entregable #2.
 */
public class CalculadoraMonolitica {
    
    public double calcularOperacionBinaria(String operacion, double a, double b) throws Exception {
        switch(operacion.toLowerCase()) {
            case "suma":
                return a + b;
            case "resta":
                return a - b;
            case "multiplicacion":
                return a * b;
            case "division":
                if (b == 0) {
                    throw new ArithmeticException("No se puede dividir por cero");
                }
                return a / b;
            default:
                throw new Exception("Operación binaria no soportada: " + operacion);
        }
    }
    
    public double calcularOperacionUnaria(String operacion, double a) throws Exception {
        switch(operacion.toLowerCase()) {
            case "raiz":
                if (a < 0) {
                    throw new ArithmeticException("No se puede sacar raíz de un número negativo en los reales");
                }
                return Math.sqrt(a);
            case "logaritmo":
                if (a <= 0) {
                    throw new ArithmeticException("El logaritmo natural solo está definido para números positivos mayores a 0");
                }
                return Math.log(a);
            default:
                throw new Exception("Operación unaria no soportada: " + operacion);
        }
    }
}
