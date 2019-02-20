package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Task
import com.task2trip.android.Model.TaskList
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

    override fun getArgs(args: Bundle?) {
        args?.let {
            isMessage = it.getBoolean(Constants.EXTRA_IS_MESSAGE, false)
            message = it.getString(Constants.EXTRA_MESSAGE_TEXT, "")
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
        }
    }

    private fun initPresenter(view: View) {
        presenter = TaskListPresenter(this, view.context)
        presenter.getAllTask()
    }

    private fun initCategoryRecycleView(view: View) {
        rvTaskList.setHasFixedSize(true)
        rvTaskList.layoutManager = LinearLayoutManager(view.context)
    }

    override fun onTaskListResult(taskResult: TaskList) {
        val adapter = TaskListAdapter(taskResult.payload)
        adapter.setClickListener(this)
        rvTaskList.adapter = adapter
    }

    override fun onItemClick(item: Task, position: Int) {
//        val args = Bundle()
//        args.putParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST, ArrayList(MockData.dataTaskAddCategory()))
//        args.putInt(Constants.EXTRA_TASK_CATEGORY_SELECTED_POSITION, position)
//        navigateTo(R.id.taskAddParamsFragment, args)
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
