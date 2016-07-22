package mockist_style

import java.util.*

class SimpleCalendar : Calendar {
    override fun getCurrentDate(): Date {
        return Date()
    }
}