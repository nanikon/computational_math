package model

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.exp
import kotlin.math.log

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
        "x^2 + sqrt(y)^4 = 4"
    ),
    TwoVariableEquation(
        { x, y -> x.pow(2).multiply(BigDecimal(-3)).plus(y)},
        { x -> x.pow(2).multiply(BigDecimal(3)) },
        { x, _ -> x.multiply(BigDecimal(-6))},
        { _, _ -> BigDecimal.ONE},
        "y = 3 * x^2"
    ),
    TwoVariableEquation(
        { x, y -> x.plus(exp(y.toDouble()).toBigDecimal()).minus(BigDecimal(6)) },
        { x -> log(BigDecimal(6).minus(x).toDouble(), exp(1.0)).toBigDecimal() },
        { _, _ -> BigDecimal.ONE },
        { _, y -> exp(y.toDouble()).toBigDecimal() },
        "x + e^y = 6"
    ),
    TwoVariableEquation(
        {x, y -> x.multiply(BigDecimal(6)).minus(y).minus(BigDecimal(9.89)) },
        {x -> x.multiply(BigDecimal(6)).minus(BigDecimal(9.89)) },
        { _, _ -> BigDecimal(6)},
        { _, _ -> -BigDecimal.ONE},
        "6x - y = 9.89"
    )
)
