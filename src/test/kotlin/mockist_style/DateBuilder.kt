package mockist_style

import java.util.*
import java.util.GregorianCalendar.NOVEMBER

fun aDate(year: Int = 2011, month: Int = NOVEMBER, day: Int = 11) = Date(GregorianCalendar(year, month, day).timeInMillis)
