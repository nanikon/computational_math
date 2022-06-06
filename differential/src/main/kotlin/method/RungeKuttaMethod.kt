package method

import model.DiffEquation
import util.divideN
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
object RungeKuttaMethod {
   fun compute(diff: DiffEquation, xs: List<BigDecimal>, y0: BigDecimal, eps: BigDecimal): List<BigDecimal> {
      val ys = mutableListOf(y0)
      var n = 2
      for (i in 1 until xs.size) {
         n /= 2
         var newY = calculateInterval(diff.function, xs[i - 1], xs[i], ys[i - 1], n)
         var oldY: BigDecimal
         do {
            oldY = newY
            n *= 2
            newY = calculateInterval(diff.function, xs[i - 1], xs[i], ys[i - 1], n)
         } while ((oldY - newY).divideN(15).abs() >= eps) // 2 ^ 4 - 1 = 16 - 1 = 15
         ys.add(newY)
      }
      return ys
   }

   private fun calculateInterval(
      function: (x: BigDecimal, y: BigDecimal) -> BigDecimal,
      a: BigDecimal,
      b: BigDecimal,
      y0: BigDecimal,
      n: Int
   ): BigDecimal {
      val h = (b - a).divideN(n)
      val xs = mutableListOf(a)
      var elem = a + h
      while (elem <= b) {
         xs.add(elem)
         elem += h
      }

      val ys = mutableListOf(y0)
      for (i in 1 until xs.size) {
         ys.add(calculateY(function, xs[i - 1], ys[i - 1], h))
      }
      return ys.last()
   }

   private fun calculateY(
      function: (x: BigDecimal, y: BigDecimal) -> BigDecimal,
      x: BigDecimal,
      y: BigDecimal,
      h: BigDecimal
   ): BigDecimal {
      val hDiv2 = h.divideN(2)
      val k1 = h * function(x, y)
      val k2 = h * function(x + hDiv2, y + k1.divideN(2))
      val k3 = h * function (x + hDiv2, y + k2.divideN(2))
      val k4 = h * function(x + h, y + k3)
      return y + (k1 + BigDecimal(2) * k2 + BigDecimal(2) * k3 + k4).divideN(6)
   }
}
