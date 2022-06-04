package input

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.ln
import kotlin.math.sin

/**
 * @author Natalia Nikonova
 */
class InputFunction(
    val function: (BigDecimal) -> BigDecimal,
    val isRav: (BigDecimal) -> Boolean,
    private val stringForm: String
) {
    override fun toString(): String = stringForm
}

val functions = listOf(
    InputFunction(
        { x -> sin(x.toDouble()).toBigDecimal(MathContext.DECIMAL32) },
        { x -> true },
        "sin(x)"
    ),
    InputFunction(
        { x -> x.sqrt(MathContext.DECIMAL32) },
        { x -> x >= BigDecimal.ONE },
        "sqrt(x)"
    ),
    InputFunction(
        { x -> ln(x.toDouble()).toBigDecimal() },
        { x -> x > BigDecimal.ZERO },
        "ln(x)"
    )
)
