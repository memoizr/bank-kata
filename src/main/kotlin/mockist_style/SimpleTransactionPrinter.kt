package mockist_style

import java.text.SimpleDateFormat
import java.util.*

class SimpleTransactionPrinter(private val printer: Printer) : TransactionPrinter {
    private val header = "DATE | AMOUNT | BALANCE"

    override fun printTransactions(transactions: List<Transaction>) {
        printHeader()
        printEachTransaction(transactions)
    }

    private fun printHeader() {
        printer.print(header)
    }

    private fun printEachTransaction(transactions: List<Transaction>) {
        data class PrintStatement(val date: Date, val amount: Int, val balance: Int)
        transactions.scan<Transaction, PrintStatement> { statement, transaction ->
            PrintStatement(transaction.date, transaction.amount, transaction.amount + (statement?.balance ?: 0))
        }.forEach {
            printer.print("${it.date.formatted()} | ${it.amount} | ${it.balance}")
        }
    }

    private fun <T, R> Collection<T>.scan(function: (R?, T) -> R): Collection<R> {
        var r: R? = null
        return this.map {
            val new = function(r, it)
            r = new
            new
        }
    }

    private fun Date.formatted() = SimpleDateFormat("dd/MM/yy").format(this)
}