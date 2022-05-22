package approximation

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class CubicApproximation : Approximation() {
   override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Triple<String, BigDecimal, Map<String, List<BigDecimal>>> {
      println("Кубическая аппрокимация")
      println("Точки не изменяются: " + points.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
      println("n, sum(x), sum(x^2), sum(x^3), sum(x^4), sum(x^5), sum(x^6), sum(y), sum(x*y), sum(x^2*y), sum(x^3*y)")

      val coef = convertToMatrixAndSolve(points, 3)
      val strFunction = "${coef[0]} ${toStr(coef[1])} * x ${toStr(coef[2])} * x^2 ${toStr(coef[3])} * x^3"
      val function = { x: BigDecimal -> coef[0] + coef[1] * x + coef[2] * x.pow(2) + coef[3] * x.pow(3) }

      println("Получена функция: $strFunction")
      val string = "Кубическая аппроксимация: $strFunction"
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
