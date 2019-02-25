package com.task2trip.android.UI.Fragment.Search

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
import kotlinx.android.synthetic.main.fragment_task_search.*

class SearchFragment : BaseFragment(), TaskListView, ItemClickListener<Task> {
    private lateinit var presenter: TaskListPresenter
    private var taskCategory = ""
    private var taskCountryAndCity = ""
    private var taskStatus = ""
    private var userRole = ""

    override fun getArgs(args: Bundle?) {
        args?.let {
            taskCategory = it.getString(Constants.EXTRA_TASK_SEARCH_CATEGORY, "")
            taskCountryAndCity = it.getString(Constants.EXTRA_TASK_SEARCH_COUNTRY_CITY, "")
            taskStatus = it.getString(Constants.EXTRA_TASK_SEARCH_STATUS, "")
            userRole = it.getString(Constants.EXTRA_USER_ROLE, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_search
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        rvSearchResultList.setHasFixedSize(true)
        rvSearchResultList.layoutManager = LinearLayoutManager(view.context)
    }

    private fun initPresenter(view: View) {
        presenter = TaskListPresenter(this, view.context)
        presenter.searchTasks(null, null, null, null, null, taskStatus)
    }

    override fun onTaskListResult(taskResult: TaskList) {
        val adapter = TaskListAdapter(taskResult.payload)
        adapter.setClickListener(this)
        rvSearchResultList.adapter = adapter
    }

    override fun onItemClick(item: Task, position: Int) {
        val args = Bundle()
        with(args) {
            putParcelable(Constants.EXTRA_TASK, item)
            putBoolean(Constants.EXTRA_TASK_IS_EDIT, false)
            putString(Constants.EXTRA_USER_ROLE, userRole)
        }
        navigateTo(R.id.taskDetailsFragment, args)
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
