package method

import model.DiffEquation
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
object AdamsMethod {
    fun compute(
        diff: DiffEquation,
        xs: List<BigDecimal>, y4: List<BigDecimal>,
        h: BigDecimal,
        eps: BigDecimal
    ): List<BigDecimal> {
        val ys = y4.toMutableList()
        for (i in 4 until xs.size) {
            var newY = ys[i - 1] +
                    h.divide(BigDecimal(24), MathContext.DECIMAL32) * (
                    BigDecimal(55) * diff.function(xs[i - 1], ys[i - 1]) -
                            BigDecimal(59) * diff.function(xs[i - 2], ys[i - 2]) +
                            BigDecimal(37) * diff.function(xs[i - 3], ys[i - 3]) -
                            BigDecimal(9) * diff.function(xs[i - 4], ys[i - 4])
                    )
            var oldY: BigDecimal
            do {
                oldY = newY
                newY = ys[i - 1] + h.divide(BigDecimal(24), MathContext.DECIMAL32) * (
                        BigDecimal(9) * diff.function(xs[i], oldY) -
                                BigDecimal(19) * diff.function(xs[i - 1], xs[i - 1]) -
                                BigDecimal(5) * diff.function(xs[i - 2], ys[i - 2]) +
                                BigDecimal(1) * diff.function(xs[i - 3], ys[i - 3])
                        )
            } while ((oldY - newY).abs() >= eps)
            ys.add(newY)
        }
        return ys
    }
}
