import approximation.CubicApproximation
import approximation.ExponentialApproximation
import approximation.LinearApproximation
import approximation.LogarithmicApproximation
import approximation.PowerApproximation
import approximation.QuadraticApproximation
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot
import utlis.chooseParser
import utlis.show

/**
 * @author Natalia Nikonova
 */

val approximations = listOf(
    LinearApproximation(),
    QuadraticApproximation(),
    CubicApproximation(),
    PowerApproximation(),
    ExponentialApproximation(),
    LogarithmicApproximation()
)

val colors = listOf(
    "blue",
    "dark-green",
    "orange",
    "pink",
    "red",
    "yellow"
)

fun main() {
    var plot = letsPlot()
    val parser = chooseParser()
    val points = parser.parse()
    val pointsData = mapOf(
        "x" to points.map { it.first },
        "y" to points.map { it.second }
    )
    var legend = ""
    val results = approximations.mapIndexed { index, approximation ->
        approximation.calculateCoefAndError(points).also { answer ->
            plot += geomPoint(
            data = answer.third,
            color = colors[index],
            size = 1.0,
        ) { x = "x"; y = answer.first }
            legend += "${answer.first}, цвет - ${colors[index]}\n"
        }
    }
    val minSko = results.minOf { result -> result.second }
    val bestApproximation = results[results.indexOfFirst { approx -> approx.second == minSko }]
    println("\nНаилучшая аппроксимирующая функция: ${bestApproximation.first}, её СКО - ${bestApproximation.second}")
    plot += geomPoint(
        data = pointsData,
        color = "black",
        size = 5.0
    ) { x = "x"; y = "y" }
    print(legend)
    show(plot)
}
