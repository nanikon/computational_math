package util

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */

fun List<BigDecimal>.sum() = this.reduce { x, y -> x.plus(y) }
