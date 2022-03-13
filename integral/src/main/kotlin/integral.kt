import method.Method
import model.IntegrateFunction
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.pow

/**
 * @author Natalia Nikonova
 */

fun main() {
    println("integral")
}

fun calculateIntegral(a: BigDecimal, b: BigDecimal, approximation: BigDecimal, function: IntegrateFunction, method: Method) {
    var n = 4
    var currentValue = method.calculateSquare(a, b, n, function)
    var errorRate: BigDecimal
    do {
        n *= 2
        val lastValue = currentValue
        currentValue = method.calculateSquare(a, b, n, function)
        errorRate = currentValue.minus(lastValue).divide((2.0.pow(method.accuracyOrder()) - 1).toBigDecimal(), MathContext.DECIMAL64)
        println("При разбиении на $n частей значение интеграла $currentValue, погрешность $errorRate")
    } while (errorRate > approximation)
    println("\nУра, мы приблизились к нужной точности!")
    println("Ответ: значение интеграла - $currentValue, число разбиения интервала интегрирования $n")
}