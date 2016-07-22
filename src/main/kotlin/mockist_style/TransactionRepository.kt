package mockist_style

interface TransactionRepository {
    fun deposit(amount: Int)
    fun withdraw(amount: Int)
    fun getAllTransactions(): List<Transaction>
}