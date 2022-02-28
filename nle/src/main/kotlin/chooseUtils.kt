import methods.single.HalfDivisionMethod
import methods.single.SimpleIterationsMethod
import methods.single.SingleMethod
import model.OneVariableEquation
import model.TwoVariableEquation
import model.equations
import model.systemEquations
import parser.ConsoleParser
import parser.FileParser
import parser.Parser
import printer.ConsolePrinter
import printer.FilePrinter
import printer.Printer

/**
 * @author Natalia Nikonova
 */

fun chooseMode(): String {
    println("Для решения одного уравнения введите 1, для решения системы из двух введите 2")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, для решения одного уравнения введите 1, для решения системы из двух введите 2")
        chose = readLine()?.trim()
    }
    return chose
}

fun chooseParser(obj: String) : Parser {
    println("Для ввода $obj с клавиатуры введите 1, для ввода с файла - 2")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, для ввода $obj с клавиатуры введите 1, для ввода с файла - 2")
        chose = readLine()?.trim()
    }
    return if (chose == "1") { ConsoleParser() }
    else { FileParser() }
}

fun chooseMethod(eq: OneVariableEquation): SingleMethod {
    println("Для использования метода половинного деления введите 1, для метода простых итераций - 2")
    var chose = readLine()?.trim()
    while ((chose != "1") && (chose != "2")) {
        println("Ввод некорректен. Пожалуйста, для использования метода половинного деления введите 1, для метода простых итераций - 2")
        chose = readLine()?.trim()
    }
    return if (chose == "1") { HalfDivisionMethod(eq) }
    else { SimpleIterationsMethod(eq) }
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

fun chooseEquations(number: String, notUse: String): TwoVariableEquation {
    println("Выберите $number уравнение для системы и введите его номер:")
    systemEquations.forEachIndexed { index, equation -> println("${index + 1} - $equation") }
    while(true) {
        try {
            val chose = readLine()!!.trim()
            if (notUse == chose) { throw RuntimeException("Вы уже выбрали такое уравнение") }
            return systemEquations[chose.toInt() - 1]
        } catch (ex: NullPointerException) {
            println("Вы ничего не ввели, попробуйте ещё раз")
        } catch (ex: NumberFormatException) {
            println("Вы ввели не целое число, попробуйте ещё раз")
        } catch(ex: IndexOutOfBoundsException) {
            println("Уравнения с таким номером нет, попробуйте ещё раз")
        } catch (ex: RuntimeException) {
            println(ex.message)
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
