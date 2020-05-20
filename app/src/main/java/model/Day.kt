package model

import util.DateUtil
import java.util.*

class Day: ViewModel(){
    var day: String? = null


    fun fgetDay(): String?{
        return day
    }

    fun fsetDay(day: String){
        this.day = day
    }

    //TODO DAY에 달력일 값 넣기
    fun setCalendar(calendar: Calendar){
        day = DateUtil.getDate(calendar.timeInMillis, DateUtil.DAY_FORMAT)
    }
}