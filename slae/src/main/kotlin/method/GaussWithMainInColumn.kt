package method

import Matrix
import kotlin.math.abs

/**
 * @author Natalia Nikonova
 */
class GaussWithMainInColumn(private val matrix: Matrix) : DirectMethod {
    private var countReplace = 0
    init {
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
                val multiplier = -1 * matrix.getElem(j, i) / matrix.getElem(i, i)
                matrix.addMultipliedFirstToSecond(i, j, multiplier)
            }
        }
    }

    private fun maxElemInColumn(index: Int) : Int {
        var maxElem = matrix.getElem(index, index)
        var result = index
        for (j in index + 1 until matrix.dim) {
            val curElem = matrix.getElem(j, index)
            if (abs(curElem) > abs(maxElem)) {
                maxElem = curElem
                result = j
            }
        }
        return result
    }


    override fun getDeterminant(): Double {
        var result = if (countReplace % 2 == 0) { 1.0 } else { -1.0 }
        for (i in 0 until matrix.dim) { result *= matrix.getElem(i, i) }
        return result
    }

    override fun getTriangularMatrix(): Matrix {
        return matrix
    }

    override fun getSolution(): List<Double> {
        TODO("Not yet implemented")
    }

    override fun getInconsistencies(): List<Double> {
        TODO("Not yet implemented")
    }
}