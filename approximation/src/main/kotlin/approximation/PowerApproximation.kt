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
    override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal> {
        val modifyPoints = points.map { coords ->
            Pair(
                ln(coords.first.toDouble()).toBigDecimal(MathContext.DECIMAL64),
                ln(coords.second.toDouble()).toBigDecimal(MathContext.DECIMAL64)
            )
        }
        val coef = convertToMatrixAndSolve(modifyPoints, 1)
        val b = coef[1]
        val a = exp(coef[0].toDouble()).toBigDecimal(MathContext.DECIMAL64)
        val string = "Степенная аппроксимация: $a * x^$b"
        val sko = calculateStandardDeviation(points) { x ->
            a * x.toDouble().pow(b.toDouble()).toBigDecimal(MathContext.DECIMAL64)
        }
        return Pair(string, sko)
    }
}
