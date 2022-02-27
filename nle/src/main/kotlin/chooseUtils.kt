import methods.single.HalfDivisionMethod
import methods.single.SimpleIterationsMethod
import methods.single.SingleMethod
import model.OneVariableEquation
import printer.ConsolePrinter
import printer.FilePrinter
import printer.Printer

/**
 * @author Natalia Nikonova
 */

fun chooseMethod(): SingleMethod {
    println("Для использования метода половинного деления введите 1, для метода простых итераций - 2")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, для использования метода половинного деления введите 1, для метода простых итераций - 2")
        chose = readLine()?.trim()
    }
    return if (chose == "1") { HalfDivisionMethod() }
    else { SimpleIterationsMethod() }
}

fun chooseEquation(): OneVariableEquation {
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

fun choosePrinter(): Printer {
    println("Введите 1, чтобы вывести результат на консоль, и 2, чтобы вывести его в файл")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, введите 1, чтобы вывести результат на консоль, и 2, чтобы вывести его в файл")
        chose = readLine()?.trim()
    }
    return if (chose == "1") { ConsolePrinter() }
    else { FilePrinter() }
}
