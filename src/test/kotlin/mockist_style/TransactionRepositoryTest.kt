package mockist_style

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TransactionRepositoryTest {
    val calendar = mock<Calendar>()
    val transactionRepository = InMemoryTransactionRepository(calendar)

    @Test
    fun depositsAmount() {
        val date = aDate()
        whenever(calendar.getCurrentDate()).thenReturn(date)
        transactionRepository.deposit(10)

        assertThat(transactionRepository.getAllTransactions()).isEqualTo(listOf(Transaction(10, date)))
    }

    @Test
    fun withdrawsAmount() {
        val date = aDate()
        whenever(calendar.getCurrentDate()).thenReturn(date)
        transactionRepository.withdraw(10)

        assertThat(transactionRepository.getAllTransactions()).isEqualTo(listOf(Transaction(-10, date)))
    }

    @Test
    fun keepsMultipleTransactions() {
        val firstDate = aDate()
        val secondDate = aDate()
        whenever(calendar.getCurrentDate()).thenReturn(firstDate, secondDate)
        transactionRepository.withdraw(10)
        transactionRepository.deposit(15)

        assertThat(transactionRepository.getAllTransactions()).isEqualTo(listOf(Transaction(-10, firstDate), Transaction(15, secondDate)))
    }
}

