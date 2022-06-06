package method

import model.DiffEquation
import util.divideN
import java.math.BigDecimal

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
                    h.divideN(24) * (
                    BigDecimal(55) * diff.function(xs[i - 1], ys[i - 1]) -
                            BigDecimal(59) * diff.function(xs[i - 2], ys[i - 2]) +
                            BigDecimal(37) * diff.function(xs[i - 3], ys[i - 3]) -
                            BigDecimal(9) * diff.function(xs[i - 4], ys[i - 4])
                    )
            /*println("$newY = ${ys[i - 1]} +\n" +
                    "                    ${h.divideN(24)}3 * (\n" +
                    "                    BigDecimal(55) * ${diff.function(xs[i - 1], ys[i - 1]) }-\n" +
                    "                            BigDecimal(59) * ${diff.function(xs[i - 2], ys[i - 2])} +\n" +
                    "                            BigDecimal(37) * ${diff.function(xs[i - 3], ys[i - 3])} -\n" +
                    "                            BigDecimal(9) * ${diff.function(xs[i - 4], ys[i - 4])}")*/
            var oldY = newY
            var count = 0
            do {
                oldY = newY
                newY = ys[i - 1] +
                        h.divideN(24) * (
                        BigDecimal(9) * diff.function(xs[i], oldY) +
                                BigDecimal(19) * diff.function(xs[i - 1], ys[i - 1]) -
                                BigDecimal(5) * diff.function(xs[i - 2], ys[i - 2]) +
                                BigDecimal(1) * diff.function(xs[i - 3], ys[i - 3])
                        )
                // println("$i-й y: $oldY, $newY, ${oldY - newY}, $count")
                count++
                if (count == 100) {
                    println("Корректирующие значения расходятся. Нужная точность не достигается")
                    break
                }
            } while ((oldY - newY).abs() >= eps)
            println("$i-ый y - $count")
            ys.add(newY)
        }
        return ys
    }
}
