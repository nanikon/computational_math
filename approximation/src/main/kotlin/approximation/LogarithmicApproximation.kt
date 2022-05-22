package approximation

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.ln

/**
 * @author Natalia Nikonova
 */
class LogarithmicApproximation : Approximation() {
    override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Triple<String, BigDecimal, Map<String, List<BigDecimal>>>? {
        val modifyPoints = runCatching {
            points.map { coords ->
                Pair(ln(coords.first.toDouble()).toBigDecimal(MathContext.DECIMAL64), coords.second)
            }
        }.getOrNull() ?: run {
            println("Невозможно использовать логарифмическую аппроксимацию, так как есть точки с х и у, меньше нуля")
            return null
        }

        println("Логарифмическая аппрокимация")
        println("x линеаризуется, у не изменяется: " + modifyPoints.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
        println("n, sum(x), sum(x^2), sum(y), sum(x*y)")

        val coef = convertToMatrixAndSolve(modifyPoints, 1)
        val strFunction = "${coef[0]} ${toStr(coef[1])} * ln(x)"
        val function = { x: BigDecimal -> coef[0] + coef[1] * ln(x.toDouble()).toBigDecimal(MathContext.DECIMAL64) }

        println("Получена функция: $strFunction")
        val string = "Логарифмическая аппроксимация: $strFunction"

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
