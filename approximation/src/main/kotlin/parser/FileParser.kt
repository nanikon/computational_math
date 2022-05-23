package parser

import java.io.File
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class FileParser : Parser {
    override fun parse(): List<Pair<BigDecimal, BigDecimal>> {
        while(true) {
          try {
              val data = parseFile()
              val n = data[0].toIntOrNull()
                  ?: throw NumberFormatException("На первой строке - кол-во точек - встречено не целое число")
              if (n + 1 != data.size) {
                  throw IndexOutOfBoundsException("Указанное кол-во точек не соответствует найденному в файле")
              }
              return data.drop(1).mapIndexed{ i, line ->
                  val coords = line.split(" ")
                  if (coords.size != 2) { throw IndexOutOfBoundsException("В строке $i не ровно два элемента") }
                  val x = coords[0].toBigDecimalOrNull()
                      ?: throw NumberFormatException("В строке $i первый элемент не является числом")
                  val y = coords[1].toBigDecimalOrNull()
                      ?: throw NumberFormatException("В строке $i второй элемент не является числом")
                  Pair(x, y)
              }
          } catch(ex: Throwable) {
              println("Содержимое файла некорректно ${ex.message}. Попробуйте другой файл")
          }
        }
    }

    private fun parseFile() : List<String> {
        while (true) {
            try {
                println("Введите имя файла, в котором на первой строке указано кол-во точек, а в каждой следующей - " +
                        "координаты одной точки по х и по у через пробел")
                val fileName = readLine()!!.trim()
                val result = File(fileName)
                if (result.exists() && result.canRead()) { return result.readLines() }
                else if (result.exists()) { println("Этот файл невозможно прочесть") }
                else { print("Такого файла не существует")}
            } catch (ex: NullPointerException) {
                print("Вы ничего не ввели")
            }
            println(" Попробуйте ещё раз.")
        }
    }
}
