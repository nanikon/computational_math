import methods.system.NewtonMethod
import model.systemEquations

/**
 * @author Natalia Nikonova
 */

fun main() {
    // Выбор одного или системы
    val mode = chooseMode()
    if (mode == "1") {
        // Выбор уравнения
        val eq = chooseEquation()
        // Выбор метода
        val method = chooseMethod(eq)
        // Выбор формата входных данных
        val parser = chooseParser("границ интервала изоляции корней и погрешности")
        // Считывание данных
        // Проверка данных
        parser.parseInterval(method)
        // Рассчет
        val data = method.solve(parser.parseApprox())
        // Выбор формата выходных данных
        // Вывод выходных данных
        val printer = choosePrinter()
        printer.print(data.toString())
    } else {
        val firstEq = chooseEquations("первое", "-1")
        val secondEq = chooseEquations("второе", (systemEquations.indexOf(firstEq) + 1).toString())
        // сюды график
        createGraphTwoVariable(firstEq, secondEq)
        val method = NewtonMethod(firstEq, secondEq)
        val parser = chooseParser("начального приближения переменных и погрешности")
        parser.parseInitValues(method)
        val data = method.solve(parser.parseApprox())
        val printer = choosePrinter()
        printer.print(data.toString())
    }
}
