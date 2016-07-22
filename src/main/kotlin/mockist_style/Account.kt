package mockist_style

class Account(private val transactionRepository: TransactionRepository, private val transactionPrinter: TransactionPrinter) {
    fun printStatement() {
        transactionPrinter.printTransactions(transactionRepository.getAllTransactions())
    }

    fun deposit(amount: Int) {
        transactionRepository.deposit(amount)
    }

    fun withdraw(amount: Int) {
        transactionRepository.withdraw(amount)
    }
}