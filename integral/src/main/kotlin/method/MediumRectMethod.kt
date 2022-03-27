package method

import model.IntegrateFunction
import model.PointPosition
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class MediumRectMethod : RectMethod() {
    override fun getValues(
        a: BigDecimal,
        b: BigDecimal,
        h: BigDecimal,
        function: IntegrateFunction,
        approximation: BigDecimal
    ): List<BigDecimal> {
        val result = mutableListOf<BigDecimal>()
        var added = a.plus(h.divide(BigDecimal(2), MathContext.DECIMAL64))
        do {
            result.add(function.calculate(added, PointPosition.MID, approximation))
            added = added.plus(h)
        } while (added < b)
        return result
    }

    override fun toString(): String = "Метод средних прямоугольников"
}
