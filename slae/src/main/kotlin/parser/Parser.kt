package parser

import Matrix

/**
 * @author Natalia Nikonova
 */
interface Parser {
    fun parseDim() : Int
    fun parseMatrix(n: Int) : Matrix
}