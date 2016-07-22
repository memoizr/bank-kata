package mockist_style

fun main(args: Array<String>) {
    val calendar = SimpleCalendar()
    val transactionRepository = InMemoryTransactionRepository(calendar)
    val printer = ConsolePrinter()
    val transactionPrinter = SimpleTransactionPrinter(printer)
    val account = Account(transactionRepository, transactionPrinter)

    account.deposit(10)
    account.deposit(34)
    account.withdraw(31)
    account.deposit(99)

    account.printStatement()
}

