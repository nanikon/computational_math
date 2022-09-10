package method

import model.IntegrateFunction
import model.PointPosition
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class LeftRectMethod : RectMethod() {
    override fun getValues(
        a: BigDecimal,
        b: BigDecimal,
        h: BigDecimal,
        function: IntegrateFunction,
        approximation: BigDecimal
    ): List<BigDecimal> {
        val result = mutableListOf<BigDecimal>()
        var added = a
        result.add(function.calculate(added, PointPosition.START, approximation))
        do {
            added = added.plus(h)
            result.add(function.calculate(added, PointPosition.MID, approximation))
        } while (added < b)
        return result
    }

    override fun toString(): String = "Метод левых прямоугольников"
}
