package mockist_style
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.Test

class SimpleTransactionPrinterTest {
    val printer = mock<Printer>()
    val transactionPrinter = SimpleTransactionPrinter(printer)

    @Test
    fun printsHeaderEvenWithNoTransactions() {
        transactionPrinter.printTransactions(emptyList())

        verify(printer).print("DATE | AMOUNT | BALANCE")
        verifyNoMoreInteractions(printer)
    }

    @Test
    fun printsTransactions() {
        val firstDate = aDate(day = 11)
        val secondDate = aDate(day = 12)

        transactionPrinter.printTransactions(listOf(Transaction(10, firstDate), Transaction(20, secondDate)))

        with(inOrder(printer)) {
            verify(printer).print("DATE | AMOUNT | BALANCE")
            verify(printer).print("11/11/11 | 10 | 10")
            verify(printer).print("12/11/11 | 20 | 30")
        }
        verifyNoMoreInteractions(printer)
    }
}