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
    val interval = parseInterval(function)
    val approximation = parseApprox()
    val method = chooseMethod()
    calculateIntegral(interval.first, interval.second, approximation, function, method)
}

fun calculateIntegral(a: BigDecimal, b: BigDecimal, approximation: BigDecimal, function: IntegrateFunction, method: Method) {
    val intervals = mutableListOf<Pair<BigDecimal, BigDecimal>>()
    var startPoint = a
    for (point in function.valueBreakPoints) {
        if (point in a..b)  {
            intervals.add(Pair(startPoint, point))
            startPoint = point
        }
    }
    intervals.add(Pair(startPoint, b))
    var n = 4
    var currentValue = calculateSquares(intervals, function, method, n)
    var errorRate: BigDecimal
    do {
        n *= 2
        val lastValue = currentValue
        currentValue = calculateSquares(intervals, function, method, n)
        errorRate = currentValue
            .minus(lastValue)
            .divide((2.0.pow(method.accuracyOrder()) - 1).toBigDecimal(), MathContext.DECIMAL64)
            .abs()
        println("При разбиении на $n частей значение интеграла $currentValue, погрешность $errorRate")
    } while (errorRate > approximation)
    println("\nУра, мы приблизились к нужной точности!")
    println("Ответ: значение интеграла - $currentValue, число разбиения интервала интегрирования $n")
}

fun calculateSquares(
    intervals: List<Pair<BigDecimal, BigDecimal>>,
    function: IntegrateFunction,
    method: Method,
    n: Int
) = intervals.sumOf { interval ->
    method.calculateSquare(interval.first, interval.second, getH(interval.first, interval.second, n), function)
}

fun getH(a: BigDecimal, b: BigDecimal, n: Int) : BigDecimal = b.minus(a).divide(n.toBigDecimal(), MathContext.DECIMAL64)