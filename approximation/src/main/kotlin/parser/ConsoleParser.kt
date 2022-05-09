package parser

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
class ConsoleParser : Parser {
   override fun parse(): List<Pair<BigDecimal, BigDecimal>> {
      val result = mutableListOf<Pair<BigDecimal, BigDecimal>>()
      val n = parseCount()
      for (i in 1..n) {
         result.add(parsePair(i))
      }
      return result
   }

   private fun parsePair(i: Int) : Pair<BigDecimal, BigDecimal> {
      println("Введите коориданту $i-ой точки по х, затем пробел, затем координату точки по y")
      while (true) {
         val input = readLine()?.trim()?.split(" ")
         if (input == null) {
            println("Вы ничего не ввели. Попробуйте ещё раз")
            continue
         } else if (input.size != 2) {
            println("Вы ввели не два элемента. Попробуйте ещё раз")
            continue
         }
         val x = input[0].toBigDecimalOrNull()
         val y = input[1].toBigDecimalOrNull()
         when {
            x != null && y != null -> return Pair(x, y)
            x == null && y == null -> println("Оба введенных элемента не являются числами. Попробуйте ещё раз")
            x == null -> println("Первый элемент не является числом. Попробуйте ещё раз")
            else -> println("Второй элемент не является числом. Попробуйте ещё раз")
         }
      }
   }

   private fun parseCount(): Int {
      println("Введите количество точек")
      while (true) {
         val result = readLine()?.trim()?.toIntOrNull()
         if (result != null && result > 9 && result < 13) {
            return result
         }
         println("Некорректный ввод. Кол-во точек должно быть целым числом в интервале [10, 12]. Попробуйте ещё раз")
      }
   }
}