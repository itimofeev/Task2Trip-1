package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment : BaseFragment() {
    var userRole = ""
    var taskItem: Task = MockData.getEmptyTask()

    override fun getArgs(args: Bundle?) {
        args?.let {
            userRole = it.getString(Constants.EXTRA_USER_ROLE, UserRole.NOT_AUTHORIZED.name)
            taskItem = it.getParcelable(Constants.EXTRA_TASK) ?: MockData.getEmptyTask()
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
        tvStatusDateTime.text = task.status.plus(task.canceledTime)
        tvTaskName.text = task.name
        tvTaskPrice.text = "${task.budgetEstimate} Rub"
        tvTaskLocation.text = "не определено"
        tvTaskDescription.text = task.description
    }

    private fun initApplyButton() {
        if (userRole == UserRole.LOCAL.name) {
            btTaskAddOffer.visibility = View.VISIBLE
            btTaskAddOffer.setOnClickListener {
                onApplyTaskClick(taskItem)
            }
        } else {
            btTaskAddOffer.visibility = View.GONE
        }
    }

    private fun onApplyTaskClick(task: Task) {
        val args = Bundle()
        args.putParcelable(Constants.EXTRA_TASK, task)
        navigateTo(R.id.taskAddOfferFragment, args)
    }
}
