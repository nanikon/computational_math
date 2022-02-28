package methods.single

import model.OneVariableEquation
import model.OneVariableResultData
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * @author Natalia Nikonova
 */
class SimpleIterationsMethod(
   var eq: OneVariableEquation
) : SingleMethod {
   private var leftBorder = BigDecimal.ZERO
   private var rightBorder = BigDecimal.ZERO
   private var isCorrect = false

   private var lambda = BigDecimal.ZERO
   private var q = BigDecimal.ZERO

   override fun setAndVerifyData(a: BigDecimal, b: BigDecimal): Boolean {
      leftBorder = a
      rightBorder = b
      val left = a.multiply(BigDecimal(1000)).toInt()
      val right = b.multiply(BigDecimal(1000)).toInt()
      lambda = - BigDecimal.ONE.divide((left..right).maxOf {
         eq.valueFirstDerivative(BigDecimal(it.toDouble() / 1000).setScale(3, RoundingMode.HALF_UP))
      }, MathContext.DECIMAL64)
      q = (left..right).maxOf {
         BigDecimal.ONE.plus(
            lambda.multiply(eq.valueFirstDerivative(BigDecimal(it.toDouble() / 1000).setScale(3, RoundingMode.HALF_UP)))
         ).abs()
      }
      isCorrect = q < BigDecimal.ONE
      return isCorrect
   }

   override fun solve(approximation: BigDecimal): OneVariableResultData {
      if (!isCorrect) throw RuntimeException("Сохраненные на данный момент входные данные не валидны, расчет невозможен")
      var last: BigDecimal
      var current = leftBorder
      var count = 0
      do {
         last = current
         current = eq.valueFunction(last).multiply(lambda, MathContext.DECIMAL64).plus(last)
         count++
         println("Итерация №$count: предыдущее приближение $last, текущее приближение $current, " +
            "значение функции на предыдущем ${eq.valueFunction(last)}, модуль разницы ${current.minus(last).abs()}")
      } while (((q <= BigDecimal(0.5)) && (current.minus(last).abs() >= approximation)) ||
         ((q > BigDecimal(0.5)) && (current.minus(last).abs() >= BigDecimal.ONE.minus(q).divide(q, MathContext.DECIMAL64).multiply(approximation))))
      return OneVariableResultData(root = current, valueRoot = eq.valueFunction(current), countIteration = count)
   }
}