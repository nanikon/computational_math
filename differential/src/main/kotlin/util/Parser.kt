package util

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
object Parser {
   fun parseBigDecimal(obj: String, sign: String, condition: (BigDecimal) -> Boolean): BigDecimal {
      while (true) {
         println("Введите $obj. Это должно быть дробное число $sign")
         val result = readLine()?.trim()?.toBigDecimalOrNull()
         if (result != null && condition(result)) { return result }
         println("Некорректный ввод. Попробуйте ещё раз")
      }
   }
}
