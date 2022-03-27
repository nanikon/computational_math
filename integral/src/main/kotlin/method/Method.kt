package method

import model.IntegrateFunction
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface Method {
   fun calculateSquare(a: BigDecimal, b: BigDecimal, h: BigDecimal, function: IntegrateFunction) : BigDecimal

   fun accuracyOrder() : Int
}
