package util

import jetbrains.datalore.plot.PlotSvgExport
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import java.awt.Desktop
import java.io.File

/**
 * @author Natalia Nikonova
 */
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
