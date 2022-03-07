package methods.single

import createGraphOneVariable
import model.OneVariableEquation
import model.OneVariableResultData
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * @author Natalia Nikonova
 */
class HalfDivisionMethod(
   var eq: OneVariableEquation
) : SingleMethod {
   private var leftBorder = BigDecimal.ZERO
   private var rightBorder = BigDecimal.ZERO
   private var isCorrect = false

   override fun setAndVerifyData(a: BigDecimal, b: BigDecimal): Boolean {
      leftBorder = a
      rightBorder = b
      val left = a.multiply(BigDecimal(1000)).toInt()
      val right = b.multiply(BigDecimal(1000)).toInt()
      val marker = eq.valueFirstDerivative(a)
      isCorrect = eq.valueFunction(a) * eq.valueFunction(b) <= BigDecimal.ZERO && (left..right).all {
         marker.multiply(eq.valueFirstDerivative(BigDecimal(it.toDouble() / 1000).setScale(3, RoundingMode.HALF_UP))) >= BigDecimal.ZERO
      }
      return isCorrect
   }

   override fun solve(approximation: BigDecimal): OneVariableResultData {
      if (!isCorrect) throw RuntimeException("Сохраненные на данный момент входные данные не валидны, расчет невозможен")
      var a = leftBorder
      var b = rightBorder
      var count = 0
      var x = BigDecimal.ZERO
      var y = approximation.plus(BigDecimal.ONE)
      while ((b.minus(a) > approximation) && y.abs() > approximation) {
         x = a.plus(b).divide(BigDecimal(2), MathContext.DECIMAL64)
         y = eq.valueFunction(x)
         count++
         println("Итерация №$count: левая граница $a, правая граница $b, приближение к корню $x, значение функции в нем $y")
         if (y.multiply(eq.valueFunction(a)) < BigDecimal.ZERO) { b = x } else { a = x }
      }
      val delta = rightBorder.minus(leftBorder).divide(BigDecimal(5), MathContext.DECIMAL64)
      createGraphOneVariable(leftBorder - delta, rightBorder + delta, eq)
      return OneVariableResultData(root = x, valueRoot = y, countIteration = count)
   }

}