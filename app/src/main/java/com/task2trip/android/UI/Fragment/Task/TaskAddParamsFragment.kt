package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Common.toPattern
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Task
import com.task2trip.android.Model.TaskCategory
import com.task2trip.android.Model.TaskSaveModel
import com.task2trip.android.Presenter.TaskAddParamsPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.TaskParamsView
import kotlinx.android.synthetic.main.fragment_task_add_params.*
import java.util.Calendar

class TaskAddParamsFragment : BaseFragment(), TaskParamsView {
    private lateinit var presenter: TaskAddParamsPresenter
    private var categoryList = ArrayList<TaskCategory>()
    private var categorySelectedPosition = 0

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
        tvCategoryName.text = getSelectedCategory().defaultValue
        btAddMyTask.setOnClickListener {
            hideKeyboard()
            addMyTaskClick(TaskSaveModel(etTaskName.text.toString(), etTaskDescription.text.toString(),
                getSelectedCategory().id, 1299,
                Calendar.getInstance().toPattern(), Calendar.getInstance().toPattern()))
        }
    }

    private fun getSelectedCategory(): TaskCategory {
        return if (categoryList.size > categorySelectedPosition) {
            categoryList[categorySelectedPosition]
        } else {
            MockData.getEmptyCategory()
        }
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
