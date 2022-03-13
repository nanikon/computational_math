import method.Method
import model.IntegrateFunction
import util.chooseFunction
import util.chooseMethod
import util.parseApprox
import util.parseInterval
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.pow

/**
 * @author Natalia Nikonova
 */

fun main() {
    val function = chooseFunction()
    val interval = parseInterval()
    val approximation = parseApprox()
    val method = chooseMethod()
    calculateIntegral(interval.first, interval.second, approximation, function, method)
}

fun calculateIntegral(a: BigDecimal, b: BigDecimal, approximation: BigDecimal, function: IntegrateFunction, method: Method) {
    var n = 4
    var currentValue = method.calculateSquare(a, b, getH(a, b, n), function)
    var errorRate: BigDecimal
    do {
        n *= 2
        val lastValue = currentValue
        currentValue = method.calculateSquare(a, b, getH(a, b, n), function)
        errorRate = currentValue
            .minus(lastValue)
            .divide((2.0.pow(method.accuracyOrder()) - 1).toBigDecimal(), MathContext.DECIMAL64)
            .abs()
        println("При разбиении на $n частей значение интеграла $currentValue, погрешность $errorRate")
    } while (errorRate > approximation)
    println("\nУра, мы приблизились к нужной точности!")
    println("Ответ: значение интеграла - $currentValue, число разбиения интервала интегрирования $n")
}

fun getH(a: BigDecimal, b: BigDecimal, n: Int) : BigDecimal = b.minus(a).divide(n.toBigDecimal(), MathContext.DECIMAL64)