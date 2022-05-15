package slae

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * @author Natalia Nikonova
 */
class Matrix (val dim: Int) {
    private var data : MutableList<MutableList<BigDecimal>> = mutableListOf()

    init {
        val row = mutableListOf<BigDecimal>()
        for (i in 0..dim) { row.add(BigDecimal(0)) } //  длину на 1 шире т.к. есть столбец В
        for (i in 1..dim) {
            data.add(row)
        }
    }

    fun setRow(index: Int, row: MutableList<BigDecimal>) {
        data[index] = row
    }

    fun getElem(row: Int, column: Int) : BigDecimal {
        return data[row][column]
    }

    fun swapRows(i: Int, j: Int) {
        val tmpRow = data[i].toMutableList()
        data[i] = data[j].toMutableList()
        data[j] = tmpRow
    }

    fun addMultipliedFirstToSecond(first: Int, second: Int, multiplier: BigDecimal) {
        for (i in 0 .. dim) {
            data[second][i] = data[second][i].add(multiplier.multiply(data[first][i], MathContext.DECIMAL64))
        }
    }

    fun multiplyRowByVectorAndSum(index: Int, vector: List<BigDecimal>) : BigDecimal {
        return data[index].foldIndexed(BigDecimal(0)) { idx, acc, elem ->
            if (idx != dim) acc.add(elem.multiply(vector[idx]), MathContext.DECIMAL64)
            else acc
        }
    }

    fun printMatrix() {
        for (row in data) {
            println(row.joinToString(separator = " "){ "%4.5f".format(it.setScale(5, RoundingMode.HALF_UP )) })
        }
    }

    fun maxElemInColumn(index: Int) : Int {
        val maxElem = data.drop(index).maxOf { row -> row[index].abs() }
        return data.drop(index).indexOfFirst { row -> row[index].abs() == maxElem }
    }
}
