package approximation

import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class LinearApproximation : Approximation() {
   override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Triple<String, BigDecimal, Map<String, List<BigDecimal>>> {
      println("Линейная аппрокимация")
      println("Точки не изменяются: " + points.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
      println("n, sum(x), sum(x^2), sum(y), sum(x*y)")

      val coef = convertToMatrixAndSolve(points, 1)
      val strFunction = "%.3f ${toStr(coef[1])} * x".format(coef[0])
      val function = { x: BigDecimal -> coef[0] + coef[1] * x }

      println("Получена функция: $strFunction")
      val string = "Линейная аппроксимация: $strFunction"

      val sko = calculateStandardDeviation(points, function)
      correlation(points)
      println()
      val graphPoints = getGraphPoints(points, function)
      val coords = mapOf(
         "x" to graphPoints["x"]!!,
         string to graphPoints["y"]!!
      )

      return Triple(string, sko, coords)
   }

   private fun correlation(points: List<Pair<BigDecimal, BigDecimal>>) {
      val midX = points.sumOf { coords -> coords.first }.divide(BigDecimal(points.size), MathContext.DECIMAL64)
      val midY = points.sumOf { coords -> coords.second }.divide(BigDecimal(points.size), MathContext.DECIMAL64)
      val xMinusMidX = points.sumOf { coords -> (coords.first - midX).pow(2) }
      val yMinusMidY = points.sumOf { coords -> (coords.second - midY).pow(2) }
      val xy = points.sumOf { coords -> (coords.first - midX) * (coords.second - midY) }
      val coef = xy.divide(xMinusMidX.multiply(yMinusMidY).sqrt(MathContext.DECIMAL64), MathContext.DECIMAL64)
      val decision = when {
         coef > BigDecimal.ZERO -> "прямо-пропорциональная зависимость, "
         coef < BigDecimal.ZERO -> "обратно-пропорциональная зависимость, "
         else -> "зависимость отсутствует"
      } + when {
         coef == BigDecimal.ZERO -> ""
         coef.abs() > BigDecimal(0.99) -> "связь строгая"
         coef.abs() > BigDecimal(0.9) -> "связь весьма высокая"
         coef.abs() > BigDecimal(0.7) -> "связь высокая"
         coef.abs() > BigDecimal(0.5) -> "связь заметная"
         coef.abs() > BigDecimal(0.3) -> "связь умеренная"
         else -> "связь слабая"
      }
      println("Коэффициент корреляции Пирсона: $coef - $decision")
    }
}
