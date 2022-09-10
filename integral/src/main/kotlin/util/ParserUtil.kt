package util

import model.IntegrateFunction
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

fun parseInterval(function: IntegrateFunction) : Pair<BigDecimal, BigDecimal> {
    while (true) {
        val a = parseBigDecimal("левую границу интервала", "") { true }
        val b = parseBigDecimal("правую границу интервала", "больше левой границы $a") { x -> x > a }
        if (!function.isDivergent(a, b)) { return Pair(a, b) }
        println("На введенном интервале невозможно посчитать интеграл. Попробуйте ещё раз")
    }

}