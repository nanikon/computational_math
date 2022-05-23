package parser

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface Parser {
   fun parse(): List<Pair<BigDecimal, BigDecimal>>
}
