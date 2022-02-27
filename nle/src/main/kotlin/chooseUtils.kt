import methods.single.HalfDivisionMethod
import methods.single.SimpleIterationsMethod
import methods.single.SingleMethod
import model.OneVariableEquation

/**
 * @author Natalia Nikonova
 */

fun choseMethod(): SingleMethod {
    println("Для использования метода половинного деления введите 1, для метода простых итераций - 2")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, для использования метода половинного деления введите 1, для метода простых итераций - 2")
        chose = readLine()?.trim()
    }
    return if (chose == "1") { HalfDivisionMethod() }
    else { SimpleIterationsMethod() }
}

fun choseEquation(): OneVariableEquation {
    println("Выберите функцию для решения и введите её номер:")
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