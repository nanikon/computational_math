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

val functions = listOf(
    IntegrateFunction(
        { x -> x.pow(3).multiply(BigDecimal(3))
            .minus(x.pow(2).multiply(BigDecimal(4)))
            .minus(x.multiply(BigDecimal(5)))
            .minus(BigDecimal(16))
        },
        "3x^3 - 4x^2 + 5x - 16"
    ),
    IntegrateFunction(
        { x -> x },
        "x"
    )
)