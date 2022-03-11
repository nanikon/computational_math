package methods.system

import model.ManyVariablesResultData
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface SystemMethod {
    fun setAndVerifyData(x: BigDecimal, y: BigDecimal): Boolean
    fun solve(approximation: BigDecimal): ManyVariablesResultData
}