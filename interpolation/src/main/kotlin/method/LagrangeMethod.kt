package method

import util.multiplyOf
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class LagrangeMethod : Method {
    override fun compute(x: BigDecimal, table: List<Pair<BigDecimal, BigDecimal>>): Pair<BigDecimal, BigDecimal> {
        println("\nМетод Лагранжа")
        val xs = table.map { it.first }
        val differences = mutableListOf<List<BigDecimal>>()
        println("Таблица попарных разностей")
        for (i in xs.indices) {
            val row = mutableListOf<BigDecimal>()
            for (j in 0 until i) {
                row.add(xs[j] - xs[i])
            }
            differences.add(row)
            println(row.joinToString(separator = "\t"))
        }
        val multiply = xs.multiplyOf { number -> x - number }
        println("Общая часть числителя $multiply")

        val denominators = (xs.indices).map { i ->
            differences[i].multiplyOf { number -> -number } *
                    (i + 1 until xs.size).map { j -> differences[j][i] }.multiplyOf { it } *
                    (x - xs[i])
        }
        println("Знаменатели: ${denominators.joinToString(separator=" ")}")
        val coef = (xs.indices).map { i ->
            multiply.divide(denominators[i], MathContext.DECIMAL32)
        }

        val y = (xs.indices).map { i -> coef[i] * table[i].second }.sumOf { it }
        coef.mapIndexed { index, number -> "$number * y_$index" }
            .joinToString(separator = " + ")
            .also { println("Значение многочлена Лагранжа при x=$x: $it = $y") }
        return Pair(x, y)
    }
}
