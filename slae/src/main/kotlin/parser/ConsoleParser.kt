package parser

import Matrix
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class ConsoleParser : Parser {
    override fun parseDim(): Int {
        println("Введите размерность матрицы:")
        while (true) {
            val result = readLine()?.trim()
            val intResult = result?.toIntOrNull()
            if (intResult != null && intResult > 0 && intResult <= 20) { return intResult }
            println("Некорректный ввод. размерность должна быть натуральным числом, не большим 20. Попробуйте ещё раз")
        }
    }

    override fun parseMatrix(n: Int): Matrix {
        println("Вводите матрицу построчно, элементы в строке должны быть строго через один пробел")
        val result = Matrix(n)
        for (i in 0 until n) {
            while (true) {
                try {
                    val row = readLine()!!.split(" ").map { elem -> elem.toBigDecimal() } as MutableList<BigDecimal>
                    if (row.size != n + 1) { throw Exception() }
                    result.setRow(i, row)
                    break
                } catch (ex: Throwable) {
                    println("Введенная строка некорректна! Попробуйте ещё раз")
                }
            }
        }
        return result
    }
}