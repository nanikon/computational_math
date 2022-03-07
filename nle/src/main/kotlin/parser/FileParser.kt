package parser

import methods.single.SingleMethod
import methods.system.SystemMethod
import java.io.File
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class FileParser : Parser {
    private fun parseFile(format: String) : List<String> {
        while (true) {
            try {
                println("Введите имя файла, в котором представлена информация в виде: $format")
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

    override fun parseIntervalAndApprox(method: SingleMethod): BigDecimal {
        while (true) {
            try {
                val lines = parseFile(
                    "на первой строке - два числа через пробел, левая и правая границы интервала,"
                            + " на второй - одно число, погрешность"
                )
                val a = lines[0].split(" ")[0].toBigDecimal()
                val b = lines[0].split(" ")[1].toBigDecimal()
                if (a < b) {
                    val approx = lines[1].toBigDecimal()
                    if (method.setAndVerifyData(a, b)) {
                        println("Получены значения: левая граница интервала $a, правая граница интервала $b, погрешность $approx")
                        return approx
                    }
                    println("Интервал некорректен - на нем либо не ровно один корень, либо он слишком большой.")
                } else println("Интервал некорректен - левая граница должна быть меньше правой.")
            } catch (ex: NumberFormatException) {
                println("Встречено не число.")
            } catch (ex: IndexOutOfBoundsException) {
                println("В файле недостаточно строк или в какой-либо строке недостаточно чисел.")
                ex.printStackTrace()
            } catch (ex: Throwable) {
                println("Содержимое файла неккоректно.")
            }
            println("Попробуйте использовать другой файл. Начнем все с начала...")
        }
    }

    override fun parseInitValuesAndApprox(method: SystemMethod): BigDecimal {
        while (true) {
            try {
                val lines = parseFile("на первой строке - два числа через пробел, начальные приближения х и y,"
                        + " на второй - одно число, погрешность.")
                val x0 = lines[0].split(" ")[0].toBigDecimal()
                val y0 = lines[0].split(" ")[1].toBigDecimal()
                val approx = lines[1].toBigDecimal()
                if (method.setAndVerifyData(x0, y0)) {
                    println("Получены значения: начальное приближение переменной х $x0, переменной у $y0, погрешность $approx")
                    return approx
                }
                println("Введенный начальные приближения некоррекны.")
            } catch (ex: NumberFormatException) {
                println("Встречено не число.")
            } catch (ex: IndexOutOfBoundsException) {
                println("В файле недостаточно строк или в какой-либо строке недостаточно чисел.")
            } catch (ex: Throwable) {
                println("Содержимое файла неккоректно.")
            }
            println("Попробуйте использовать другой файл. Начнем все с начала...")
        }
    }

}