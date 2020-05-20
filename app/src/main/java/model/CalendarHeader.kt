package model

import util.DateUtil

class CalendarHeader: ViewModel() {
    var header: String? = null


    fun fgetHeader(): String?{
        return header
    }

    fun fsetHeader(time: Long){
        var value: String? = DateUtil.getDate(time, DateUtil.CALENDAR_HEADER_FORMAT)
        this.header = value
    }

    fun fsetHeader(header: String?){
        this.header = header
    }
}