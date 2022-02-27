package methods.system

import model.ManyVariablesResultData
import model.TwoVariableEquation
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface SystemMethod {
    fun setAndVerifyData(a: BigDecimal, b: BigDecimal, firstEquation: TwoVariableEquation, secondEquation: TwoVariableEquation): Boolean
    fun solve(x0: BigDecimal, y0: BigDecimal, approximation: BigDecimal): ManyVariablesResultData
}