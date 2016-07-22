package mockist_style

class InMemoryTransactionRepository(private var calendar: Calendar) : TransactionRepository {
    private val transactions = mutableListOf<Transaction>()

    override fun getAllTransactions(): List<Transaction> = transactions.toList()

    override fun withdraw(amount: Int) {
        transactions.add(Transaction(-amount, calendar.getCurrentDate()))
    }

    override fun deposit(amount: Int) {
        transactions.add(Transaction(amount, calendar.getCurrentDate()))
    }
}