package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
data class ResultData(
    val root: BigDecimal,
    val valueRoot: BigDecimal,
    val countIteration: Int
)
