package method

import Matrix
import java.math.BigDecimal

/**
 * @author Natalia Nikonova
 */
interface DirectMethod {
    fun getDeterminant() : BigDecimal
    fun getTriangularMatrix() : Matrix
    fun getSolution() : List<BigDecimal>
    fun getInconsistencies() : List<BigDecimal>
}