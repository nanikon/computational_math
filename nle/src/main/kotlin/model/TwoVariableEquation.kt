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
        { x, y -> x.pow(2).plus(y.pow(2)).minus(BigDecimal(4)) },
        { x, y -> x.multiply(BigDecimal(2)) },
        { x, y -> y.multiply(BigDecimal(2)) },
        "x^2 + y^2 = 4"
    ),
    TwoVariableEquation(
        {x, y -> x.pow(2).multiply(BigDecimal(-3)).plus(y)},
        {x, y -> x.multiply(BigDecimal(-6))},
        {x, y -> BigDecimal.ONE},
        "y = 3 * x^2"
    )
)