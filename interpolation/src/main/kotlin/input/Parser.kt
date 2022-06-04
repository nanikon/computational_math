package input

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
object Parser {
    fun parseTable(): List<Pair<BigDecimal, BigDecimal>> {
        val result = mutableListOf<Pair<BigDecimal, BigDecimal>>()
        val n = parseCount()
        for (i in 1..n) {
            result.add(parsePair(i))
        }
        return result
    }

    fun parseX(predict: (x: BigDecimal) -> Boolean): BigDecimal {
        while (true) {
            println("Введите х для подсчета приблизительного значения функции в нем")
            val result = readLine()?.trim()?.toBigDecimalOrNull()
            if (result == null) {
                println("Вы ничего не ввели")
            } else if (predict(result)) { return result }
            println(", попробуйте ещё раз")
        }
    }

    fun parseCount(): Int {
        println("Введите количество узлов интерполяции")
        while (true) {
            val result = readLine()?.trim()?.toIntOrNull()
            if (result != null && result > 2 && result < 13) {
                return result
            }
            println("Некорректный ввод. Кол-во узлов должно быть целым числом в интервале [3, 12]. Попробуйте ещё раз")
        }
    }

    private fun parsePair(i: Int) : Pair<BigDecimal, BigDecimal> {
        println("Введите коориданту $i-ой узла по х, затем пробел, затем координату узла по y")
        while (true) {
            val input = readLine()?.trim()?.split(" ")
            if (input == null) {
                println("Вы ничего не ввели. Попробуйте ещё раз")
                continue
            } else if (input.size != 2) {
                println("Вы ввели не два элемента. Попробуйте ещё раз")
                continue
            }
            val x = input[0].toBigDecimalOrNull()
            val y = input[1].toBigDecimalOrNull()
            when {
                x != null && y != null -> return Pair(x, y)
                x == null && y == null -> println("Оба введенных элемента не являются числами. Попробуйте ещё раз")
                x == null -> println("Первый элемент не является числом. Попробуйте ещё раз")
                else -> println("Второй элемент не является числом. Попробуйте ещё раз")
            }
        }
    }

}
