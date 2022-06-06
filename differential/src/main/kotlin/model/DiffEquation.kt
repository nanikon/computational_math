package model

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.exp

/**
 * @author Natalia Nikonova
 */
class DiffEquation(
    val function: (x: BigDecimal, y: BigDecimal) -> BigDecimal,
    private val coef: (x0: BigDecimal, y0: BigDecimal) -> BigDecimal,
    private val solution: (x: BigDecimal, c: BigDecimal) -> BigDecimal,
    val strEquation: String,
    private val strSolution: (c: BigDecimal) -> String
) {
    private lateinit var c: BigDecimal

    fun computeCoef(x0: BigDecimal, y0: BigDecimal) {
        c = coef(x0, y0)
    }

    fun compute(x: BigDecimal): BigDecimal = solution(x, c)

    fun solutionToString() = strSolution(c)
}

val equations = listOf(
    DiffEquation(
        { x, y -> y + (BigDecimal.ONE + x) * y.pow(2) },
        { x0, y0 ->
            -exp(x0.toDouble()).toBigDecimal(MathContext.DECIMAL32) *
                    (BigDecimal.ONE.divide(y0, MathContext.DECIMAL32) + x0)
        },
        { x, c ->
            -exp(x.toDouble()).toBigDecimal(MathContext.DECIMAL32)
                .divide(x * exp(x.toDouble()).toBigDecimal(MathContext.DECIMAL32) + c, MathContext.DECIMAL32)
        },
        "y' = y + (1 + x) * y^2",
        { c -> "y = -e^x / (x * e^x + $c" }
    )
)
