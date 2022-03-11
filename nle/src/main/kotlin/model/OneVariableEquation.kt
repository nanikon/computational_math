package model

import java.math.BigDecimal
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

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
        { x ->
            x.pow(3).multiply(BigDecimal(1.8))
            .minus(x.pow(2).multiply(BigDecimal(2.47)))
            .minus(x.multiply(BigDecimal(5.53)))
            .plus(BigDecimal(1.539))
        },
        { x ->
            x.pow(2).multiply(BigDecimal(5.4))
            .minus(x.multiply(BigDecimal(4.94)))
            .minus(BigDecimal(5.53))
        },
        "1,8x^3 - 2,47x^2 - 5,53x + 1,539"
    ),
    OneVariableEquation(
        { x ->
            x.pow(2)
            .plus(sin(x.toDouble()).toBigDecimal())
            .minus(BigDecimal(3))
        },
        { x ->
            x.multiply(BigDecimal(2))
            .plus(cos(x.toDouble()).toBigDecimal())
        },
        "x^2 + sin(x) - 3"
    ),
    OneVariableEquation(
        { x ->
            exp(-1 * x.toDouble()).toBigDecimal()
            .minus(exp(x.toDouble()).toBigDecimal())
        },
        { x ->
            (-1 * exp(-x.toDouble())).toBigDecimal()
            .minus(exp(x.toDouble()).toBigDecimal())
        },
        "e^-x - e^x"
    ),
    OneVariableEquation(
        { x ->
            x.pow(3)
            .minus(x.pow(2).multiply(BigDecimal(1.8)))
            .minus(x.multiply(BigDecimal(8.64)))
            .plus(BigDecimal(17.28))
        },
        { x ->
            x.pow(2).multiply(BigDecimal(3))
            .minus(x.multiply(BigDecimal(3.6)))
            .minus((BigDecimal(8.64)))
        },
        "x^3 - 1,8x^2 - 8,64x + 17,28"
    ),
    OneVariableEquation(
        { x ->
            x.pow(10)
            .minus(x.pow(7))
            .minus(x.pow(3).multiply(BigDecimal(5)))
            .plus(x.pow(2).multiply(BigDecimal(2)))
            .plus(x)
        },
        { x -> x.pow(9).multiply(BigDecimal.TEN)
            .minus(x.pow(6).multiply(BigDecimal(7)))
            .minus(x.pow(2).multiply(BigDecimal(15)))
            .plus(x.multiply(BigDecimal(4)))
            .plus(BigDecimal.ONE)
        },
        "x^10 - x^7  - 5x^3 + 2x^2 + x"
    )
)
