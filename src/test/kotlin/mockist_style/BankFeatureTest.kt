package mockist_style
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test

class BankFeatureTest {
    val printer = mock<Printer>()
    val calendar = mock<Calendar>()

    val transactionRepository = InMemoryTransactionRepository(calendar)
    val transactionPrinter = SimpleTransactionPrinter(printer)
    val account = Account(transactionRepository, transactionPrinter)

    @Test
    fun printsStatatementWithCorrectValues() {
        val fistDate = aDate(day = 11)
        val secondDate = aDate(day = 12)
        val thirdDate = aDate(day = 13)
        val fourthDate = aDate(day = 14)
        whenever(calendar.getCurrentDate()).thenReturn(fistDate, secondDate, thirdDate, fourthDate)

        account.deposit(30)
        account.deposit(20)
        account.withdraw(10)
        account.deposit(15)

        account.printStatement()

        with(inOrder(printer)) {
            verify(printer).print("DATE | AMOUNT | BALANCE")
            verify(printer).print("11/11/11 | 30 | 30")
            verify(printer).print("12/11/11 | 20 | 50")
            verify(printer).print("13/11/11 | -10 | 40")
            verify(printer).print("14/11/11 | 15 | 55")
        }
        verifyNoMoreInteractions(printer)
    }
}

