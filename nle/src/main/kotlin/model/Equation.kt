package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class Equation(
    private val function: (x: BigDecimal) -> BigDecimal,
    private val firstDerivative: (x: BigDecimal) -> BigDecimal,
    private val stringForm: String
) {
    fun valueFunction(x: BigDecimal): BigDecimal = function(x)

    fun valueFirstDerivative(x: BigDecimal): BigDecimal = firstDerivative(x)

    override fun toString(): String = stringForm
}