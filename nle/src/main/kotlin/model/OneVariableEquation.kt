package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class OneVariableEquation(
    val function: (x: BigDecimal) -> BigDecimal,
    val firstDerivative: (x: BigDecimal) -> BigDecimal,
    private val stringForm: String
) {
    override fun toString(): String = stringForm
}

val equations = listOf(
    OneVariableEquation(
        { x -> x.pow(3).multiply(BigDecimal(1.8))
            .minus(x.pow(2).multiply(BigDecimal(2.47)))
            .minus(x.multiply(BigDecimal(5.53)))
            .plus(BigDecimal(1.539))
        },
        { x -> x.pow(2).multiply(BigDecimal(5.4))
            .minus(x.multiply(BigDecimal(4.94)))
            .minus(BigDecimal(5.53))
        },
        "1,8x^3 - 2,47x^2 - 5,53x + 1,539"
    )
)
