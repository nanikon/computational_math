package methods.single

import model.OneVariableResultData
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface SingleMethod {
    fun setAndVerifyData(a:  BigDecimal, b: BigDecimal): Boolean
    fun solve(approximation: BigDecimal): OneVariableResultData
}