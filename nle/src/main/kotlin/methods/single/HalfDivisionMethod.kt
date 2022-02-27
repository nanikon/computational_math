package methods.single

import model.OneVariableEquation
import model.OneVariableResultData
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class HalfDivisionMethod : SingleMethod {
   private var leftBorder = BigDecimal.ZERO
   private var rightBorder = BigDecimal.ZERO
   private var isCorrect = false
   private lateinit var eq: OneVariableEquation

   override fun setAndVerifyData(a: BigDecimal, b: BigDecimal, equation: OneVariableEquation): Boolean {
      eq = equation
      leftBorder = a
      rightBorder = b
      val left = a.multiply(BigDecimal(1000)).toInt()
      val right = b.multiply(BigDecimal(1000)).toInt()
      val marker = eq.valueFirstDerivative(a)
      isCorrect = eq.valueFunction(a) * eq.valueFunction(b) < BigDecimal.ZERO && (left..right).all {
         marker * eq.valueFirstDerivative(BigDecimal(it.toDouble() / 1000)) >= BigDecimal.ZERO
      }
      return isCorrect
   }

   override fun solve(approximation: BigDecimal): OneVariableResultData {
      if (!isCorrect) throw RuntimeException("Сохраненные на данный момент входные данные не валидны, расчет невозможен")
      var a = leftBorder
      var b = rightBorder
      var count = 0
      var x = BigDecimal.ZERO
      var y = BigDecimal.ZERO
      while ((b.minus(a) > approximation) && y.abs() > approximation) {
         x = a.plus(b).divide(BigDecimal(2), MathContext.DECIMAL64)
         y = eq.valueFunction(x)
         count++
         println("Итерация №$count: левая граница $a, правая граница $b, приближение к корню $x, значение функции в нем $y")
         if (y.multiply(eq.valueFunction(a)) < BigDecimal.ZERO) { b = x } else { a = x }
      }
      return OneVariableResultData(root = x, valueRoot = y, countIteration = count)
   }

}