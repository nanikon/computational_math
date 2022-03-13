package method

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface Method {
   fun calculateSquare(a: BigDecimal, b: BigDecimal, n: Int) : BigDecimal

   fun accuracyOrder() : Int
}