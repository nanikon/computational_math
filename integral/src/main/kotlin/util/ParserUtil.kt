package util

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */

private fun parseBigDecimal(obj: String, reason: String, predicate: (BigDecimal) -> Boolean) : BigDecimal {
    while(true) {
        println("Введите $obj")
        val result = readLine()?.trim()?.toBigDecimalOrNull()
        if (result != null && predicate(result)) { return result; }
        println("Некорректный ввод: $obj - это дробное число $reason. Попробуйте ещё раз")
    }
}

fun parseApprox() : BigDecimal {
    return parseBigDecimal("погрешность", "меньше единицы") { x -> x <= BigDecimal.ONE }
}

fun parseInterval() : Pair<BigDecimal, BigDecimal> {
    val a = parseBigDecimal("левую границу интервала", "") { true }
    val b = parseBigDecimal("правую границу интервала", "больше левой границы $a") { x -> x > a }
    return Pair(a, b)
}