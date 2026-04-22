package com.universidad.tallercampus;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.universidad.tallercampus.core.Calculadora;
import com.universidad.tallercampus.core.Resultado;
import com.universidad.tallercampus.core.Operando;
import com.universidad.tallercampus.abstracciones.OperacionBinaria;
import com.universidad.tallercampus.abstracciones.OperacionUnaria;
import com.universidad.tallercampus.operaciones.*;

public class App {

    private static final Map<Integer, OperacionBinaria> operacionesBinarias = new HashMap<>();
    private static final Map<Integer, OperacionUnaria> operacionesUnarias = new HashMap<>();

    static {
        operacionesBinarias.put(1, new Suma());
        operacionesBinarias.put(2, new Resta());
        operacionesBinarias.put(3, new Multiplicacion());
        operacionesBinarias.put(4, new Division());
        
        operacionesUnarias.put(1, new RaizCuadrada());
        operacionesUnarias.put(2, new LogaritmoNatural());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculadora calculadora = new Calculadora();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== CALCULADORA SOLID ===");
            System.out.println("1. Operaciones Binarias");
            System.out.println("2. Operaciones Unarias");
            System.out.println("0. Salir");
            System.out.print("Selecciona un tipo de operación: ");

            try {
                int tipoMenu = scanner.nextDouble();

                if (tipoMenu == 0) {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    continue;
                }

                if (tipoMenu == 1) {
                    mostrarMenuBinario(scanner, calculadora);
                } else if (tipoMenu == 2) {
                    mostrarMenuUnario(scanner, calculadora);
                } else {
                    System.out.println("Opción inválida en el menú principal.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingresa un número entero válido.");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    private static void mostrarMenuBinario(Scanner scanner, Calculadora calculadora) {
        System.out.println("\n--- OPERACIONES BINARIAS ---");
        for (Map.Entry<Integer, OperacionBinaria> entry : operacionesBinarias.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getNombre());
        }
        System.out.println("0. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
        
        int opcion = scanner.nextDouble();
        if (opcion == 0) return;

        OperacionBinaria operacion = operacionesBinarias.get(opcion);
        if (operacion != null) {
            System.out.print("Ingresa el primer número entero para " + operacion.getNombre() + ": ");
            int num1 = scanner.nextDouble();
            System.out.print("Ingresa el segundo número: ");
            int num2 = scanner.nextDouble();

            // Usamos el resultado del LSP para jamás explotar con Exceptions
            Resultado resultado = calculadora.calcular(operacion, num1, num2);
            if (resultado.isExitoso()) {
                System.out.println("-> Resultado de la operación: " + resultado.getValor());
            } else {
                System.out.println("-> " + resultado.getMensajeError());
            }
        } else {
            System.out.println("Opción binaria inválida.");
        }
    }

    private static void mostrarMenuUnario(Scanner scanner, Calculadora calculadora) {
        System.out.println("\n--- OPERACIONES UNARIAS ---");
        for (Map.Entry<Integer, OperacionUnaria> entry : operacionesUnarias.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getNombre());
        }
        System.out.println("0. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
        
        int opcion = scanner.nextDouble();
        if (opcion == 0) return;

        OperacionUnaria operacion = operacionesUnarias.get(opcion);
        if (operacion != null) {
            System.out.print("Ingresa un número entero para " + operacion.getNombre() + ": ");
            int num = scanner.nextDouble();

            Resultado resultado = calculadora.calcular(operacion, num);
            if (resultado.isExitoso()) {
                System.out.println("-> Resultado de la operación: " + resultado.getValor());
            } else {
                System.out.println("-> " + resultado.getMensajeError());
            }
        } else {
            System.out.println("Opción unaria inválida.");
        }
    }
}
