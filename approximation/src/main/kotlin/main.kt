import approximation.LinearApproximation
import utlis.chooseParser

/**
 * @author Natalia Nikonova
 */

val approximations = listOf(
    LinearApproximation()
)

fun main() {
    val parser = chooseParser()
    val points = parser.parse()
    val results = approximations.map { approximation ->
        approximation.calculateCoefAndError(points).also { println("${it.first}, СКО: ${it.second}") }
    }
}
