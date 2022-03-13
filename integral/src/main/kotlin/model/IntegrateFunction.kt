package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class IntegrateFunction(
    private val function: (BigDecimal) -> BigDecimal,
    private val view: String
) {
    fun calculate(x: BigDecimal) = function(x)

    override fun toString(): String = view
}