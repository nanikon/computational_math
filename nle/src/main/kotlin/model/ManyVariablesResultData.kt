package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
data class ManyVariablesResultData(
   val root: List<BigDecimal>,
   val errors: List<BigDecimal>,
   val countIteration: Int
)
