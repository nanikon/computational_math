package method

import model.IntegrateFunction
import util.sum
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
abstract class RectMethod : Method {

   override fun calculateSquare(
      a: BigDecimal,
      b: BigDecimal,
      h: BigDecimal,
      function: IntegrateFunction,
      approximation: BigDecimal
   ): BigDecimal =
      getValues(a, b, h, function, approximation).sum().multiply(h)

   abstract fun getValues(
      a: BigDecimal,
      b: BigDecimal,
      h: BigDecimal,
      function: IntegrateFunction,
      approximation: BigDecimal
   ) : List<BigDecimal>

   override fun accuracyOrder(): Int = 2
}
