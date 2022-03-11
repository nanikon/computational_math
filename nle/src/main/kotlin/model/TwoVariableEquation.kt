package model

import java.lang.Math.cos
import java.lang.Math.sin
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class TwoVariableEquation(
    val function: (x: BigDecimal, y: BigDecimal) -> BigDecimal,
    val yFromX:  (BigDecimal) -> BigDecimal,
    val derivativeX: (x: BigDecimal, y:BigDecimal) -> BigDecimal,
    val derivativeY: (x: BigDecimal, y:BigDecimal) -> BigDecimal,
    private val stringForm: String
) {
    override fun toString(): String = stringForm
}

val systemEquations = listOf(
    TwoVariableEquation(
        { x, y -> x.pow(2).plus(y.pow(2)).minus(BigDecimal(4)) },
        { x -> BigDecimal(4).minus(x.pow(2)).sqrt(MathContext.DECIMAL64) },
        { x, _ -> x.multiply(BigDecimal(2)) },
        { _, y -> y.multiply(BigDecimal(2)) },
        "x^2 + y^2 = 4"
    ),
    TwoVariableEquation(
        {x, y -> x.pow(2).multiply(BigDecimal(-3)).plus(y)},
        { x -> x.pow(2).multiply(BigDecimal(3)) },
        {x, _ -> x.multiply(BigDecimal(-6))},
        {_, _ -> BigDecimal.ONE},
        "y = 3 * x^2"
    )
)
