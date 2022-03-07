import methods.system.NewtonMethod
import model.systemEquations

/**
 * @author Natalia Nikonova
 */

fun main() {
    val mode = chooseMode()
    if (mode == "1") {
        val eq = chooseEquation()
        val method = chooseMethod(eq)
        val parser = chooseParser("границ интервала изоляции корней и погрешности")
        val data = method.solve(parser.parseIntervalAndApprox(method))
        val printer = choosePrinter()
        printer.print(data.toString())
    } else {
        val firstEq = chooseEquations("первое", "-1")
        val secondEq = chooseEquations("второе", (systemEquations.indexOf(firstEq) + 1).toString())
        createGraphTwoVariable(firstEq, secondEq)
        val method = NewtonMethod(firstEq, secondEq)
        val parser = chooseParser("начального приближения переменных и погрешности")
        val data = method.solve(parser.parseInitValuesAndApprox(method))
        val printer = choosePrinter()
        printer.print(data.toString())
    }
}
