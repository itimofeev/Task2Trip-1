package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Common.toCalendar
import com.task2trip.android.Common.toPattern
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment : BaseFragment() {
    private var isEditTask = false
    private var taskItem: Task = MockData.getEmptyTask()
    private var userRole: UserRole = MockData.getEmptyUser().getRole()

    companion object {
        fun getInstance(task: Task, isEdit: Boolean, userRoleName: String): TaskDetailsFragment {
            val fragment = TaskDetailsFragment()
            val args = Bundle()
            with(args) {
                putParcelable(Constants.EXTRA_TASK, task)
                putBoolean(Constants.EXTRA_TASK_IS_EDIT, isEdit)
                putString(Constants.EXTRA_USER_ROLE, userRoleName)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun getArgs(args: Bundle?) {
        args?.let {
            isEditTask = it.getBoolean(Constants.EXTRA_TASK_IS_EDIT, false)
            taskItem = it.getParcelable(Constants.EXTRA_TASK) ?: MockData.getEmptyTask()
            val userRoleName = it.getString(Constants.EXTRA_USER_ROLE, UserRole.TRAVELER.name) ?: MockData.getEmptyUser().getRole().name
            userRole = UserRole.getName(userRoleName)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_details
    }

    override fun initComponents(view: View) {
        initApplyButton()
        setData(taskItem)
    }

    private fun setData(task: Task) {
        val dateTime = task.canceledTime.toCalendar()
        tvStatusDateTime.text = task.status.plus(dateTime.toPattern("dd.MM.yyyy HH:mm"))
        tvTaskName.text = task.name
        tvTaskPrice.text = "${task.budgetEstimate} Rub"
        tvTaskLocation.text = "Местоположение не определено"
        tvTaskDescription.text = task.description
    }

    private fun initApplyButton() {
        if (isEditTask) {
            btTaskOfferOrEdit.text = getString(R.string.task_edit)
        } else {
            if (userRole == UserRole.TRAVELER) {
                btTaskOfferOrEdit.visibility = View.GONE
            }
            btTaskOfferOrEdit.text = getString(R.string.task_add_offer)
        }
        btTaskOfferOrEdit.setOnClickListener {
            onApplyTaskClick(taskItem)
        }
    }

    private fun onApplyTaskClick(task: Task) {
        val args = Bundle()
        args.putParcelable(Constants.EXTRA_TASK, task)
        if (isEditTask) {
            navigateTo(R.id.taskAddParamsFragment, args)
        } else {
            when (userRole) {
                UserRole.LOCAL -> navigateTo(R.id.taskAddOfferFragment, args)
                UserRole.TRAVELER -> navigateTo(R.id.profileFragment, args)
                else -> navigateTo(R.id.loginRegisterFragment, Bundle())
            }
        }
    }
}
