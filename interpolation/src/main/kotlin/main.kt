import util.chooseBuilder

/**
 * @author Natalia Nikonova
 */
fun main() {
    val builder = chooseBuilder()
    val (x, table) = builder.build()
    println("Получена таблица интерполяционных узлов:")
    table.forEach { print("$it ") }
    println("\nЗначение аргумента, при котором будет рассчитываться функция: $x")
}
