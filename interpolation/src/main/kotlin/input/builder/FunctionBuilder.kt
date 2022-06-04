package input.builder

import input.InputFunction
import input.Parser
import util.chooseFunction
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author Natalia Nikonova
 */
class FunctionBuilder : Builder {
    override fun build(): Pair<BigDecimal, List<Pair<BigDecimal, BigDecimal>>> {
        val function = chooseFunction()
        val x = Parser.parseX { input ->
            function.isRav(input).also {
                if (!it) print("Введенный x не входит в ОДЗ заданной функции")
            }
        }
        val m = Parser.parseCount()
        val xs = getXs(x, m, function)
        val table = xs.map { Pair(it, function.function(it)) }
        return Pair(x, table)
    }

    private fun getXs(x: BigDecimal, m: Int, function: InputFunction): List<BigDecimal> {
        val (a, b) = firstInterval(x)
        val h = b - a
        var c = (m - 2) / 2
        var d = (m - 2) / 2 + ((m - 2) % 2)
        var x0 = a - h * c.toBigDecimal()
        var xn = b + h * d.toBigDecimal()
        while (!function.isRav(x0)) {
            c--
            d++
            x0 = a - h * c.toBigDecimal()
            xn = b + h * d.toBigDecimal()
        }
        while (!function.isRav(xn)) {
            c++
            d--
            x0 = a - h * c.toBigDecimal()
            xn = b + h * d.toBigDecimal()
        }
        return (0 until m).map { x0 + h * it.toBigDecimal() }
    }

    private fun firstInterval(x: BigDecimal): Pair<BigDecimal, BigDecimal> {
        var n = 6
        var a = x
        var b = x
        while (a == b) {
            if (n == 0) {
                a -= BigDecimal.ONE
                b += BigDecimal.ONE
                break
            }
            a = x.setScale(n, RoundingMode.FLOOR)
            b = x.setScale(n, RoundingMode.CEILING)
            n--
        }
        return Pair(a, b)
    }
}
