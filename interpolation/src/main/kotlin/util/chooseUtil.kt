package util

import input.InputFunction
import input.builder.Builder
import input.builder.FunctionBuilder
import input.builder.TableBuilder
import input.functions

/**
 * @author Natalia Nikonova
 */
fun chooseFunction(): InputFunction {
    println("Выберите функцию для интерполирования и введите её номер:")
    functions.forEachIndexed { index, function -> println("${index + 1} - $function") }
    while(true) {
        try {
            val chose = readLine()!!.trim().toInt()
            return functions[chose - 1]
        } catch (ex: NullPointerException) {
            println("Вы ничего не ввели, попробуйте ещё раз")
        } catch (ex: NumberFormatException) {
            println("Вы ввели не целое число, попробуйте ещё раз")
        } catch(ex: IndexOutOfBoundsException) {
            println("Функции с таким номером нет, попробуйте ещё раз")
        }
    }
}

fun chooseBuilder(): Builder {
    println("Для задания функции через таблицу введите 1, для выбора её формулы - 2:")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, для задания функции через таблицу введите 1, для выбора её формулы - 2:")
        chose = readLine()?.trim()
    }
    return if (chose == "1") TableBuilder()
    else FunctionBuilder()
}
