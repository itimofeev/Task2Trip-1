package com.task2trip.android.UI.Fragment.Task

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Common.toInt
import com.task2trip.android.Common.toPattern
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Model.Task.TaskSaveModel
import com.task2trip.android.Presenter.TaskAddParamsPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Dialog.DateTimeDialog
import com.task2trip.android.UI.Dialog.show
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.TaskParamsView
import kotlinx.android.synthetic.main.fragment_task_add_params.*
import java.util.Calendar

class TaskAddParamsFragment : BaseFragment(), TaskParamsView {
    private lateinit var presenter: TaskAddParamsPresenter
    private var categoryList = ArrayList<TaskCategory>()
    private var categorySelectedPosition = 0
    private val dateStart = Calendar.getInstance()
    private val dateEnd = Calendar.getInstance()

    override fun getArgs(args: Bundle?) {
        args?.let {
            val lst: ArrayList<TaskCategory>? = it.getParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST)
            if (!lst.isNullOrEmpty()) {
                categoryList.addAll(lst)
            }
            categorySelectedPosition = it.getInt(Constants.EXTRA_TASK_CATEGORY_SELECTED_POSITION, 0)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_add_params
    }

    override fun initComponents(view: View) {
        presenter = TaskAddParamsPresenter(this, view.context)
        initDateTimeCalendar()
        tvCategoryName.text = getSelectedCategory().defaultValue
        btAddMyTask.setOnClickListener {
            hideKeyboard()
            addMyTaskClick(TaskSaveModel(etTaskName.text.toString() + " " + etCountryAndCity.text.toString(),
                etTaskDescription.text.toString(), getSelectedCategory().id, etPrice.toInt(),
                Calendar.getInstance().toPattern(), Calendar.getInstance().toPattern(), MockData.getEmptyGeoLocations()))
        }
    }

    private fun initDateTimeCalendar() {
        layoutDateTime.setOnClickListener {
            onShowCalendar()
        }
        ivDateTime.setOnClickListener {
            onShowCalendar()
        }
        tvDateTimeFromTo.setOnClickListener {
            onShowCalendar()
        }
        ivArrowRight3.setOnClickListener {
            onShowCalendar()
        }
    }

    private fun getSelectedCategory(): TaskCategory {
        return if (categoryList.size > categorySelectedPosition) {
            categoryList[categorySelectedPosition]
        } else {
            MockData.getEmptyCategory()
        }
    }

    private fun onShowCalendar() {
        val dateAndTime = Calendar.getInstance()
        context?.let {
            DateTimeDialog.getInstance("Укажите НАЧАЛО задания", true, dateAndTime.timeInMillis)
                .show(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_DIALOG_DATE_TIME) {
            if (resultCode == Activity.RESULT_OK) {
                //
            } else if (resultCode == Activity.RESULT_CANCELED) {
                //
            }
        }
    }

    var dateCallback: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val dateAndTime = Calendar.getInstance()
            dateAndTime.set(Calendar.YEAR, year)
            dateAndTime.set(Calendar.MONTH, monthOfYear)
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            tvDateTimeFromTo.text = dateAndTime.toPattern("yyyy.MM.dd")
        }

    private fun addMyTaskClick(taskSaveModel: TaskSaveModel) {
        presenter.saveTask(taskSaveModel)
    }

    override fun onSaveTaskResult(task: Task) {
        if (task.id.isNotEmpty()) {
            val args = Bundle()
            with(args) {
                putBoolean(Constants.EXTRA_IS_MESSAGE, true)
                putString(Constants.EXTRA_MESSAGE_TEXT, "Задача с id=${task.id} успешно сохранена")
            }
            navigateTo(R.id.taskListTravelerFragment, args)
        } else {
            onMessage("Не удалось корректно сохранить задачу. Повторите позже!")
        }
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
