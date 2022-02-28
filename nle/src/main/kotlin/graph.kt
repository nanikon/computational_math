import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.letsPlot
import jetbrains.datalore.plot.PlotSvgExport
import jetbrains.letsPlot.intern.toSpec

import model.OneVariableEquation
import java.awt.Desktop
import java.io.File
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */

fun createGraphOneVariable(a: BigDecimal, b: BigDecimal, eq: OneVariableEquation) {
    val left = a.multiply(BigDecimal(1000)).toInt()
    val right = b.multiply(BigDecimal(1000)).toInt()
    val range = (left..right).toList().map{ it.toBigDecimal().divide(BigDecimal(1000), MathContext.DECIMAL64)}
    val data = mapOf(
        "x" to range,
        "y" to range.map {eq.valueFunction(it) }
    )
    val plot = createPlot(data)
    show(plot)
}

fun createPlot(data: Map<String, List<BigDecimal>>) : Plot {
    return letsPlot(data) + geomPoint(
        color = "dark-green",
        size = 1.0,
    ) { x = "x"; y = "y" }
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