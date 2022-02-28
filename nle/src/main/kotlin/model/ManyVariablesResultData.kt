package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
data class ManyVariablesResultData(
   val root: List<BigDecimal>,
   val errors: List<BigDecimal>,
   val countIteration: Int
) {
   override fun toString(): String = "Вектор неизвестных:\n" + root.joinToString(separator="\n") +
           "\nВектор погрешностей:\n" + errors.joinToString(separator = "\n") + "\nЧисло итераций:\n$countIteration"
}
