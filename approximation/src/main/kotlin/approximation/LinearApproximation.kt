package approximation

import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class LinearApproximation : Approximation() {
   override fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal> {
      println("Линейная аппрокимация")
      println("Точки не изменяются: " + points.joinToString(separator = ", ") { "(" + it.first + ";" + it.second + ")" })
      println("n, sum(x), sum(x^2), sum(y), sum(x*y)")
      val coef = convertToMatrixAndSolve(points, 1)
      println("Получена функция: ${coef[0]} + ${coef[1]} * x")
      val string = "Линейная аппроксимация: ${coef[0]} + ${coef[1]} * x"
      val sko = calculateStandardDeviation(points) { x -> coef[0] + coef[1] * x }
      correlation(points)
      println()
      return Pair(string, sko)
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
