import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot
import method.methods
import util.chooseBuilder
import util.show

/**
 * @author Natalia Nikonova
 */

val colors = listOf("red", "blue")

val shapes = listOf(21, 23)

fun main() {
    var plot = letsPlot()
    val builder = chooseBuilder()
    val (arg, table) = builder.build()
    println("Получена таблица интерполяционных узлов:")
    table.forEach { print("$it ") }
    println("\nЗначение аргумента, при котором будет рассчитываться функция: $arg")
    val pointsData = mapOf(
        "x" to table.map { it.first },
        "y" to table.map { it.second }
    )
    plot += geomPoint(
        data = pointsData,
        color = "black",
        size = 5.0
    ) { x = "x"; y = "y" }
    methods.forEachIndexed { index, method ->
        method.compute(arg, table).also { answer ->
            val resultData = mapOf(
                "x" to listOf(answer.first),
                "y" to listOf(answer.second)
            )
            plot += geomPoint(
                data = resultData,
                shape = shapes[index],
                color = colors[index],
                size = 7.0,
                fill = "white"
            ) { x = "x"; y = "y" }
        }
    }
    show(plot)
}
