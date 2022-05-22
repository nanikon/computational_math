import approximation.CubicApproximation
import approximation.ExponentialApproximation
import approximation.LinearApproximation
import approximation.LogarithmicApproximation
import approximation.PowerApproximation
import approximation.QuadraticApproximation
import utlis.chooseParser

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

fun main() {
    val parser = chooseParser()
    val points = parser.parse()
    val results = approximations.map { approximation ->
        approximation.calculateCoefAndError(points)
    }
    val minSko = results.minOf { result -> result.second }
    val bestApproximation = results[results.indexOfFirst { approx -> approx.second == minSko }]
    println("\nНаилучшая аппроксимирующая функция: ${bestApproximation.first}, её СКО - ${bestApproximation.second}")
}
