package approximation

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.ln

/**
 * @author Natalia Nikonova
 */
class LogarithmicApproximation : Approximation() {
    override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal> {
        val modifyPoints = points.map { coords ->
            Pair(ln(coords.first.toDouble()).toBigDecimal(MathContext.DECIMAL64), coords.second)
        }
        val coef = convertToMatrixAndSolve(modifyPoints, 1)
        val string = "Логарифмическая аппроксимация: ${coef[0]} + ${coef[1]} * ln(x)"
        val sko = calculateStandardDeviation(points) { x ->
            coef[0] + coef[1] * ln(x.toDouble()).toBigDecimal(MathContext.DECIMAL64)
        }
        return Pair(string, sko)
    }
}
