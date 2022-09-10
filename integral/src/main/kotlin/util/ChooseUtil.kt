package util

import method.LeftRectMethod
import method.MediumRectMethod
import method.Method
import method.RightRectMethod
import method.SimpsonMethod
import method.TrapezeMethod
import model.IntegrateFunction
import model.functions

/**
 * @author Natalia Nikonova
 */

fun chooseFunction() : IntegrateFunction {
    println("Выберите функцию для интегрирования и введите её номер:")
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

fun chooseMethod() : Method {
    val methods = listOf(
        LeftRectMethod(),
        MediumRectMethod(),
        RightRectMethod(),
        TrapezeMethod(),
        SimpsonMethod()
    )
    println("Выберите метод интегрирования и введите соответствующий ему номер:")
    methods.forEachIndexed { index, method -> println("${index + 1} - $method") }
    while(true) {
        try {
            val chose = readLine()!!.trim().toInt()
            return methods[chose - 1]
        } catch (ex: NullPointerException) {
            println("Вы ничего не ввели, попробуйте ещё раз")
        } catch (ex: NumberFormatException) {
            println("Вы ввели не целое число, попробуйте ещё раз")
        } catch(ex: IndexOutOfBoundsException) {
            println("Метода с таким номером нет, попробуйте ещё раз")
        }
    }
}