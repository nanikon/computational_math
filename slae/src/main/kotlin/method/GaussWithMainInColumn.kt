package method

import Matrix
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.system.exitProcess

/**
 * @author Natalia Nikonova
 */
class GaussWithMainInColumn(private val matrix: Matrix) : DirectMethod {
    private var countReplace = 0
    private var solution = mutableListOf<BigDecimal>()
    private var discrepancies = mutableListOf<BigDecimal>()
    init {
        for (i in 0 until matrix.dim) { solution.add(BigDecimal(0)) }
        compute()
    }

    private fun compute() {
        // Выборка главного элемента по столбцам и прямой ход Гаусса
        for (i in 0 until matrix.dim) {
            val indexRowWithMaxElem = maxElemInColumn(i)
            if (indexRowWithMaxElem != i) {
                matrix.swapRows(i, indexRowWithMaxElem)
                println("Перестановка строк ${i + 1} и ${indexRowWithMaxElem + 1}. Получена матрица:")
                matrix.printMatrix()
                countReplace++
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
        // Обратный ход Гаусса и получение решения
        for (i in matrix.dim - 1 downTo 0) {
            val x = (matrix.getElem(i, matrix.dim).minus(matrix.multiplyRowByVectorAndSum(i, solution))).divide(matrix.getElem(i, i), MathContext.DECIMAL64)
            solution[i] = x
        }
        // Рассчет неувязок
        for (i in 0 until matrix.dim) {
            discrepancies.add(matrix.getElem(i, matrix.dim).minus(matrix.multiplyRowByVectorAndSum(i, solution)))
        }
    }

    private fun maxElemInColumn(index: Int) : Int {
        var maxElem = matrix.getElem(index, index)
        var result = index
        for (j in index + 1 until matrix.dim) {
            val curElem = matrix.getElem(j, index)
            if (curElem.abs() > maxElem.abs()) {
                maxElem = curElem
                result = j
            }
        }
        return result
    }

    override fun getDeterminant(): BigDecimal {
        var result = if (countReplace % 2 == 0) { BigDecimal(1) } else { -BigDecimal(1) }
        for (i in 0 until matrix.dim) { result = result.multiply(matrix.getElem(i, i)) }
        return result.setScale(3, RoundingMode.HALF_UP)
    }

    override fun getTriangularMatrix(): Matrix {
        return matrix
    }

    override fun getSolution(): List<BigDecimal> {
        return solution
    }

    override fun getDiscrepancies(): List<BigDecimal> {
        return discrepancies
    }
}