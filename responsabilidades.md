# Responsabilidades de la clase App (Calculadora SOLID)

La clase `App` actúa como un **contenedor** (o *namespace*) para albergar toda la arquitectura de la calculadora en un solo archivo, respetando tu instrucción de mantenerlo unificado. Por sí misma, orquesta la ejecución general del programa. 

Sin embargo, su interior está subdividido en clases e interfaces más pequeñas que respetan rigurosamente el **Principio de Responsabilidad Única (SRP)** de la filosofía SOLID. A continuación se detallan las responsabilidades de cada bloque en su interior:

## 1. Interfaces Contractuales
Define el contrato de cómo deben comportarse las distintas clases matemáticas. Están separadas aplicando el Principio de Segregación de Interfaz (ISP) para no forzar a clases unarias a implementar métodos que solo necesitan las binarias.

- **`OperacionBinaria`**: Tiene la responsabilidad exclusiva de exigir un método para procesar operaciones aritméticas que requieran dos números (`a`, `b`).
- **`OperacionUnaria`**: Tiene la responsabilidad exclusiva de exigir un método para procesar operaciones aritméticas que requieran de un único operando (`a`).

## 2. Clases de Operaciones (Capa Logico-Matemática)
Cada clase concreta asume una única tarea matemática. Esto obedece a cabalidad al Principio de Responsabilidad Única; de este modo, si hay que modificar el algoritmo de la división, no se toca ni por accidente el código de la suma o la resta.

- **`Suma`**: Responsable del algoritmo de adición de dos números y retornar el total.
- **`Resta`**: Responsable de restar un número de otro y computar la diferencia.
- **`Multiplicacion`**: Responsable de computar el producto base del operando multiplicador.
- **`Division`**: Responsable de calcular la división y asegurar la integridad previniendo la división por cero (Lanzando una `ArithmeticException`).
- **`RaizCuadrada`**: Responsable de calcular la raíz cuadrada sobre naturales verificando que el número no sea negativo antes de procesarlo matemáticamente.
- **`LogaritmoNatural`**: Responsable de computar el logaritmo basado en la función `Math.log`, impidiendo mediante validaciones el uso de números iguales o menores a cero.

## 3. Clase Contexto o Ejecutora
- **`Calculadora`**: Su única responsabilidad es ser el **motor de ejecución**. La calculadora delega el trabajo real y se basa en el principio de Inversión de Dependencias (DIP) y no le interesa saber **qué** operación está haciendo, sino que recibe el "contrato" de la operación, le proporciona las cifras variables recibidas del usuario y recupera el doble entregado. 

## 4. Método Principal (App.main) y Capa de Vista/Controlador
- **`main(String[] args)`**: Su responsabilidad es hacer funcionar la capa interactiva de la aplicación mediante la consola. El código de este módulo se encarga exclusivamente de:
  - Configurar, abrir y cerrar los flujos de lectura (`Scanner`).
  - Mostrar el menú de forma iterativa constante visualmente.
  - Recibir la selección de un usuario, procesarla y enviar las dependencias a la "Calculadora".
  - Capturar y reportar los fallos del formato de entrada (texto en lugar de número mediante `InputMismatchException`) y los fallos reportados por las clases matemáticas (`ArithmeticException`).
