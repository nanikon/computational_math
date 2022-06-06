import method.AdamsMethod
import util.Parser
import util.chooseEquation
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
fun main() {
    val equation = chooseEquation()
    val a = Parser.parseBigDecimal("левую границу интервала", "") { true }
    val b = Parser.parseBigDecimal("правую границу интервала", "больше, чем $a") { it > a }
    val y0 = Parser.parseBigDecimal("начальное значение y0 в точке x0=$a", "") { true }
    equation.computeCoef(a, y0)
    val h = Parser.parseBigDecimal("длину интервала", " и помещаться в интервал целое число раз") {
        b == a + (b - a).divide(it, MathContext.DECIMAL32) * it
    }
    val eps = Parser.parseBigDecimal("погрешность", "между 0 и 1") {
        it > BigDecimal.ZERO && it < BigDecimal.ONE
    }

    val xs = mutableListOf(a)
    var elem = a + h
    while (elem != b) {
        xs.add(elem)
        elem += h
    }

    val ys = xs.map { equation.compute(it) }
    val result2 = AdamsMethod.compute(equation, xs, ys, h, eps)
}
