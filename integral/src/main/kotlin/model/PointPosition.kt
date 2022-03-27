package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
enum class PointPosition(
   val value: (BigDecimal, BigDecimal) -> BigDecimal
) {
   START({x: BigDecimal, eps: BigDecimal -> x.plus(eps)}),
   MID({x: BigDecimal, _: BigDecimal -> x}),
   END({x: BigDecimal, eps: BigDecimal -> x.minus(eps)})
}