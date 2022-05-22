package approximation

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow

/**
 * @author Natalia Nikonova
 */
class PowerApproximation : Approximation() {
    override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Triple<String, BigDecimal, Map<String, List<BigDecimal>>> {
        val modifyPoints = points.map { coords ->
            Pair(
                ln(coords.first.toDouble()).toBigDecimal(MathContext.DECIMAL64),
                ln(coords.second.toDouble()).toBigDecimal(MathContext.DECIMAL64)
            )
        }

        println("Степенная аппрокимация")
        println("х и у линеаризируются: " + modifyPoints.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
        println("n, sum(x), sum(x^2), sum(y), sum(x*y)")

        val coef = convertToMatrixAndSolve(modifyPoints, 1)
        val b = coef[1]
        val a = exp(coef[0].toDouble()).toBigDecimal(MathContext.DECIMAL64)
        val strFunction = "$a * x^$b"
        val function = { x: BigDecimal -> a * x.toDouble().pow(b.toDouble()).toBigDecimal(MathContext.DECIMAL64) }

        println("Получена функция: $strFunction")
        val string = "Степенная аппроксимация: $strFunction"

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
