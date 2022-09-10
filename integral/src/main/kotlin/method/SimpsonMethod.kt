package method

import model.IntegrateFunction
import model.PointPosition
import util.sum
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class SimpsonMethod : Method {
   override fun calculateSquare(
      a: BigDecimal,
      b: BigDecimal,
      h: BigDecimal,
      function: IntegrateFunction,
      approximation: BigDecimal
   ): BigDecimal {
      val values = getValues(a, b, h, function, approximation)
      val odd = values.filterIndexed { index, _ -> index % 2 == 1 }
      val even = values.filterIndexed { index, _ -> index % 2 == 0 && 0 != index && values.lastIndex != index }
      return values.first()
         .plus(values.last())
         .plus(odd.sum().multiply(BigDecimal(4)))
         .plus(even.sum().multiply(BigDecimal(2)))
         .multiply(h.divide(BigDecimal(3), MathContext.DECIMAL64))
   }

   private fun getValues(
      a: BigDecimal,
      b: BigDecimal,
      h: BigDecimal,
      function: IntegrateFunction,
      approximation: BigDecimal
   ) : List<BigDecimal> {
      val result = mutableListOf<BigDecimal>()
      result.add(function.calculate(a, PointPosition.START, approximation))
      var added = a.plus(h)
      while (added < b.minus(h.divide(BigDecimal(2), MathContext.DECIMAL64))) {
         result.add(function.calculate(added, PointPosition.MID, approximation))
         added = added.plus(h)
      }
      result.add(function.calculate(b, PointPosition.END, approximation))
      return result
   }

   override fun accuracyOrder(): Int = 4

   override fun toString(): String = "Метод Симпсона"
}
