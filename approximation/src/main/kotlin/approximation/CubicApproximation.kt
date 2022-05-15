package approximation

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class CubicApproximation : Approximation() {
   override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal> {
      val coef = convertToMatrixAndSolve(points, 3)
      val string = "Кубическая аппроксимация: ${coef[0]} + ${coef[1]} * x + ${coef[2]} * x^2 + ${coef[3]} * x^3"
      val sko = calculateStandardDeviation(points) { x -> coef[0] + coef[1] * x + coef[2] * x.pow(2) + coef[3] * x.pow(3) }
      return Pair(string, sko)
   }
}
