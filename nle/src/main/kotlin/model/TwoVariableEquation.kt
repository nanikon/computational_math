package model

import java.lang.Math.cos
import java.lang.Math.sin
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

val systemEquations = listOf(
    TwoVariableEquation(
        {x, y -> BigDecimal(sin(y.toDouble())).plus(x.pow(2))},
        {x, y -> x + y},
        {x, y -> x + y},
        "sin(y)+x^2=0"
    )
)