package util

import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
fun BigDecimal.divideN(number: Int) = this.divide(BigDecimal(number), MathContext.DECIMAL32)
