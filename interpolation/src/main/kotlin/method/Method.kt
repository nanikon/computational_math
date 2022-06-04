package method

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface Method {
    fun compute(x: BigDecimal, table: List<Pair<BigDecimal, BigDecimal>>): Pair<BigDecimal, BigDecimal>
}
