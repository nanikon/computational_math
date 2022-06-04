package input.builder

import input.Parser
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class TableBuilder : Builder {
    override fun build(): Pair<BigDecimal, List<Pair<BigDecimal, BigDecimal>>> {
        while (true) {
            val table = Parser.parseTable().sortedBy { it.first }
            if (!checkTable(table)) {
                println("Введенные узлы не равносторонние. Попробуйте ещё раз")
                continue
            }
            val x = Parser.parseX{ x ->
                when {
                    x in table.map { it.first } -> false.also {
                        print("Введенный x совпадает с известным значением функции")
                    }
                    x < table.minOf { it.first } || x > table.maxOf { it.first } -> false.also {
                        print("Введенный x совпадает с известным значением функции")
                    }
                    else -> true
                }
            }
            return Pair(x, table)
        }
    }

    private fun checkTable(table: List<Pair<BigDecimal, BigDecimal>>): Boolean {
        val h = table[1].first - table[0].first
        var result = true
        for (i in 1..table.size - 2) {
            result = result && (table[i + 1].first - table[i].first == h)
        }
        return result
    }
}
