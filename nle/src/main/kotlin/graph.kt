import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.letsPlot
import jetbrains.datalore.plot.PlotSvgExport
import jetbrains.letsPlot.intern.toSpec

import model.OneVariableEquation
import model.TwoVariableEquation
import java.awt.Desktop
import java.io.File
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */

fun createGraph(
    firstEq: (BigDecimal) -> BigDecimal,
    secondEq: (BigDecimal) -> BigDecimal,
    leftBorder: BigDecimal = BigDecimal(-5),
    rightBorder: BigDecimal = BigDecimal(5)
) {
    val left = leftBorder.multiply(BigDecimal(1000)).toInt()
    val right = rightBorder.multiply(BigDecimal(1000)).toInt()
    val range = (left..right).toList().map{ it.toBigDecimal().divide(BigDecimal(1000), MathContext.DECIMAL64)}
    val firstRange = mutableListOf<BigDecimal>()
    val secondRange = mutableListOf<BigDecimal>()
    val firstValue = mutableListOf<BigDecimal>()
    val secondValue = mutableListOf<BigDecimal>()
    for (i in range.indices) {
        try {
            firstValue.add(firstEq(range[i]))
            firstRange.add(range[i])
        } catch (_: ArithmeticException) {}
    }
    for (i in range.indices) {
        try {
            secondValue.add(secondEq(range[i]))
            secondRange.add(range[i])
        } catch (_: ArithmeticException) {}
    }

    val firstData = mapOf(
        "x1" to firstRange,
        "y1" to firstValue,
    )
    val secondData = mapOf(
        "x2" to secondRange,
        "y2" to secondValue
    )
    val plot = letsPlot(firstData) + geomPoint(
        color = "dark-green",
        size = 1.0,
    ) { x = "x1"; y = "y1" } + geomPoint(
        data = secondData,
        color = "dark-blue",
        size = 1.0
    ) { x = "x2"; y = "y2" }
    show(plot)
}

fun show(plot: Plot) {
    val content = PlotSvgExport.buildSvgImageFromRawSpecs(plot.toSpec())
    openInBrowser(content)
}

fun openInBrowser(content: String) {
    val dir = File(System.getProperty("user.dir"), "lets-plot-images")
    dir.mkdir()
    val file = File(dir.canonicalPath, "my_plot.html")
    file.createNewFile()
    file.writeText(content)

    Desktop.getDesktop().browse(file.toURI())
}
