package method

import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class NewtonMethod : Method {
    override fun compute(x: BigDecimal, table: List<Pair<BigDecimal, BigDecimal>>): Pair<BigDecimal, BigDecimal> {
        println("\nМетод Ньютона")
        val xs = table.map { it.first }
        val ys = table.map { it.second }
        val finiteDifferences = mutableListOf(ys)
        println("Таблица конечных разностей")
        println(ys.joinToString(separator = "\t"))
        for (i in 0 until ys.size - 1) {
            val row = mutableListOf<BigDecimal>()
            for (j in 0 until ys.size - 1 - i) {
                row.add(finiteDifferences[i][j + 1] - finiteDifferences[i][j])
            }
            finiteDifferences.add(row)
            println(row.joinToString(separator = "\t"))
        }
        val middle = (xs[0] + xs.last()).divide(BigDecimal(2), MathContext.DECIMAL32)
        val h = xs[1] - xs[0]
        var y = BigDecimal.ZERO
        print("Значение многочлена Ньютона при x=$x: ")
        if (x < middle) {
            // формула вперед
            print("!")
            val a = xs.maxOf { if (it < x) it else xs[0] }
            val t = (x - a).divide(h, MathContext.DECIMAL32)
            val i = xs.indexOf(a)
            var factor = BigDecimal.ONE
            for (j in finiteDifferences[i].indices) {
                if (j != 0) { print(" + ") }
                print("${finiteDifferences[j][i]} * $factor")
                y += finiteDifferences[j][i] * factor
                factor *= (t - j.toBigDecimal()).divide((j + 1).toBigDecimal(), MathContext.DECIMAL32)
            }
        } else {
            // формула назад
            val a = xs.minOf { if (it > x) it else xs.last() }
            val t = (x - a).divide(h, MathContext.DECIMAL32)
            val i = xs.indexOf(a)
            var factor = BigDecimal.ONE
            for (j in i downTo 0) {
                if (j != i) { print(" + ") }
                val c = i - j
                print("${finiteDifferences[c][j]} * $factor")
                y += finiteDifferences[c][j] * factor
                factor *= (t + c.toBigDecimal()).divide((c + 1).toBigDecimal(), MathContext.DECIMAL32)
            }
        }
        println(" = $y")
        return Pair(x, y)
    }
}
