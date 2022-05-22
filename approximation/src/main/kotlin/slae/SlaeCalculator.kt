package slae

import java.math.BigDecimal
import java.math.MathContext
import kotlin.system.exitProcess

/**
 * @author Natalia Nikonova
 */
object SlaeCalculator {
    fun calculate(matrix: Matrix): List<BigDecimal> {
        val solution = mutableListOf<BigDecimal>()
        (1..matrix.dim).forEach { _ -> solution.add(BigDecimal(0)) }

        for (i in 0 until matrix.dim) {
            val indexRowWithMaxElem = matrix.maxElemInColumn(i)
            if (indexRowWithMaxElem != i) {
                matrix.swapRows(i, indexRowWithMaxElem)
                println("Перестановка строк ${i + 1} и ${indexRowWithMaxElem + 1}. Получена матрица:")
                matrix.printMatrix()
            }
            try {
                if (matrix.getElem(i, i) == BigDecimal(0)) { throw ArithmeticException() }
                for (j in i + 1 until matrix.dim) {
                    val multiplier = -matrix.getElem(j, i).divide(matrix.getElem(i, i), MathContext.DECIMAL64)
                    matrix.addMultipliedFirstToSecond(i, j, multiplier)
                }
            } catch (ex: ArithmeticException) {
                println("Максимальный по модулю ведущий элемент 0 в строке ${i + 1}. У системы не будет единственного решения, завершение программы")
                exitProcess(-1)
            }
            println("Из матрицы выражен элемент ${i + 1}. Получена матрица:")
            matrix.printMatrix()
        }

        for (i in matrix.dim - 1 downTo 0) {
            val x = (matrix.getElem(i, matrix.dim).minus(matrix.multiplyRowByVectorAndSum(i, solution))).divide(matrix.getElem(i, i), MathContext.DECIMAL64)
            solution[i] = x
        }
        return solution
    }
}
