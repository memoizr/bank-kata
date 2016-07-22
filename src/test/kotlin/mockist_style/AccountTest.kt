package mockist_style

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

class AccountTest {
    val transactionRepository = mock<TransactionRepository>()
    val transactionPrinter = mock<TransactionPrinter>()

    val account = Account(transactionRepository, transactionPrinter)
    val transactions = listOf(Transaction(10, aDate()), Transaction(10, aDate()), Transaction(10, aDate()))

    @Before
    fun before() {
        whenever(transactionRepository.getAllTransactions()).thenReturn(transactions)
    }

    @Test
    fun depositsAmount() {
        account.deposit(10)

        verify(transactionRepository).deposit(10)
    }

    @Test
    fun withdrawAmount() {
        account.withdraw(10)

        verify(transactionRepository).withdraw(10)
    }

    @Test
    fun printsStatement() {
        account.printStatement()

        verify(transactionPrinter).printTransactions(transactions)
    }
}
