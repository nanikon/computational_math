package approximation

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class LinearApproximation : Approximation() {
   override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal> {
      val coef = convertToMatrixAndSolve(points, 1)
      val string = "Линейная аппроксимация: ${coef[0]} + ${coef[1]} * x"
      val sko = calculateStandardDeviation(points) { x -> coef[0] + coef[1] * x }
      return Pair(string, sko)
   }
}
