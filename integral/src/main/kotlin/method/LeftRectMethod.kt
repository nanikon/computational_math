package method

import model.IntegrateFunction
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class LeftRectMethod : RectMethod() {
    override fun getValues(a: BigDecimal, b: BigDecimal, h: BigDecimal, function: IntegrateFunction): List<BigDecimal> {
        val result = mutableListOf<BigDecimal>()
        var added = a
        do {
            result.add(function.calculate(added))
            added = added.plus(h)
        } while (added < b)
        return result
    }
}