package method

import model.IntegrateFunction
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class RightRectMethod : RectMethod() {
    override fun getValues(a: BigDecimal, b: BigDecimal, h: BigDecimal, function: IntegrateFunction): List<BigDecimal> {
        val result = mutableListOf<BigDecimal>()
        var added = a
        do {
            added = added.plus(h)
            result.add(function.calculate(added))
        } while (added < b)
        return result
    }
}