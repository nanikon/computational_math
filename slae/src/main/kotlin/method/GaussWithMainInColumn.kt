package method

import Matrix

/**
 * @author Natalia Nikonova
 */
class GaussWithMainInColumn(private val matrix: Matrix) : DirectMethod {
    private fun compute() {
        for (i in 0 until matrix.dim) {
            val indexRowWithMaxElem = maxElemInColumn(i)
            if (indexRowWithMaxElem != i) { matrix.swapRows(i, indexRowWithMaxElem) }
        }
    }

    private fun maxElemInColumn(index: Int) : Int {
        var maxElem = matrix.getElem(index, index)
        var result = index
        for (j in index + 1 until matrix.dim) {
            val curElem = matrix.getElem(j, index)
            if (curElem > maxElem) {
                maxElem = curElem
                result = j
            }
        }
        return result
    }


    override fun getDeterminant(): Int {
        TODO("Not yet implemented")
    }

    override fun getTriangularMatrix(): Matrix {
        TODO("Not yet implemented")
    }

    override fun getSolution(): List<Double> {
        TODO("Not yet implemented")
    }

    override fun getInconsistencies(): List<Double> {
        TODO("Not yet implemented")
    }
}