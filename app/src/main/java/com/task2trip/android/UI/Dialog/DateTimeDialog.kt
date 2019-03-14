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

class DateTimeDialog: DialogFragment() {
    private var initDateTime = 0L
    private var isShowTime = true
    private var title = ""

    companion object {
        fun getInstance(title: String, isShowTime: Boolean, initDateTimeMillisec: Long = 0L): DateTimeDialog {
            val fragment = DateTimeDialog()
            val args = Bundle()
            args.putLong(Constants.EXTRA_DIALOG_INIT_DATE_TIME, initDateTimeMillisec)
            args.putBoolean(Constants.EXTRA_DIALOG_IS_SHOW_TIME, isShowTime)
            args.putString(Constants.EXTRA_DIALOG_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            initDateTime = it.getLong(Constants.EXTRA_DIALOG_INIT_DATE_TIME, 0L)
            isShowTime = it.getBoolean(Constants.EXTRA_DIALOG_IS_SHOW_TIME, true)
            title = it.getString(Constants.EXTRA_DIALOG_TITLE, "")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater: LayoutInflater? = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View? = inflater?.inflate(R.layout.dialog_date_time, null)
        initComponents(view)
        return AlertDialog.Builder(context!!)
            .setPositiveButton("ok") { dialog, _ ->
                targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, Intent())
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
            datePicker.setOnDateChangeListener { view, year, month, dayOfMonth ->  }
            timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->  }
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