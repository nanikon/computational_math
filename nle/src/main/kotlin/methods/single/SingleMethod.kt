package methods.single

import model.OneVariableEquation
import model.OneVariableResultData
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface SingleMethod {
    fun setAndVerifyData(a:  BigDecimal, b: BigDecimal, equation: OneVariableEquation): Boolean
    fun solve(approximation: BigDecimal): OneVariableResultData
}