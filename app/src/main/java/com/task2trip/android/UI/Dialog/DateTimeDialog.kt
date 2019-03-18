package com.task2trip.android.UI.Dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.task2trip.android.Common.Constants
import com.task2trip.android.R
import java.util.Calendar

class DateTimeDialog: DialogFragment() {
    private var initDateTime = 0L
    private var selectedDateTime = 0L
    private var isShowTime = true
    private var isStartDialog = true
    private var title = ""

    companion object {
        fun getInstance(title: String, isShowTime: Boolean, isStartDialog: Boolean, initDateTimeMillisec: Long = Calendar.getInstance().timeInMillis): DateTimeDialog {
            val fragment = DateTimeDialog()
            val args = Bundle()
            args.putLong(Constants.EXTRA_DIALOG_INIT_DATE_TIME, initDateTimeMillisec)
            args.putBoolean(Constants.EXTRA_DIALOG_IS_SHOW_TIME, isShowTime)
            args.putString(Constants.EXTRA_DIALOG_TITLE, title)
            args.putBoolean(Constants.EXTRA_DIALOG_IS_START, isStartDialog)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            initDateTime = it.getLong(Constants.EXTRA_DIALOG_INIT_DATE_TIME, Calendar.getInstance().timeInMillis)
            isShowTime = it.getBoolean(Constants.EXTRA_DIALOG_IS_SHOW_TIME, true)
            title = it.getString(Constants.EXTRA_DIALOG_TITLE, "")
            isStartDialog = it.getBoolean(Constants.EXTRA_DIALOG_IS_START, true)
            selectedDateTime = initDateTime
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater: LayoutInflater? = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View? = inflater?.inflate(R.layout.dialog_date_time, null)
        initComponents(view)
        return AlertDialog.Builder(context!!)
            .setPositiveButton("ok") { dialog, _ ->
                val args = Intent()
                args.putExtra(Constants.EXTRA_SELECTED_DATE_TIME, selectedDateTime)
                args.putExtra(Constants.EXTRA_DIALOG_IS_START, isStartDialog)
                targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, args)
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, Intent())
                dialog.dismiss()
            }
            .setView(view)
            .create()
    }

    private fun initComponents(view: View?) {
        view?.let {
            val timePicker = it.findViewById<TimePicker>(R.id.timePicker)
            val tvTitle = it.findViewById<TextView>(R.id.tvTitle)
            val datePicker = it.findViewById<CalendarView>(R.id.datePicker)
            timePicker.setIs24HourView(true)
            tvTitle.text = title
            val initCalendar = Calendar.getInstance()
            initCalendar.timeInMillis = initDateTime
            datePicker.date = initCalendar.timeInMillis
            datePicker.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val dateAndTime = Calendar.getInstance()
                if (selectedDateTime > 0L) {
                    dateAndTime.timeInMillis = selectedDateTime
                }
                dateAndTime.set(Calendar.YEAR, year)
                dateAndTime.set(Calendar.MONTH, month)
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                selectedDateTime = dateAndTime.timeInMillis
            }
            timePicker.currentHour = initCalendar.get(Calendar.HOUR_OF_DAY)
            timePicker.currentMinute = initCalendar.get(Calendar.MINUTE)
            timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
                val dateAndTime = Calendar.getInstance()
                if (selectedDateTime > 0L) {
                    dateAndTime.timeInMillis = selectedDateTime
                }
                dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                dateAndTime.set(Calendar.MINUTE, minute)
                selectedDateTime = dateAndTime.timeInMillis
            }
        }
    }
}

fun DateTimeDialog.show(targetFragment: Fragment?) {
    this.setTargetFragment(targetFragment, Constants.REQUEST_DIALOG_DATE_TIME)
    val manager = targetFragment?.fragmentManager
    manager?.let {
        this.show(it, this.javaClass::class.java.name)
    }
}