package model

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.sin

/**
 * @author Natalia Nikonova
 */
class IntegrateFunction(
    private val function: (BigDecimal) -> BigDecimal,
    private val view: String,
    private val breakPoints: Set<BreakPoint>
) {
    val valueBreakPoints = mutableSetOf<BigDecimal>()

    init {
        for (point in breakPoints) {
            valueBreakPoints.add(point.x)
        }
    }

    fun isBreakPoint(x: BigDecimal) = valueBreakPoints.contains(x)

    fun isDivergent(a: BigDecimal, b: BigDecimal) = breakPoints.fold(false) { acc, elem ->
        if (elem.x in a..b)
            acc || elem.isDivergent.also {
                if (elem.isDivergent) { println("На интервала найдена несходящаяся точка разрыва ${elem.x}") }
            }
        else acc
    }

    fun calculate(x: BigDecimal) = function(x)

    override fun toString(): String = view
}

val functions = listOf(
    IntegrateFunction(
        { x -> x.pow(3).multiply(BigDecimal(3))
            .minus(x.pow(2).multiply(BigDecimal(4)))
            .plus(x.multiply(BigDecimal(5)))
            .minus(BigDecimal(16))
        },
        "3x^3 - 4x^2 + 5x - 16",
        emptySet()
    ),
    IntegrateFunction(
        { x -> sin(x.toDouble()).toBigDecimal(MathContext.DECIMAL64) },
        "sin(x)",
        emptySet()
    ),
    IntegrateFunction(
        { x -> BigDecimal.ONE.divide(x.sqrt(MathContext.DECIMAL64)) },
        "1/sqrt(x)",
        setOf(
            BreakPoint(BigDecimal.ZERO, false)
        )
    ),
    IntegrateFunction(
        { x -> BigDecimal.ONE.divide(BigDecimal.ONE.minus(x)) },
        "1/(1-x)",
        setOf(
            BreakPoint(BigDecimal.ONE, true)
        )
    )
)