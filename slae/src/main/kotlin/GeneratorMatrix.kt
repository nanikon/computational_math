import java.math.BigDecimal
import kotlin.random.Random

/**
 * @author Natalia Nikonova
 */
class GeneratorMatrix(private val n: Int) {
   val matrix: Matrix
      get() {
         val result  = Matrix(n)
         val solution = mutableListOf<BigDecimal>()
         for (i in 0 until n) { solution.add(BigDecimal((-50..50).random()).setScale(3)) }
         for (i in 0 until n) {
            val row = mutableListOf<BigDecimal>()
            for (j in 0 until n) { row.add(BigDecimal("${(-50..50).random()}.${(0..9).random()}").setScale(1)) }
            val b = row.foldIndexed(BigDecimal(0).setScale(1)) { idx, acc, elem -> acc + elem * solution[idx] }
            row.add(b)
            result.setRow(i, row)
         }
         return result
      }
}