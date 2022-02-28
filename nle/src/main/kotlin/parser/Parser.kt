package parser

import methods.single.SingleMethod
import methods.system.SystemMethod
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface Parser {
   fun parseInterval(method: SingleMethod)
   fun parseApprox(): BigDecimal
   fun parseInitValues(method: SystemMethod)
}