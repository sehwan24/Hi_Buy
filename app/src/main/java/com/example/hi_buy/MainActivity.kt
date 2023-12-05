package com.example.hi_buy

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
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
    lateinit var item_tv1 : TextView
    lateinit var item_tv2 : TextView
    lateinit var time_tv1 : TextView
    lateinit var time_tv2 : TextView
    lateinit var image : ImageView
    lateinit var med1 : ImageButton
    lateinit var setting_btn : ImageButton
    lateinit var mail_btn : ImageButton
    lateinit var med3 : ImageButton
    lateinit var med2 : ImageButton
    lateinit var plus_btn : ImageButton
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
        item_tv1 = findViewById(R.id.item_tv1)
        item_tv2 = findViewById(R.id.item_tv2)
        time_tv1 = findViewById(R.id.time_tv1)
        time_tv2 = findViewById(R.id.time_tv2)
        image = findViewById(R.id.image)
        med1 = findViewById(R.id.med1)
        med3 = findViewById(R.id.med3)
        mail_btn = findViewById(R.id.mail_btn)
        setting_btn = findViewById(R.id.setting_btn)
        plus_btn = findViewById(R.id.plus_btn)
        med2 = findViewById(R.id.med2)
        //cl_calendar_item = findViewById(R.id.cl_calendar_item)
        //tv_date_calendar_item = findViewById(R.id.tv_date_calendar_item)



        item_tv1.setText("")
        item_tv2.setText("")
        time_tv1.setText("")
        time_tv2.setText("")


        btn1.setOnClickListener {
            val shop = Intent(this, ShopActivity::class.java)
            startActivity(shop)
        }

        med1.setOnClickListener {
            med1.setImageResource(R.drawable.group_3)
            image.setImageResource(R.drawable.character2)
        }

        med2.setOnClickListener {
            med2.setImageResource(R.drawable.group_3)
        }

        med3.setOnClickListener {
            med3.setImageResource(R.drawable.group_3)
            image.setImageResource(R.drawable.character5)
            setting_btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#41FF5F"))
            mail_btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#41FF5F"))
            btn1.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#41FF5F"))
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
                println(cal[Calendar.DAY_OF_WEEK])

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
                item_tv1.setText("item1")
                item_tv2.setText("item2")
                time_tv1.setText("time1")
                time_tv2.setText("time2")
                med1.visibility = View.VISIBLE
                med2.visibility = View.VISIBLE
                med3.visibility = View.VISIBLE
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
            initialPositionIndex = currentDay-1
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