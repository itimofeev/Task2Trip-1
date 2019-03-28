package com.task2trip.android.UI.Fragment.Task

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Common.toCalendar
import com.task2trip.android.Common.toInt
import com.task2trip.android.Common.toPattern
import com.task2trip.android.Model.Location.GeoCountryCity
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Model.Task.TaskSaveModel
import com.task2trip.android.Presenter.SearchLocationPresenter
import com.task2trip.android.Presenter.TaskAddParamsPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Dialog.DateTimeDialog
import com.task2trip.android.UI.Dialog.show
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Widget.SearchLocationFieldCallback
import com.task2trip.android.View.SearchLocationView
import com.task2trip.android.View.TaskParamsView
import kotlinx.android.synthetic.main.fragment_task_add_params.*
import java.util.Calendar

class TaskAddParamsFragment : BaseFragment(), TaskParamsView, SearchLocationView, SearchLocationFieldCallback {
    private lateinit var presenterTask: TaskAddParamsPresenter
    private lateinit var presenterSearch: SearchLocationPresenter
    private var categoryList = ArrayList<TaskCategory>()
    private var categorySelectedPosition = 0
    private var dateStart = Calendar.getInstance()
    private var dateEnd = Calendar.getInstance()
    private var taskForEdit = MockData.getEmptyTask()
    private var isEditTask = false

    override fun getArgs(args: Bundle?) {
        args?.let {
            val lst: ArrayList<TaskCategory>? = it.getParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST)
            taskForEdit = it.getParcelable<Task>(Constants.EXTRA_TASK) ?: MockData.getEmptyTask()
            if (taskForEdit.id.isNotEmpty()) {
                isEditTask = true
            }
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
        setToolbar(toolbar)
        initPresenter(view)
        initDateTimeCalendar()
        searchLocationView.setSearchLocationFieldCallback(this)
        tvCategoryName.text = getSelectedCategory().defaultValue
        if (isEditTask) {
            btAddMyTask.text = getString(R.string.save)
            updateFields(taskForEdit)
        } else {
            btAddMyTask.text = getString(R.string.task_add_my)
        }
        btAddMyTask.setOnClickListener {
            hideKeyboard()
            val taskForSave = TaskSaveModel(etTaskName.text.toString(),
                etTaskDescription.text.toString(),
                getSelectedCategory().id,
                etPrice.toInt(),
                dateStart.toPattern(),
                dateEnd.toPattern(),
                searchLocationView.getItem())
            if (isEditTask) {
                taskForSave.categoryId = taskForEdit.category.id
                editMyTaskClick(taskForEdit.id, taskForSave)
            } else {
                addMyTaskClick(taskForSave)
            }
        }
    }

    private fun initPresenter(view: View) {
        presenterTask = TaskAddParamsPresenter(this, view.context)
        presenterSearch = SearchLocationPresenter(this, view.context)
    }

    private fun updateFields(task: Task) {
        tvCategoryName.text = task.category.defaultValue
        etTaskName.setText(task.name)
        etTaskDescription.setText(task.description)
        dateStart = task.fromDate.toCalendar()
        dateEnd = task.toDate.toCalendar()
        setDateTime(dateStart, dateEnd)
        etPrice.setText(task.budgetEstimate.toString())
    }

    private fun initDateTimeCalendar() {
        layoutDateTime.setOnClickListener {
            onShowCalendar(true)
        }
        ivDateTime.setOnClickListener {
            onShowCalendar(true)
        }
        tvDateTimeFromTo.setOnClickListener {
            onShowCalendar(true)
        }
        ivArrowRight3.setOnClickListener {
            onShowCalendar(true)
        }
    }

    private fun getSelectedCategory(): TaskCategory {
        return if (categoryList.size > categorySelectedPosition) {
            categoryList[categorySelectedPosition]
        } else {
            MockData.getEmptyCategory()
        }
    }

    private fun onShowCalendar(isStartDialog: Boolean) {
        var dateAndTime = Calendar.getInstance()
        context?.let {
            val title = if (isStartDialog) {
                dateAndTime = dateStart
                getString(R.string.choose_begin_task_date)
            } else {
                dateAndTime = dateEnd
                getString(R.string.choose_end_task_date)
            }
            DateTimeDialog.getInstance(title, true, isStartDialog, dateAndTime.timeInMillis)
                .show(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_DIALOG_DATE_TIME) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    val selectedDT: Long = it.getLongExtra(Constants.EXTRA_SELECTED_DATE_TIME, 0L)
                    val isStartDialog: Boolean = it.getBooleanExtra(Constants.EXTRA_DIALOG_IS_START, true)
                    val clndr = Calendar.getInstance()
                    clndr.timeInMillis = selectedDT
                    if (isStartDialog) {
                        dateStart = clndr
                        onShowCalendar(false)
                    } else {
                        dateEnd = clndr
                    }
                    setDateTime(dateStart, dateEnd)
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                //
            }
        }
    }

    private fun setDateTime(dateStart: Calendar, dateEnd: Calendar) {
        tvDateTimeFromTo.text = "с "
            .plus(dateStart.toPattern(Constants.TIME_PATTERN_DDMMYYYY))
            .plus(" по ")
            .plus(dateEnd.toPattern(Constants.TIME_PATTERN_DDMMYYYY))
    }

    private fun addMyTaskClick(taskSaveModel: TaskSaveModel) {
        presenterTask.saveTask(taskSaveModel)
    }

    private fun editMyTaskClick(taskId: String, taskSaveModel: TaskSaveModel) {
        presenterTask.editTask(taskId, taskSaveModel)
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

    override fun onUpdateTaskResult(task: Task) {
        if (task.id.isNotEmpty()) {
            val args = Bundle()
            with(args) {
                putBoolean(Constants.EXTRA_IS_MESSAGE, true)
                putString(Constants.EXTRA_MESSAGE_TEXT, "Задача с id=${task.id} успешно обновлена")
            }
            navigateTo(R.id.taskListTravelerFragment, args)
        } else {
            onMessage("Не удалось корректно обновить задачу. Повторите позже!")
        }
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            viewLoadAndMessage?.show()
        } else {
            viewLoadAndMessage?.hide()
        }
        viewLoadAndMessage?.setProgress(isProgress)
    }

    override fun onSearchLocationResult(items: List<GeoCountryCity>) {
        searchLocationView?.setDataForSearch(items)
    }

    override fun onTextLocationChanged(text: String) {
        // Ищем, если хотя бы введено searchLocationView.getMinimumTextSearchSize() символов
        if (text.length >= searchLocationView?.getMinimumTextSearchSize() ?: 0) {
            presenterSearch.getCountryAndCityByText(text)
        }
    }

    override fun onItemLocationChanged(item: GeoCountryCity) {
        //
    }

    override fun onLocationClick() {
        //
    }
}
