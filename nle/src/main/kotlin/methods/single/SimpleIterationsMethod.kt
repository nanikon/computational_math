package methods.single

import model.Equation
import model.ResultData
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class SimpleIterationsMethod : SingleMethod {
   private var leftBorder = BigDecimal.ZERO
   private var rightBorder = BigDecimal.ZERO
   private var isCorrect = false
   private lateinit var eq: Equation

   private var lambda = BigDecimal.ZERO
   private var q = BigDecimal.ZERO

   override fun setAndVerifyData(a: BigDecimal, b: BigDecimal, equation: Equation): Boolean {
      eq = equation
      leftBorder = a
      rightBorder = b
      val left = a.multiply(BigDecimal(1000)).toInt()
      val right = b.multiply(BigDecimal(1000)).toInt()
      lambda = - BigDecimal.ONE.divide((left..right).maxOf { eq.valueFirstDerivative(BigDecimal(it.toDouble() / 1000)) })
      q = (left..right).maxOf {
         BigDecimal.ONE.plus(
            lambda.multiply(eq.valueFirstDerivative(BigDecimal(it.toDouble() / 1000)))
         ).abs()
      }
      isCorrect = q < BigDecimal.ONE
      return isCorrect
   }

   override fun solve(approximation: BigDecimal): ResultData {
      if (!isCorrect) throw RuntimeException("Сохраненные на данный момент входные данные не валидны, расчет невозможен")
      var last: BigDecimal
      var current = leftBorder
      var count = 0
      do {
         last = current
         current = eq.valueFunction(last).multiply(lambda).plus(last)
         count++
      } while (((q <= BigDecimal(0.5)) && (current.minus(last).abs() < approximation)) ||
         ((q > BigDecimal(0.5)) && (current.minus(last).abs() < BigDecimal.ONE.minus(q).divide(q).multiply(approximation))))
      return ResultData(root = current, valueRoot = eq.valueFunction(current), countIteration = count)
   }
}