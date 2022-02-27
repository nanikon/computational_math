package methods.single

import model.Equation
import model.ResultData
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface SingleMethod {
    fun setAndVerifyData(a:  BigDecimal, b: BigDecimal, equation: Equation): Boolean
    fun solve(approximation: BigDecimal): ResultData
}