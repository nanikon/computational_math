package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
data class OneVariableResultData(
    val root: BigDecimal,
    val valueRoot: BigDecimal,
    val countIteration: Int
)
