package method

import Matrix
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class GaussWithMainInColumn(private val matrix: Matrix) : DirectMethod {
    private var countReplace = 0
    private var solution = mutableListOf<BigDecimal>()
    init {
        for (i in 0 until matrix.dim) { solution.add(BigDecimal(0)) }
        compute()
    }

    private fun compute() {
        for (i in 0 until matrix.dim) {
            val indexRowWithMaxElem = maxElemInColumn(i)
            if (indexRowWithMaxElem != i) {
                matrix.swapRows(i, indexRowWithMaxElem)
                countReplace++
            }
            for (j in i + 1 until matrix.dim) {
                val multiplier = -matrix.getElem(j, i).divide(matrix.getElem(i, i))
                matrix.addMultipliedFirstToSecond(i, j, multiplier)
            }
        }
        for (i in matrix.dim - 1 downTo 0) {
            val x = (matrix.getElem(i, matrix.dim) - matrix.multiplyRowByVectorAndSum(i, solution)) / matrix.getElem(i, i)
            solution[i] = x
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
        return result
    }

    override fun getTriangularMatrix(): Matrix {
        return matrix
    }

    override fun getSolution(): List<BigDecimal> {
        return solution
    }

    override fun getInconsistencies(): List<BigDecimal> {
        TODO("Not yet implemented")
    }
}