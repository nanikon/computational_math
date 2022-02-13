/**
 * @author Natalia Nikonova
 */
class Matrix (private val dim: Int) {
    private var data : MutableList<MutableList<Int>> = mutableListOf()

    init {
        val row = mutableListOf<Int>()
        for (i in 0..dim) { row.add(0) } //  длину на 1 шире т.к. есть столбец В
        for (i in 1..dim) {
            data.add(row)
        }
    }

    fun setRow(index: Int, row: MutableList<Int>) {
        data[index] = row
    }

    fun printMatrix() {
        for (row in data) {
            println(row.joinToString(separator = " "))
        }
    }
}