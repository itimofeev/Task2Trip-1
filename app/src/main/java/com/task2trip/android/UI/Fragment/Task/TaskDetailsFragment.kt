package com.task2trip.android.UI.Fragment.Task

import android.os.Build
import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.*
import com.task2trip.android.Model.LocalStoreManager
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Offer
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskStatus
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment : BaseFragment() {
    private var isEditTask = false
    private var taskItem: Task = MockData.getEmptyTask()
    private var offerItem: Offer = MockData.getEmptyOffer()
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
            offerItem = it.getParcelable(Constants.EXTRA_OFFER) ?: MockData.getEmptyOffer()
            val userRoleName = it.getString(Constants.EXTRA_USER_ROLE, UserRole.TRAVELER.name) ?: MockData.getEmptyUser().getRole().name
            userRole = UserRole.getName(userRoleName)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_details
    }

    override fun initComponents(view: View) {
        setToolbar(toolbar)
        val storage = LocalStoreManager(view.context)
        // Если моя собственная задача
        if (taskItem.user.getId() == storage.get(Constants.EXTRA_USER_ID, "")) {
            isEditTask = true
        }
        initApplyButton()
        setData(taskItem)
    }

    private fun setData(task: Task) {
        val dateTime = task.toDate.toCalendar()
        var statusAndTime = task.status.parseStatusValue().getMyName()
        dateTime?.let {
            statusAndTime += ". актуальна до: " + it.toPattern("dd.MM.yyyy HH:mm")
        }
        tvStatusDateTime.text = statusAndTime
        tvTaskName.text = task.name
        tvTaskPrice.text = task.budgetEstimate.toString().plus(" Rub")
        tvTaskLocation.text = getString(R.string.error_no_location)
        tvTaskDescription.text = task.description
    }

    private fun initApplyButton() {
        if (isEditTask) {
            when(taskItem.status.parseStatusValue()) {
                TaskStatus.NEW -> {
                    btTaskOfferOrEdit.text = getString(R.string.task_edit)
                    btTaskOfferOrEdit.setOnClickListener {
                        onApplyEditClick(taskItem)
                    }
                }
                TaskStatus.IN_PROGRESS -> {
                    btTaskOfferOrEdit.text = getString(R.string.task_finish)
                    btTaskOfferOrEdit.setOnClickListener {
                        onApplyFinishClick(taskItem, taskItem.chosenOfferId)
                    }
                }
                else -> {
                    setButtonFinished()
                }
            }
        } else {
            if (userRole == UserRole.TRAVELER) {
                btTaskOfferOrEdit.visibility = View.GONE
            }
            btTaskOfferOrEdit.text = getString(R.string.task_add_offer)
            btTaskOfferOrEdit.setOnClickListener {
                onApplyOfferTaskClick(taskItem)
            }
        }
    }

    private fun setButtonFinished() {
        btTaskOfferOrEdit.text = getString(R.string.task_finished)
        btTaskOfferOrEdit.isEnabled = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btTaskOfferOrEdit.setTextColor(resources.getColor(R.color.gray, null))
        } else {
            btTaskOfferOrEdit.setTextColor(resources.getColor(R.color.gray))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btTaskOfferOrEdit.background = resources.getDrawable(R.drawable.bg_button_border, null)
        } else {
            btTaskOfferOrEdit.background = resources.getDrawable(R.drawable.bg_button_border)
        }
    }

    private fun onApplyOfferTaskClick(task: Task) {
        val args = Bundle()
        args.putParcelable(Constants.EXTRA_TASK, task)
        when (userRole) {
            UserRole.LOCAL -> navigateTo(R.id.taskAddOfferFragment, args)
            UserRole.TRAVELER -> navigateTo(R.id.profileFragment, args)
            else -> navigateTo(R.id.loginRegisterFragment, Bundle())
        }
    }

    private fun onApplyEditClick(task: Task) {
        val args = Bundle()
        args.putParcelable(Constants.EXTRA_TASK, task)
        navigateTo(R.id.taskAddParamsFragment, args)
    }

    private fun onApplyFinishClick(task: Task, offerId: String) {
        val args = Bundle().apply {
            putString(Constants.EXTRA_TASK_ID, task.id)
            putString(Constants.EXTRA_OFFER_ID, offerId)
        }
        navigateTo(R.id.taskFinishFragment, args)
    }
}