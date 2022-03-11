package parser

import methods.single.SingleMethod
import methods.system.SystemMethod
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class ConsoleParser : Parser {
    private fun parseInterval(method: SingleMethod) {
        while(true) {
            val a = parseA("левую границу интервала")
            val b = parseB(a)

            if (method.setAndVerifyData(a, b)) { break }
            println("Введенный интервал некорректен - на нем либо не ровно один корень, либо он слишком большой. Попробуйте ещё раз")
        }
    }

    private fun parseA(obj: String) : BigDecimal {
        println("Введите $obj:")
        while (true) {
            val result = readLine()?.trim()?.toBigDecimal()
            if (result != null) {
                return result
            }
            println("Некорректный ввод: $obj должна быть дробным числом. Попробуйте ещё раз")
        }
    }

    private fun parseB(a: BigDecimal) : BigDecimal {
        println("Введите правую границу интервала:")
        while (true) {
            val result = readLine()?.trim()?.toBigDecimal()
            if (result != null && result > a) {
                return result
            }
            println("Некорректный ввод. Правая граница должна быть дробным числом большим левой. Попробуйте ещё раз")
        }
    }

    private fun parseInitValues(method: SystemMethod) {
        while(true) {
            val x0 = parseA("приближение переменной Х")
            val y0 = parseA("приближение переменной Y")

            if (method.setAndVerifyData(x0, y0)) { break }
            println("Введенный начальные приближения некоррекны. Попробуйте ещё раз")
        }
    }

    private fun parseApprox(): BigDecimal {
        println("Введите погрешность:")
        while (true) {
            val result = readLine()?.trim()?.toBigDecimal()
            if (result != null && result < BigDecimal.ONE) { return result }
            println("Некорректный ввод: погрешность должна быть дробным числом и лучше меньше 1. Попробуйте ещё раз")
        }
    }

    override fun parseIntervalAndApprox(method: SingleMethod) : BigDecimal {
        parseInterval(method)
        return parseApprox()
    }

    override fun parseInitValuesAndApprox(method: SystemMethod) : BigDecimal {
        parseInitValues(method)
        return parseApprox()
    }
}
