package exception

/**
 * @author Natalia Nikonova
 */
class SolutionNotExistException(count: Int) : RuntimeException("Мы сделали уже $count операций, но все не можем приблизится к решению. Наверное, его у этой системы нет. Завершаем работу")