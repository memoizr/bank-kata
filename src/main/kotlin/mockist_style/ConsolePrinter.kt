package mockist_style

class ConsolePrinter : Printer {
    override fun print(message: String) {
        println(message)
    }
}