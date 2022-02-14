package parser

import Matrix
import java.io.File
import java.io.FileNotFoundException
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * @author Natalia Nikonova
 */
class FileParser : Parser {
    override fun parseDim(): Int {
        while (true) {
            println("Введите имя файла:")
            try {
                val fileName = readLine()
                val dim = File(fileName!!).readLines()[0].toInt()
                println(dim)
                if (dim in 1..20) { return dim; }
            } catch (ex: FileNotFoundException) {
                println("Файл с таким именем не найден")
            } catch (ex: Throwable) {
                println("Содержимое файла некорректно")
            }
        }
    }

    override fun parseMatrix(n: Int): Matrix {
        while (true) {
            println("Введите имя файла:")
            try {
                val result = Matrix(n)
                var i = 0
                val fileName = readLine()
                File(fileName!!).forEachLine {
                    val row = it.split(" ").map { elem -> elem.toBigDecimal().setScale(3, RoundingMode.HALF_UP) } as MutableList<BigDecimal>
                    if (row.size != n + 1) { throw Exception() }
                    result.setRow(i, row)
                    i++
                }
                if (i != n) { throw Exception() }
                return result
            } catch (ex: FileNotFoundException) {
                println("Файл с таким именем не найден")
            } catch (ex: Throwable) { println("Содержимое файла некорректно") }
        }
    }
}