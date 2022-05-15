package approximation

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class QuadraticApproximation : Approximation() {
   override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal> {
      val coef = convertToMatrixAndSolve(points, 2)
      val string = "Квадратичная аппроксимация: ${coef[0]} + ${coef[1]} * x + ${coef[2]} * x^2"
      val sko = calculateStandardDeviation(points) { x -> coef[0] + coef[1] * x + coef[2] * x.pow(2) }
      return Pair(string, sko)
   }
}
