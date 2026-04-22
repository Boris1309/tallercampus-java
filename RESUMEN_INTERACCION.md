# Análisis y Resumen del Pair Programming: Calculadora S.O.L.I.D.

Este documento evidencia el proceso iterativo guiado por el desarrollador en conjunto con Antigravity (IA) para refactorizar una calculadora básica hacia un diseño robusto utilizando metodologías avanzadas de ingeniería de software (DDD, TDD y principios SOLID).

---

## 1. Documentación del "Pair Programming" con IA

El proceso se desarrolló mediante una serie de prompts iterativos orientados a la corrección estructural:

*   **Prompt 1 (Inicio):** _"Calculadora SoLiD. Quiero crear un programa en java que tenga operaciones aritmeticas entre numeros enteros, de tipo binarias (suma, resta, etc) y unarias (raiz cuadrada, logaritmo natural). Es una aplicación de consola. Tip: quiero todo en la misma clase"_
    *   **Resultado de la IA:** Generó la típica clase `CalculadoraMonolitica` que usamos como contra-ejemplo. Una sola clase enorme validando y ejecutando `switch-cases`. 
*   **Prompt 2 (SRP y OCP):** _"Refactoriza la clase anterior siguiendo los principios SOLID uno por uno exceptuando el último (DIP). Empieza separando las responsabilidades (SRP) y asegurando que nuevas operaciones no modifiquen la clase principal (OCP)."_
    *   **Resultado de la IA:** Dividió el monolito. Creó interfaces `OperacionBinaria` y `OperacionUnaria`. Las operaciones como `Suma` y `Resta` pasaron a ser clases independientes concretas.
*   **Prompt 3 (LSP - Sustitución de Liskov):** _"Aplica el principio de Liskov (LSP). Las operaciones de división o raíz negativa me están crasheando la aplicación. Envuelve los fallos sin generar excepciones abruptas que rompan el contrato esperado."_
    *   **Resultado de la IA:** Introdujo la clase envoltorio `Resultado.java` que retorna éxito/fallo (`isExitoso`).
*   **Prompt 4 (ISP - Segregación de Interfaces):** _"Aplica ISP. Asegúrate de que las operaciones no se vean forzadas a implementar métodos que no usan (por ejemplo, una suma no necesita implementar un método de un solo parámetro reservado para logaritmos)."_
    *   **Resultado de la IA:** Se afianzó el diseño de `OperacionBinaria` (dos parámetros) y `OperacionUnaria` (un parámetro), evitando interfaces "gruesas". *NOTA*: Se descartó deliberadamente el principio de Inversión de Dependencias (DIP) por instrucciones directas.
*   **Prompt 5 (DDD, TDD y Entregables finales):** _"Finalmente, aísla el dominio con Value Objects e incluye las pruebas para TDD en los límites matemáticos (como la división sobre cero)."_
    *   **Resultado de la IA:** Implementó la clase inmutable `Operando.java` y se añadió alta cobertura en `AppTest.java` (JUnit).

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
