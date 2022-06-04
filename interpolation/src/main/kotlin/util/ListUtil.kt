package util

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
fun List<BigDecimal>.multiplyOf(transform: (BigDecimal) -> BigDecimal): BigDecimal =
    this.reduce { acc, number -> acc * transform(number) }
