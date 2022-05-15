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
        val modifyPoints = points.map { coords ->
            Pair(coords.first, ln(coords.second.toDouble()).toBigDecimal(MathContext.DECIMAL64))
        }
        val coef = convertToMatrixAndSolve(modifyPoints, 1)
        val b = coef[1]
        val a = exp(coef[0].toDouble()).toBigDecimal(MathContext.DECIMAL64)
        val string = "Экспоненциальная аппроксимация: $a * e^($b * x)"
        val sko = calculateStandardDeviation(points) { x ->
            a * exp((x * b).toDouble()).toBigDecimal(MathContext.DECIMAL64)
        }
        return Pair(string, sko)
    }
}
