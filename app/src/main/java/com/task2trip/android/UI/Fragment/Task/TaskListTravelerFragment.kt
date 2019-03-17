package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskList
import com.task2trip.android.Presenter.TaskListPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskListAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.TaskListView
import kotlinx.android.synthetic.main.fragment_task_list_traveler.*

class TaskListTravelerFragment : BaseFragment(), TaskListView, ItemClickListener<Task> {
    private lateinit var presenter: TaskListPresenter
    private var message = ""
    private var isMessage = false
    private var userId = ""
    private var userRoleStr = ""

    companion object {
        fun getInstance(userId: String, userRole: String): TaskListTravelerFragment {
            val fragment = TaskListTravelerFragment()
            val args = Bundle()
            args.putString(Constants.EXTRA_USER_ID, userId)
            args.putString(Constants.EXTRA_USER_ROLE, userRole)
            fragment.arguments = args
            return fragment
        }
    }

    override fun getArgs(args: Bundle?) {
        args?.let {
            isMessage = it.getBoolean(Constants.EXTRA_IS_MESSAGE, false)
            message = it.getString(Constants.EXTRA_MESSAGE_TEXT, "")
            userId = it.getString(Constants.EXTRA_USER_ID, "")
            userRoleStr = it.getString(Constants.EXTRA_USER_ROLE, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_list_traveler
    }

    override fun initComponents(view: View) {
        initCategoryRecycleView(view)
        initPresenter(view)
        if (isMessage) {
            onMessage(message)
            viewLoadAndMessage.setMessage(message)
        }
        btCreateTask.setOnClickListener {
            navigateTo(R.id.taskCategoryFragment, Bundle())
        }
    }

    private fun initPresenter(view: View) {
        presenter = TaskListPresenter(this, view.context)
        presenter.getTasksByUserId(userId)
    }

    private fun initCategoryRecycleView(view: View) {
        rvTaskList.setHasFixedSize(true)
        rvTaskList.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onTaskListResult(taskResult: TaskList) {
        val adapter = TaskListAdapter(taskResult.payload)
        adapter.setClickListener(this)
        rvTaskList?.adapter = adapter
        if (taskResult.total < 1) {
            viewLoadAndMessage.setMessage("У вас еще не было задач!")
        } else {
            viewLoadAndMessage.hide()
        }
    }

    override fun onItemClick(item: Task, position: Int) {
        val args = Bundle()
        with(args) {
            putParcelable(Constants.EXTRA_TASK, item)
            putBoolean(Constants.EXTRA_TASK_IS_EDIT, true)
            putString(Constants.EXTRA_USER_ROLE, userRoleStr)
        }
        navigateTo(R.id.taskDetailOfferPagerFragment, args)
    }

    override fun onProgress(isProgress: Boolean) {
        viewLoadAndMessage.setProgress(isProgress)
    }
}
