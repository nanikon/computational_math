package util

import model.DiffEquation
import model.equations

/**
 * @author Natalia Nikonova
 */
fun chooseEquation(): DiffEquation {
    println("Выберите уравнение для решения и введите его номер:")
    equations.forEachIndexed { index, equation -> println("${index + 1} - $equation") }
    while(true) {
        try {
            val chose = readLine()!!.trim()
            return equations[chose.toInt() - 1]
        } catch (ex: NullPointerException) {
            println("Вы ничего не ввели, попробуйте ещё раз")
        } catch (ex: NumberFormatException) {
            println("Вы ввели не целое число, попробуйте ещё раз")
        } catch(ex: IndexOutOfBoundsException) {
            println("Уравнения с таким номером нет, попробуйте ещё раз")
        }
    }
}
