package com.example.hi_buy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var btn1 : ImageButton
    lateinit var tvDate : TextView
    lateinit var tvDay : TextView
    lateinit var btnLeft : ImageButton
    lateinit var btnRight : ImageButton
    lateinit var main_single_row_calendar : com.michalsvec.singlerowcalendar.calendar.SingleRowCalendar
    lateinit var cl_calendar_item : LinearLayout
    lateinit var tv_date_calendar_item : TextView
    lateinit var tv_day_calendar_item : TextView
    lateinit var month_tv : TextView
    private val calendar = Calendar.getInstance()
    private var currentMonth = 0
    private var currentDay = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById<ImageButton>(R.id.btn1)
        main_single_row_calendar = findViewById(R.id.main_single_row_calendar)
        tvDate = findViewById(R.id.tvDate)
        tvDay = findViewById(R.id.tvDay)
        btnLeft = findViewById(R.id.btnLeft)
        btnRight = findViewById(R.id.btnRight)
        month_tv = findViewById(R.id.month_tv)
        //cl_calendar_item = findViewById(R.id.cl_calendar_item)
        //tv_date_calendar_item = findViewById(R.id.tv_date_calendar_item)




        btn1.setOnClickListener {
            val shop = Intent(this, ShopActivity::class.java)
            startActivity(shop)
        }

        // set current date to calendar and current month to currentMonth variable
        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
        currentDay = calendar[Calendar.DATE]

        month_tv.setText((LocalDateTime.now().year).toString() +"년 " + (LocalDateTime.now().month.value).toString() + "월")

        // enable white status bar with black icons
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        // calendar view manager is responsible for our displaying logic
        val myCalendarViewManager = object :
            CalendarViewManager {
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {

                // set date to calendar according to position where we are

                val cal = Calendar.getInstance()
                cal.time = date

                println(isSelected)

                // if item is selected we return this layout items
                // in this example. monday, wednesday and friday will have special item views and other days
                // will be using basic item view
                return if (isSelected)
                    when (cal[Calendar.DAY_OF_WEEK]) {
                        Calendar.MONDAY -> R.layout.first_special_selected_calendar_item
                        Calendar.TUESDAY -> R.layout.first_special_selected_calendar_item
                        Calendar.WEDNESDAY -> R.layout.first_special_selected_calendar_item
                        Calendar.THURSDAY -> R.layout.first_special_selected_calendar_item
                        Calendar.FRIDAY -> R.layout.first_special_selected_calendar_item
                        Calendar.SATURDAY -> R.layout.second_special_selected_calendar_item
                        Calendar.SUNDAY -> R.layout.third_special_selected_calendar_item
                        else -> R.layout.selected_calendar_item
                    }
                else
                // here we return items which are not selected
                    when (cal[Calendar.DAY_OF_WEEK]) {
                        Calendar.MONDAY -> R.layout.first_special_calendar_item
                        Calendar.TUESDAY -> R.layout.first_special_calendar_item
                        Calendar.WEDNESDAY -> R.layout.first_special_calendar_item
                        Calendar.THURSDAY -> R.layout.first_special_calendar_item
                        Calendar.FRIDAY -> R.layout.first_special_calendar_item
                        Calendar.SATURDAY -> R.layout.second_special_calendar_item
                        Calendar.SUNDAY -> R.layout.third_special_calendar_item
                        else -> R.layout.calendar_item
                    }

                // NOTE: if we don't want to do it this way, we can simply change color of background
                // in bindDataToCalendarView method

            }

            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                // using this method we can bind data to calendar view
                // good practice is if all views in layout have same IDs in all item views


                val dayNumber = DateUtils.getDayNumber(date)
                holder.itemView.findViewById<TextView>(R.id.tv_date_calendar_item).text = dayNumber

                val day3LettersName = DateUtils.getDay3LettersName(date)
                holder.itemView.findViewById<TextView>(R.id.tv_day_calendar_item).text = day3LettersName


                //holder.itemView.findViewById<TextView>(R.id.tv_date_calendar_item).text = DateUtils.getDayNumber(date)
                //holder.itemView.findViewById<TextView>(R.id.tv_day_calendar_item).text = DateUtils.getDay3LettersName(date)
                //holder.itemView.tv_date_calendar_item.text = DateUtils.getDayNumber(date)
                //holder.itemView.tv_day_calendar_item.text = DateUtils.getDay3LettersName(date)

            }
        }

        // using calendar changes observer we can track changes in calendar
        val myCalendarChangesObserver = object :
            CalendarChangesObserver {
            // you can override more methods, in this example we need only this one
            @SuppressLint("SetTextI18n")
            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
                tvDate.text = "${DateUtils.getMonthName(date)}, ${DateUtils.getDayNumber(date)} "
                tvDay.text = DateUtils.getDayName(date)
                super.whenSelectionChanged(isSelected, position, date)
            }


        }

        // selection manager is responsible for managing selection
        val mySelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                // set date to calendar according to position
                val cal = Calendar.getInstance()
                cal.time = date
                // in this example sunday and saturday can't be selected, others can
                return when (cal[Calendar.DAY_OF_WEEK]) {
                    else -> true
                }
            }
        }

        // here we init our calendar, also you can set more properties if you haven't specified in XML layout
        val singleRowCalendar = main_single_row_calendar.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            //pastDaysCount = 90
            //futureDaysCount = 90
            //includeCurrentDate = true
            setDates(getFutureDatesOfCurrentMonth())
            initialPositionIndex = currentDay
            init()
        }

        btnRight.setOnClickListener {
            singleRowCalendar.setDates(getDatesOfNextMonth())
            month_tv.setText(calendar[Calendar.YEAR].toString() +"년 " + (calendar[Calendar.MONTH]+1).toString() + "월")
        }

        btnLeft.setOnClickListener {
            singleRowCalendar.setDates(getDatesOfPreviousMonth())
            month_tv.setText(calendar[Calendar.YEAR].toString() +"년 " + (calendar[Calendar.MONTH]+1).toString() + "월")
        }
    }

    private fun getDatesOfNextMonth(): List<Date> {
        currentMonth++ // + because we want next month
        if (currentMonth == 12) {
            // we will switch to january of next year, when we reach last month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] + 1)
            currentMonth = 0 // 0 == january
        }
        return getDates(mutableListOf())
    }

    private fun getDatesOfPreviousMonth(): List<Date> {
        currentMonth-- // - because we want previous month
        if (currentMonth == -1) {
            // we will switch to december of previous year, when we reach first month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] - 1)
            currentMonth = 11 // 11 == december
        }
        return getDates(mutableListOf())
    }

    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        // get all next dates of current month
        currentMonth = calendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }


    private fun getDates(list: MutableList<Date>): List<Date> {
        // load dates of whole month
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(calendar.time)
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)
        return list
    }


}