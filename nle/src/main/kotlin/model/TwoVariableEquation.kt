package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class TwoVariableEquation(
    val function: (X: BigDecimal, y: BigDecimal) -> BigDecimal,
    val derivativeX: (x: BigDecimal, y:BigDecimal) -> BigDecimal,
    val derivativeY: (x: BigDecimal, y:BigDecimal) -> BigDecimal,
    private val stringForm: String
) {
    override fun toString(): String = stringForm
}