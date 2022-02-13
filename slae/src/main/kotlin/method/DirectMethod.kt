package method

import Matrix

/**
 * @author Natalia Nikonova
 */
interface DirectMethod {
    fun getDeterminant() : Double
    fun getTriangularMatrix() : Matrix
    fun getSolution() : List<Double>
    fun getInconsistencies() : List<Double>
}