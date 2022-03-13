package method

import model.IntegrateFunction
import util.sum
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class TrapezeMethod : Method {
    override fun calculateSquare(a: BigDecimal, b: BigDecimal, h: BigDecimal, function: IntegrateFunction): BigDecimal {
        val values = getValues(a, b, h, function)
        val majorPartSum = values.slice(1 until values.lastIndex).sum()
        return values.first()
            .plus(values.last())
            .divide(BigDecimal(2), MathContext.DECIMAL64)
            .plus(majorPartSum)
            .multiply(h)
    }

    private fun getValues(a: BigDecimal, b: BigDecimal, h: BigDecimal, function: IntegrateFunction) : List<BigDecimal> {
        val result = mutableListOf<BigDecimal>()
        var added = a
        do {
            result.add(function.calculate(added))
            added = added.plus(h)
        } while (added <= b)
        return result
    }

    override fun accuracyOrder(): Int = 2

    override fun toString(): String = "Метод трапеций"
}