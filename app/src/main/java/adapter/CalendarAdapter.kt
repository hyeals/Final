package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.afinal.R
import model.CalendarHeader
import model.Day
import model.EmptyDay
import model.*
import org.w3c.dom.Text
import java.util.*

class CalendarAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var HEARDER_TYPE: Int = 0
    var EMPTY_TYPE: Int = 1
    var DAY_TYPE: Int = 2

    private var mCalendarList: ArrayList<Any> = arrayListOf()

    constructor(calendarList: ArrayList<Any>):this(){
        mCalendarList = calendarList
    }

    fun setCalendarList(calendarList: ArrayList<Any>){
        mCalendarList = calendarList
        notifyDataSetChanged()
    }

    // 뷰 타입 나누기
    override fun getItemViewType(position: Int): Int {
        var item: Any = mCalendarList[position]
        if(item is Long){
            return HEARDER_TYPE // 날짜 타입
        }else if(item is String){
            return EMPTY_TYPE // 비어있는 일자 타입
        }else{
            return DAY_TYPE // 일자 타입
        }
    }

    // 뷰 홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(parent.context)
        // 날짜 타입
        if(viewType == HEARDER_TYPE){

            var viewHolder: HeaderViewHolder = HeaderViewHolder(inflater.inflate(R.layout.item_calendar_header, parent, false))

                var params: StaggeredGridLayoutManager.LayoutParams = viewHolder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
                params.setFullSpan(true) // Span을 하나로 통합하기
                viewHolder.itemView.setLayoutParams(params)
                return viewHolder

        // 비어있는 일자 타입
        }else if(viewType == EMPTY_TYPE){
            return EmptyViewHolder(inflater.inflate((R.layout.item_day_empty), parent, false))
        }
        //일자 타입
        else{
            return DayViewHolder(inflater.inflate(R.layout.item_day, parent, false))
        }
        return DayViewHolder(inflater.inflate(R.layout.item_day, parent, false))
    }

    override fun getItemCount(): Int {
       return mCalendarList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        var viewType: Int = getItemViewType(position)

        // 날짜 타입 꾸미기
        // ex: 2020년 5월
        if(viewType == HEARDER_TYPE){
            var holder: HeaderViewHolder = viewHolder as HeaderViewHolder
            var item: Any = mCalendarList.get(position)
            var model = CalendarHeader()

            // long type의 현재 시간
            if(item is Long){
                // 현재 시간 넣으면, 날짜 패턴에 맞게 model에 데이터 들어감
                model.fsetHeader(item)
            }
            // view에 표시하기
            holder.bind(model)
        }
        // 비어있는 날짜 타입 꾸미기
        // ex: empty
        else if(viewType == EMPTY_TYPE){
            var holder = viewHolder as EmptyViewHolder
            var model: EmptyDay = EmptyDay()
            holder.bind(model)
        }
        // 일자 타입 꾸미기
        // ex: 22
        else if(viewType == DAY_TYPE){
            var holder = viewHolder as DayViewHolder
            var item: Any = mCalendarList.get(position)
            var model: Day = Day()

            if(item is Calendar){
                // Model에 Calendar값을 넣어서 몇일인지 데이터 넣기
                model.setCalendar(item)
            }
            // Model의 데이터를 View에 표현하기
            holder.bind(model)
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){ // 날짜 타입 뷰 홀더

        var itemHeaderTitle: TextView = itemView.findViewById(R.id.item_header_title)

        init{
            super.itemView

            initView(itemView)
        }

        fun initView(v: View){
            itemHeaderTitle = v.findViewById(R.id.item_header_title)
        }

        fun bind(model: ViewModel){

            // 일자 값 가져오기
            var header: String = (model as CalendarHeader).fgetHeader()!!

            // header에 표시하기 ex: 2020년 5월
            itemHeaderTitle.text = header
        }
    }

    inner class EmptyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        init {
            super.itemView

            initView(itemView)
        }

        fun initView(v: View){

        }

        fun bind(model: ViewModel){

        }
    }

    // TODO: item_day와 매칭
    inner class DayViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

       var itemDay: TextView = itemView.findViewById(R.id.item_day)

        init{
           super.itemView

           initView(itemView)
       }

       fun initView(v: View){

            if(itemDay is TextView){
                itemDay = v.findViewById(R.id.item_day)
            }
        }

        fun bind(model: ViewModel){

            if(model is Day){
                // 일자 값 가져오기
                var day: String = model.fgetDay()!!
                // 일자 값 View에 보이게 하기
                itemDay.text = day
            }
        }

    }
}