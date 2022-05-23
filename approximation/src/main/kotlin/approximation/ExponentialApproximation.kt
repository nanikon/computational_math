package approximation

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.exp
import kotlin.math.ln

/**
 * @author Natalia Nikonova
 */
class ExponentialApproximation : Approximation() {
    override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Triple<String, BigDecimal, Map<String, List<BigDecimal>>>? {
        val modifyPoints = runCatching {
            points.map { coords ->
                Pair(coords.first, ln(coords.second.toDouble()).toBigDecimal(MathContext.DECIMAL64))
            }
        }.getOrNull() ?: run {
            println("Невозможно использовать экспоненциальную аппроксимацию, так как есть точки с у меньше нуля")
            return null
        }

        println("Экспоненциальная аппрокимация")
        println("x не изменяется, у линеаризируется: " + modifyPoints.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
        println("n, sum(x), sum(x^2), sum(y), sum(x*y)")

        val coef = convertToMatrixAndSolve(modifyPoints, 1)
        val b = coef[1]
        val a = exp(coef[0].toDouble()).toBigDecimal(MathContext.DECIMAL64)
        val strFunction = "$a * e^($b * x)"
        val function = { x: BigDecimal -> a * exp((x * b).toDouble()).toBigDecimal(MathContext.DECIMAL64) }

        println("Получена функция: $strFunction")
        val string = "Экспоненциальная аппроксимация: $strFunction"

        val sko = calculateStandardDeviation(points, function)
        println()
        val graphPoints = getGraphPoints(points, function)
        val coords = mapOf(
            "x" to graphPoints["x"]!!,
            string to graphPoints["y"]!!
        )

        return Triple(string, sko, coords)
    }
}
