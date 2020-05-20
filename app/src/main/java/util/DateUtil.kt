package util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object{
        var CALENDAR_HEADER_FORMAT: String = "yyyy년" + "MM월"
        var YEAR_FORMAT: String = "yyyy"
        var MONTH_FORMAT: String = "MM"
        var DAY_FORMAT: String = "d"
        var HOUR_FORMAT: String = "HH"
        var MIN_FORMAT: String = "mm"
        var SEC_FORMAT: String = "ss"

        fun getDate(date: Long, pattern: String): String {
            try {
                var formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
                var d: Date = Date(date)
                return formatter.format(d).toUpperCase()
            } catch (e: Exception) {
                return " "
            }
        }
    }
}

