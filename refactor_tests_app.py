import os

apptest_path = "/home/borisvalbuena/Documentos/universidad/fundamentos/tallercampus/src/test/java/com/universidad/tallercampus/AppTest.java"
with open(apptest_path, 'r') as f:
    tcontent = f.read()

tcontent = tcontent.replace("import com.universidad.tallercampus.core.Calculadora;", "import com.universidad.tallercampus.core.Calculadora;\nimport com.universidad.tallercampus.core.Operando;")
tcontent = tcontent.replace(", 5, 3", ", new Operando(5), new Operando(3)")
tcontent = tcontent.replace(", 10, 2", ", new Operando(10), new Operando(2)")
tcontent = tcontent.replace(", 10, 0", ", new Operando(10), new Operando(0)")
tcontent = tcontent.replace(", 9)", ", new Operando(9))")
tcontent = tcontent.replace(", -9)", ", new Operando(-9))")
tcontent = tcontent.replace(", 1)", ", new Operando(1))")

with open(apptest_path, 'w') as f:
    f.write(tcontent)

app_path = "/home/borisvalbuena/Documentos/universidad/fundamentos/tallercampus/src/main/java/com/universidad/tallercampus/App.java"
with open(app_path, 'r') as f:
    acontent = f.read()

acontent = acontent.replace("import com.universidad.tallercampus.core.Resultado;", "import com.universidad.tallercampus.core.Resultado;\nimport com.universidad.tallercampus.core.Operando;")
acontent = acontent.replace("int a =", "double aVal =")
acontent = acontent.replace("int b =", "double bVal =")
acontent = acontent.replace("scanner.nextInt()", "scanner.nextDouble()")
acontent = acontent.replace("calc.calcular(new Suma(), a, b)", "calc.calcular(new Suma(), new Operando(aVal), new Operando(bVal))")
acontent = acontent.replace("calc.calcular(new Resta(), a, b)", "calc.calcular(new Resta(), new Operando(aVal), new Operando(bVal))")
acontent = acontent.replace("calc.calcular(new Multiplicacion(), a, b)", "calc.calcular(new Multiplicacion(), new Operando(aVal), new Operando(bVal))")
acontent = acontent.replace("calc.calcular(new Division(), a, b)", "calc.calcular(new Division(), new Operando(aVal), new Operando(bVal))")
acontent = acontent.replace("calc.calcular(new RaizCuadrada(), a)", "calc.calcular(new RaizCuadrada(), new Operando(aVal))")
acontent = acontent.replace("calc.calcular(new LogaritmoNatural(), a)", "calc.calcular(new LogaritmoNatural(), new Operando(aVal))")

with open(app_path, 'w') as f:
    f.write(acontent)
