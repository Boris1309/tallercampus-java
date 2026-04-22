# Análisis y Resumen del Pair Programming: Calculadora S.O.L.I.D.

Este documento evidencia el proceso iterativo guiado por el desarrollador en conjunto con Antigravity (IA) para refactorizar una calculadora básica hacia un diseño robusto utilizando metodologías avanzadas de ingeniería de software (DDD, TDD y principios SOLID).

---

## 1. Documentación del "Pair Programming" con IA

El proceso se desarrolló mediante una serie de prompts iterativos orientados a la corrección estructural:

*   **Prompt 1 (Inicio):** _"Crea una calculadora de consola en java con operaciones unarias y binarias."_
    *   **Resultado de la IA:** Generó la típica clase `CalculadoraMonolitica` que usamos como contra-ejemplo. Una sola clase enorme validando y ejecutando `switch-cases`. 
*   **Prompt 2 (Refactorización):** _"Refactoriza la clase anterior siguiendo los principios SOLID. Quiero aplicar OCP e ISP separando la lógica en paquetes de abstracciones."_
    *   **Resultado de la IA:** Dividió el monolito. Creó interfaces `OperacionBinaria` y `OperacionUnaria`. Las operaciones como `Suma` y `Resta` pasaron a ser clases independientes concretas.
*   **Prompt 3 (Evitar Excepciones Runtime - LSP):** _"Las operaciones de división o raíz negativa me están crasheando la aplicación. Usa el principio de sustitución de Liskov para envolver los fallos de dominio sin romper el programa."_
    *   **Resultado de la IA:** Introdujo la clase envoltorio `Resultado.java` que retorna éxito/fallo (`isExitoso`).
*   **Prompt 4 (DDD y Value Objects):** _"Necesitamos aislamiento total de dominio. Crea un Value Object para los operandos matemáticos y encapsula ahí las validaciones base para no pasar primitivos (int/double)."_
    *   **Resultado de la IA:** Implementó la clase `Operando.java`. Ahora toda interfaz inyecta un objeto inmutable `Operando` que se niega a recibir números Not-a-Number (NaN) o infinitos.
*   **Prompt 5 (TDD y Entregables finales):** _"Genera casos de prueba para los límites (división entre cero) y organiza los entregables finales con la documentación y el monolito inicial."_
    *   **Resultado de la IA:** Ampliación de `AppTest.java` (JUnit) y creación de este reporte.

---

## 2. Análisis Crítico Profundo

### El Antipatrón Monolítico (God Object)
La versión `CalculadoraMonolitica` sufría de un agudo **God Object Anti-Pattern**. 
- **Impacto del Acoplamiento:** Para añadir una nueva operación trigonométrica, el desarrollador estaba forzado a modificar el *switch-case* central (`CalculadoraMonolitica.java`). Modificar clases funcionales rompe la regla "Abierto/Cerrado" (OCP) e incrementa el riesgo de introducir bugs de regresión letales.
- **Responsabilidad Dispersa:** Una sola clase decidía cómo pintar en consola (UI), qué validar, y cómo aplicar fórmulas matemáticas (Violación total del SRP).

### La Arquitectura Implementada
La versión final supera los fallos monolíticos aplicando principios S.O.L.I.D. y Diseño Guiado por Entidades (DDD):
- **Aislamiento Total del Dominio (DDD):** El motor no procesa simples variables primitivas genéricas (`double/int`). Se creó el *Value Object* `Operando.java`. El motor es protegido por la capa del dominio: sólo entran a operarse objetos que ya vienen intrínsecamente validados de no ser números irreales.
- **Sustitución e Inversión de Dependencias (DIP/LSP):** La `Calculadora` recibe dependencias por parámetros en tiempo de ejecución: `calcular(OperacionBinaria operacion, Operando a, Operando b)`. No sabe qué ejecuta, sólo sabe que recibirá una abstracción matemática válida.
- **Robustez frente a fallos (No Crashes):** El motor devuelve un wrapper de estado lógico (`Resultado`). Los casos problemáticos no tiran "ArithmeticExceptions", sino que el proceso fluye devolviendo de manera funcional un log descriptivo de error.

---

## 3. Excelente Cobertura y Diseño Guiado por Pruebas (TDD)
En el paquete `src/test/java`, la clase `AppTest.java` actúa como red de seguridad.
Se cubrieron los **casos de borde matemáticos** asegurando el comportamiento del dominio:
- `testDivisionPorCero`: Atestigua (Assert) que enviar un cero como divisor devuelve un objeto estructurado inofensivo con `isExitoso() == false`, en lugar de corromper la memoria.
- `testRaizCuadradaNegativa`: Verifica la intercepción de variables por fuera del marco lógico real.
- `testSuma` / `testLogaritmoNatural`: Casos nominales o estándar.

Gracias a nuestro nuevo *Value Object* `Operando` y a las *Interfaces*, esta suite de testing es capaz de probar cualquier clase por aislado. Una suma se puede instanciar directamente (`new Suma().ejecutar(...)`) sin levantar los *Scanners* ni los menús de consola.
