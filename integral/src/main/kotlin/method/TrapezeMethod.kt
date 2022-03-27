package method

import model.IntegrateFunction
import model.PointPosition
import util.sum
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class TrapezeMethod : Method {
    override fun calculateSquare(
        a: BigDecimal,
        b: BigDecimal,
        h: BigDecimal,
        function: IntegrateFunction,
        approximation: BigDecimal
    ): BigDecimal {
        val values = getValues(a, b, h, function, approximation)
        val majorPartSum = values.slice(1 until values.lastIndex).sum()
        return values.first()
            .plus(values.last())
            .divide(BigDecimal(2), MathContext.DECIMAL64)
            .plus(majorPartSum)
            .multiply(h)
    }

    private fun getValues(
        a: BigDecimal,
        b: BigDecimal,
        h: BigDecimal,
        function: IntegrateFunction,
        approximation: BigDecimal
    ) : List<BigDecimal> {
        val result = mutableListOf<BigDecimal>()
        result.add(function.calculate(a, PointPosition.START, approximation))
        var added = a.plus(h)
        while (added < b.minus(h.divide(BigDecimal(2), MathContext.DECIMAL64))) {
            result.add(function.calculate(added, PointPosition.MID, approximation))
            added = added.plus(h)
        }
        result.add(function.calculate(b, PointPosition.END, approximation))
        return result
    }

    override fun accuracyOrder(): Int = 2

    override fun toString(): String = "Метод трапеций"
}
