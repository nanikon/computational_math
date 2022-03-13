package method

import model.IntegrateFunction
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
abstract class RectMethod : Method {
   abstract fun getValues(a: BigDecimal, b: BigDecimal, h: BigDecimal, function: IntegrateFunction) : List<BigDecimal>

   override fun calculateSquare(a: BigDecimal, b: BigDecimal, n: Int, function: IntegrateFunction): BigDecimal {
      val h = b.minus(a).divide(n.toBigDecimal(), MathContext.DECIMAL64)
      return getValues(a, b, h, function).reduce { x, y -> x.plus(y) }.multiply(h)
   }

   override fun accuracyOrder(): Int = 2
}