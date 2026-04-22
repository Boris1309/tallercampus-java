# Análisis y Resumen del Pair Programming: Calculadora S.O.L.I.D.

Este documento evidencia el proceso iterativo guiado por el desarrollador en conjunto con Antigravity (IA) para refactorizar una calculadora básica hacia un diseño robusto utilizando metodologías avanzadas de ingeniería de software (DDD, TDD y principios SOLID).

---

## 1. Documentación del "Pair Programming" con IA

El proceso se desarrolló mediante una serie de prompts iterativos orientados a la corrección estructural:

*   **Prompt 1 (Inicio):** _"Calculadora SoLiD. Quiero crear un programa en java que tenga operaciones aritmeticas entre numeros enteros, de tipo binarias (suma, resta, etc) y unarias (raiz cuadrada, logaritmo natural). Es una aplicación de consola. Tip: quiero todo en la misma clase"_
    *   **Resultado de la IA:** Generó la típica clase `CalculadoraMonolitica` que usamos como contra-ejemplo. Una sola clase enorme validando y ejecutando `switch-cases`. 
*   **Prompt 2 (Principio de Responsabilidad Única - SRP):** _"Refactoriza la clase anterior separando las responsabilidades. Haz que cada operación matemática (suma, resta, etc.) viva en su propia clase independiente, dejando la consola solo para presentar datos."_
    *   **Resultado de la IA:** Dividió el monolito original, sacando las operaciones a sus respectivas clases para que cada archivo tuviera una única razón para cambiar.
*   **Prompt 3 (Principio Abierto/Cerrado - OCP):** _"Ahora quiero asegurarme de que si añado nuevas operaciones en el futuro, no tenga que modificar ni tocar el código de la Calculadora principal. Debe estar abierta a extensión pero cerrada a modificación."_
    *   **Resultado de la IA:** Introdujo las jerarquías y delegó la ejecución de las fórmulas hacia afuera de la clase orquestadora.
*   **Prompt 4 (Principio de Sustitución de Liskov - LSP):** _"Corrige el programa aplicando el principio de Liskov. Las operaciones como la división por cero me están rompiendo la app con excepciones. Trata los fallos matemáticos devolviendo un estado controlado funcional en lugar de crashear, para que las clases derivadas no rompan la promesa de la clase base."_
    *   **Resultado de la IA:** Introdujo la clase envoltorio `Resultado.java` que retorna éxito/fallo (`isExitoso`) de forma segura.
*   **Prompt 5 (Principio de Segregación de Interfaces - ISP):** _"Aplica ISP. No quiero que una operación unaria (como la raíz) se vea obligada a recibir dos parámetros inútilmente. Separa las interfaces."_
    *   **Resultado de la IA:** Creó segregación estricta entre `OperacionBinaria` y `OperacionUnaria`. *(Nota: Por instrucciones de diseño, decidimos explícitamente **no implementar** el último principio de Inversión de Dependencias - DIP).*
*   **Prompt 6 (Entregables y Organización Final):** _"Organiza los entregables como los necesito: recupera la clase inicial en un paquete distinto, implementa pruebas unitarias (TDD) para validar los casos borde y construye un aislamiento de dominio usando Value Objects para los números."_
    *   **Resultado de la IA:** Implementó la clase inmutable `Operando.java`, creó los tests automatizados robustos con JUnit en `AppTest.java` y generó la estructura solicitada para el repositorio.

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
