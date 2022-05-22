package approximation

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.exp
import kotlin.math.ln

/**
 * @author Natalia Nikonova
 */
class ExponentialApproximation : Approximation() {
    override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal> {
        println("Экспоненциальная аппрокимация")
        val modifyPoints = points.map { coords ->
            Pair(coords.first, ln(coords.second.toDouble()).toBigDecimal(MathContext.DECIMAL64))
        }
        println("x не изменяется, у линеаризируется: " + modifyPoints.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
        println("n, sum(x), sum(x^2), sum(y), sum(x*y)")
        val coef = convertToMatrixAndSolve(modifyPoints, 1)
        val b = coef[1]
        val a = exp(coef[0].toDouble()).toBigDecimal(MathContext.DECIMAL64)
        val strFunction = "$a * e^($b * x)"
        println("Получена функция: $strFunction")
        val string = "Экспоненциальная аппроксимация: $strFunction"
        val sko = calculateStandardDeviation(points) { x ->
            a * exp((x * b).toDouble()).toBigDecimal(MathContext.DECIMAL64)
        }
        println()
        return Pair(string, sko)
    }
}
