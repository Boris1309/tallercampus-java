import os
import glob

def refactor_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # Imports
    content = content.replace("import com.universidad.tallercampus.core.Resultado;", "import com.universidad.tallercampus.core.Resultado;\nimport com.universidad.tallercampus.core.Operando;")
    
    # Method signatures
    content = content.replace("ejecutar(int a, int b)", "ejecutar(Operando a, Operando b)")
    content = content.replace("ejecutar(int a)", "ejecutar(Operando a)")
    content = content.replace("calcular(OperacionBinaria operacion, int a, int b)", "calcular(OperacionBinaria operacion, Operando a, Operando b)")
    content = content.replace("calcular(OperacionUnaria operacion, int a)", "calcular(OperacionUnaria operacion, Operando a)")

    # Operations logic
    content = content.replace("b == 0", "b.getValor() == 0")
    content = content.replace("a < 0", "a.getValor() < 0")
    content = content.replace("a <= 0", "a.getValor() <= 0")
    content = content.replace("(double) a / b", "a.getValor() / b.getValor()")
    content = content.replace("a + b", "a.getValor() + b.getValor()")
    content = content.replace("a - b", "a.getValor() - b.getValor()")
    content = content.replace("a * b", "a.getValor() * b.getValor()")
    content = content.replace("Math.log(a)", "Math.log(a.getValor())")
    content = content.replace("Math.sqrt(a)", "Math.sqrt(a.getValor())")

    with open(filepath, 'w') as f:
        f.write(content)

java_files = glob.glob("/home/borisvalbuena/Documentos/universidad/fundamentos/tallercampus/src/main/java/com/universidad/tallercampus/**/*.java", recursive=True)
for f in java_files:
    if "CalculadoraMonolitica" not in f and "App.java" not in f:
        refactor_file(f)

