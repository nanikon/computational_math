package printer

import java.io.File
import java.io.FileNotFoundException

/**
 * @author Natalia Nikonova
 */
class FilePrinter : Printer {
    override fun print(input: String) {
        while (true) {
            println("Введите имя файла:")
            try {
                val fileName = readLine()
                File(fileName!!).printWriter().use { out -> out.println(input) }
                break
            } catch (ex: FileNotFoundException) {
                println("Файл с таким именем не найден")
            } catch (ex: NullPointerException) {
                println("Вы ничего не ввели")
            } catch (ex: Throwable) {
                println("В файл невозможно сделать запись")
            }
        }
    }
}