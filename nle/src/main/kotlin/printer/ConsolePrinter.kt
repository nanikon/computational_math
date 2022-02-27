package printer

/**
 * @author Natalia Nikonova
 */
class ConsolePrinter : Printer {
   override fun print(input: String) {
      println(input)
   }
}