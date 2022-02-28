package methods.system

import model.ManyVariablesResultData
import model.TwoVariableEquation
import java.math.BigDecimal
import java.math.MathContext

/**
 * @author Natalia Nikonova
 */
class NewtonMethod(
    var firstEq: TwoVariableEquation,
    var secondEq: TwoVariableEquation
) : SystemMethod {
    private lateinit var x0: BigDecimal
    private lateinit var y0: BigDecimal

    override fun setAndVerifyData(x: BigDecimal, y: BigDecimal): Boolean {
        x0 = x
        y0 = y
        return true
    }

    override fun solve(approximation: BigDecimal): ManyVariablesResultData {
        var count = 0
        var deltaX: BigDecimal
        var deltaY: BigDecimal
        var x = x0
        var y = y0
        do {
            val a = firstEq.derivativeX(x, y)
            val b = firstEq.derivativeY(x, y)
            val f = - firstEq.function(x, y)
            val c = secondEq.derivativeX(x, y)
            val d = secondEq.derivativeY(x, y)
            val g = - secondEq.function(x, y)
            val cDivideA = c.divide(a, MathContext.DECIMAL64)
            deltaY = g.minus(f.multiply(cDivideA)).divide(d.minus(b.multiply(cDivideA)), MathContext.DECIMAL64)
            deltaX = f.minus(b.multiply(deltaY)).divide(a, MathContext.DECIMAL64)
            x += deltaX
            y += deltaY
            count++
            println("Итерация №$count: приближение к Х $x, приближение к $y, приращение к Х $deltaX, " +
                    "приращение к Y $deltaY, значение первого уравнения ${firstEq.function(x, y)}, " +
                    "второго ${secondEq.function(x, y)}")
        } while (deltaX > approximation || deltaY > approximation)
        return ManyVariablesResultData(root = listOf(x, y), errors = listOf(deltaX, deltaY), countIteration = count)
    }
}