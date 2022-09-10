package approximation

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class QuadraticApproximation : Approximation() {
   override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Triple<String, BigDecimal, Map<String, List<BigDecimal>>> {
      println("Квадратичная аппрокимация")
      println("Точки не изменяются: " + points.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
      println("n, sum(x), sum(x^2), sum(x^3), sum(x^4), sum(y), sum(x*y), sum(x^2*y)")

      val coef = convertToMatrixAndSolve(points, 2)
      val strFunction = "%.3f ${toStr(coef[1])} * x ${toStr(coef[2])} * x^2".format(coef[0])
      val function = { x: BigDecimal -> coef[0] + coef[1] * x + coef[2] * x.pow(2) }

      println("Получена функция: $strFunction")
      val string = "Квадратичная аппроксимация: $strFunction"
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
