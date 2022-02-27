package model

import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
data class OneVariableResultData(
    val root: BigDecimal,
    val valueRoot: BigDecimal,
    val countIteration: Int
) {
    override fun toString(): String = "Найденный корень уравнения: $root\n Значение функции в корне: $valueRoot\n" +
            "Число итераций: $countIteration"
}
