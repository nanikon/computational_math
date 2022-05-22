package approximation

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.ln

/**
 * @author Natalia Nikonova
 */
class LogarithmicApproximation : Approximation() {
    override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal> {
        println("Логарифмическая аппрокимация")
        val modifyPoints = points.map { coords ->
            Pair(ln(coords.first.toDouble()).toBigDecimal(MathContext.DECIMAL64), coords.second)
        }
        println("x линеаризуется, у не изменяется: " + modifyPoints.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
        println("n, sum(x), sum(x^2), sum(y), sum(x*y)")
        val coef = convertToMatrixAndSolve(modifyPoints, 1)
        println("Получена функция: ${coef[0]} + ${coef[1]} * ln(x)")
        val string = "Логарифмическая аппроксимация: ${coef[0]} + ${coef[1]} * ln(x)"
        val sko = calculateStandardDeviation(points) { x ->
            coef[0] + coef[1] * ln(x.toDouble()).toBigDecimal(MathContext.DECIMAL64)
        }
        println()
        return Pair(string, sko)
    }
}
