package fragment

import adapter.CalendarAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.afinal.R
import kotlinx.android.synthetic.main.fragment_tab.*
import util.Keys
import java.util.*
import kotlin.Exception
import kotlin.collections.ArrayList

class tabFragment1: Fragment(){

    var mCenterPosition: Int = 0
    var mCalendarList: ArrayList<Any> = ArrayList()

    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: CalendarAdapter
    lateinit var manager: StaggeredGridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView= inflater.inflate(R.layout.fragment_tab, container, false)

        initView(rootView)
        initSet()
        setRecycler()

        return rootView
    }

    fun initView(v: View){
        recyclerView = v.findViewById(R.id.calendar)
    }

    fun initSet(){
        initCalendarList()
    }

    fun initCalendarList(){
        var cal: GregorianCalendar = GregorianCalendar()
        setCalendarList(cal)
    }

    fun setRecycler(){

        manager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)

        mAdapter = CalendarAdapter()

        mAdapter.setCalendarList(mCalendarList)
        recyclerView.setLayoutManager(manager)
        recyclerView.setAdapter(mAdapter)

        if(mCenterPosition >= 0){
            recyclerView.scrollToPosition(mCenterPosition)
        }
    }

    fun setCalendarList(cal: GregorianCalendar){

        var calendarList: ArrayList<Any> = ArrayList()

        for(i in -300 until 300){
            try{
                var calendar: GregorianCalendar = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) +i, 1, 0, 0, 0)
                if(i == 0){
                    mCenterPosition = calendarList.size
                }

                // 타이틀
                calendarList.add(calendar.timeInMillis)

                var dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) -1 // 해당 월에 시작하는 요일 -1을 하면 빈칸을 구할 수 있음
                var max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) // 해당 월에 마지막 요일

                // EMPTY 생성
                for(j in 0 until dayOfWeek){
                    calendarList.add(Keys.Empty)
                }
                for(j in 1..max){
                    calendarList.add(GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),j))
                }
                // TODO: 결과값 넣을 때 여기다 하면 됨
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        mCalendarList = calendarList
    }
}