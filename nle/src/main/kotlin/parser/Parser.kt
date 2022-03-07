package parser

import methods.single.SingleMethod
import methods.system.SystemMethod
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface Parser {
   fun parseIntervalAndApprox(method: SingleMethod) : BigDecimal
   fun parseInitValuesAndApprox(method: SystemMethod) : BigDecimal
}