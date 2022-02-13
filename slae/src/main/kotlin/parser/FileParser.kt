package parser

import Matrix
import java.io.File
import java.io.FileNotFoundException

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
                    val row = it.split(" ").map { elem -> elem.toInt() } as MutableList<Int>
                    if (row.size != n + 1) {
                        throw Exception("В строке $i элементов меньше чем надо")
                    }
                    result.setRow(i, row)
                    i++
                }
                if (i != n) { throw Exception("Строк меньше чем надо") }
                return result
            } catch (ex: FileNotFoundException) {
                println("Файл с таким именем не найден")
            } catch (ex: Throwable) { println("Содержимое файла некорректно") }
        }
    }
}