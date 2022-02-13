import parser.ConsoleParser
import parser.FileParser
import parser.Parser

/**
 * @author Natalia Nikonova
 */
fun main() {
    val dimParser = choseParser("размерности матрицы")
    val n = dimParser.parseDim()
    println("Введена размерность $n")
    val matrixParser = choseParser("матрицы")
    val matrix = matrixParser.parseMatrix(n)
    println("Введена следующая матрица:")
    matrix.printMatrix()
}

fun choseParser(obj: String) : Parser {
    println("Для ввода $obj с клавиатуры введите 1, для ввода с файла - 2")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, для ввода $obj с клавиатуры введите 1, для ввода с файла - 2")
        chose = readLine()?.trim()
    }
    return if (chose == "1") { ConsoleParser() }
    else { FileParser() }
}