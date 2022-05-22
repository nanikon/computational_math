package approximation

import slae.Matrix
import slae.SlaeCalculator
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
abstract class Approximation {
    abstract fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Triple<String, BigDecimal, Map<String, List<BigDecimal>>>?

    // data - координаты точек, n - степень полинома аппрокимации (1 - линейная, 2 - квадратичная и т.д.)
    protected fun convertToMatrixAndSolve(data: List<Pair<BigDecimal, BigDecimal>>, n: Int): List<BigDecimal> {
        val variables = listOf(BigDecimal(data.size)) +
                (1..2 * n).map { i -> data.sumOf { coords -> coords.first.pow(i) } }
        val bColumn = (0..n).map { i -> data.sumOf { coords -> coords.second * coords.first.pow(i) } }
        println((variables + bColumn).joinToString(separator = " "))
        val matrix = Matrix(n + 1).apply {
            (0..n).forEach{ i ->
                setRow(i, (variables.subList(i, i + n + 1) + bColumn[i]) as MutableList<BigDecimal>)
            }
        }
        println("Получена матрица:")
        matrix.printMatrix()
        return SlaeCalculator.calculate(matrix)
            .also { println("Решение матрицы: " + it.joinToString(separator = " ")) }
    }

    protected fun calculateStandardDeviation(
        data: List<Pair<BigDecimal, BigDecimal>>,
        function: (BigDecimal) -> BigDecimal
    ) : BigDecimal =
        data.sumOf { coords -> (function(coords.first) - coords.second).pow(2).also { print("$it ") } }
            .also { println("\nСумма квадратов отклонений: $it") }
            .divide(BigDecimal(data.size), MathContext.DECIMAL64)
            .sqrt(MathContext.DECIMAL64)
            .also { println("СКО: $it") }

    protected fun toStr(value:BigDecimal): String =
        if (value >= BigDecimal.ZERO) { "+ $value" }
        else { "- ${value.abs()}" }

    protected fun getGraphPoints(
        data: List<Pair<BigDecimal, BigDecimal>>,
        function: (BigDecimal) -> BigDecimal
    ): Map<String, List<BigDecimal>> {
        val leftBorder = data.minOf { it.first }
        val rightBorder = data.maxOf { it.first }
        val h = (rightBorder - leftBorder).divide(BigDecimal.TEN, MathContext.DECIMAL64)
        val left = (leftBorder - h).multiply(BigDecimal(1000)).toInt()
        val right = (rightBorder + h).multiply(BigDecimal(1000)).toInt()
        val x = (left..right).toList().map{ it.toBigDecimal().divide(BigDecimal(1000), MathContext.DECIMAL64) }
        val y = x.map { function(it) }
        return mapOf(
            "x" to x,
            "y" to y
        )
    }
}
