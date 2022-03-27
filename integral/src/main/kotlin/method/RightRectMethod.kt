package method

import model.IntegrateFunction
import model.PointPosition
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class RightRectMethod : RectMethod() {
    override fun getValues(
        a: BigDecimal,
        b: BigDecimal,
        h: BigDecimal,
        function: IntegrateFunction,
        approximation: BigDecimal
    ): List<BigDecimal> {
        val result = mutableListOf<BigDecimal>()
        var added = a.plus(h)
        while (added < b.minus(h.divide(BigDecimal(2), MathContext.DECIMAL64))) {
            result.add(function.calculate(added, PointPosition.MID, approximation))
            added = added.plus(h)
        }
        result.add(function.calculate(b, PointPosition.END, approximation))
        return result
    }

    override fun toString(): String = "Метод правых прямоугольников"
}
