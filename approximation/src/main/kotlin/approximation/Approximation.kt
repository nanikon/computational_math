package approximation

import slae.Matrix
import slae.SlaeCalculator
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
abstract class Approximation {
    abstract fun calculateCoefAndError(points: List<Pair<BigDecimal, BigDecimal>>): Pair<String, BigDecimal>

    // data - координаты точек, n - степень полинома аппрокимации (1 - линейная, 2 - квадратичная и т.д.)
    protected fun convertToMatrixAndSolve(data: List<Pair<BigDecimal, BigDecimal>>, n: Int): List<BigDecimal> {
        val variables = listOf(BigDecimal(data.size)) +
                (1..2 * n).map { i -> data.sumOf { coords -> coords.first.pow(i) } }
        val bColumn = (0..n).map { i -> data.sumOf { coords -> coords.second * coords.first.pow(i) } }
        val matrix = Matrix(n + 1).apply {
            (0..n).forEach{ i ->
                setRow(i, (variables.subList(i, i + n + 1) + bColumn[i]) as MutableList<BigDecimal>)
            }
        }
        return SlaeCalculator.calculate(matrix)
    }

    protected fun calculateStandardDeviation(
        data: List<Pair<BigDecimal, BigDecimal>>,
        function: (BigDecimal) -> BigDecimal
    ) : BigDecimal =
        data.sumOf { coords -> (function(coords.first) - coords.second).pow(2) }
            .divide(BigDecimal(data.size), MathContext.DECIMAL64)
            .sqrt(MathContext.DECIMAL64)
}