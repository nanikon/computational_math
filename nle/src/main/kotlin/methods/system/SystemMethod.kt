package methods.system

import model.ManyVariablesResultData
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface SystemMethod {
    fun setAndVerifyData(x0: BigDecimal, y0: BigDecimal): Boolean
    fun solve(approximation: BigDecimal): ManyVariablesResultData
}