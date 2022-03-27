package method

import model.IntegrateFunction
import util.sum
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class SimpsonMethod : Method {
   override fun calculateSquare(a: BigDecimal, b: BigDecimal, h: BigDecimal, function: IntegrateFunction): BigDecimal {
      val values = getValues(a, b, h, function)
      val odd = values.filterIndexed { index, _ -> index % 2 == 1 }
      val even = values.filterIndexed { index, _ -> index % 2 == 0 && 0 != index && values.lastIndex != index }
      return values.first()
         .plus(values.last())
         .plus(odd.sum().multiply(BigDecimal(4)))
         .plus(even.sum().multiply(BigDecimal(2)))
         .multiply(h.divide(BigDecimal(3), MathContext.DECIMAL64))
   }

   private fun getValues(a: BigDecimal, b: BigDecimal, h: BigDecimal, function: IntegrateFunction) : List<BigDecimal> {
      val result = mutableListOf<BigDecimal>()
      var added = a
      do {
         result.add(function.calculate(added))
         added = added.plus(h)
      } while (added <= b)
      return result
   }

   override fun accuracyOrder(): Int = 4

   override fun toString(): String = "Метод Симпсона"
}
