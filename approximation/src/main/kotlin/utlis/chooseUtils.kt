package utlis

import parser.ConsoleParser
import parser.FileParser
import parser.Parser

/**
 * @author Natalia Nikonova
 */

fun chooseParser() : Parser {
    println("Для ввода точек с клавиатуры введите 1, для ввода с файла - 2")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, для ввода точек с клавиатуры введите 1, для ввода с файла - 2")
        chose = readLine()?.trim()
    }
    return if (chose == "1") { ConsoleParser() }
    else { FileParser() }
}
