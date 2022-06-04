package input.builder

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface Builder {
    fun build(): Pair<BigDecimal, List<Pair<BigDecimal, BigDecimal>>>
}
