package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.TaskAddCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener

class TaskListTravelerFragment : BaseFragment(), ItemClickListener<TaskAddCategory> {
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
        if (isMessage) {
            onMessage(message)
        }
    }

    private fun initCategoryRecycleView(view: View) {
//        val adapter = TaskCategoryAdapter(MockData.dataTaskAddCategory())
//        adapter.setClickListener(this)
//        rvTaskList.setHasFixedSize(true)
//        rvTaskList.layoutManager = LinearLayoutManager(view.context)
//        rvTaskList.adapter = adapter
    }

    override fun onItemClick(item: TaskAddCategory, position: Int) {
        val args = Bundle()
        args.putParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST, ArrayList(MockData.dataTaskAddCategory()))
        args.putInt(Constants.EXTRA_TASK_CATEGORY_SELECTED_POSITION, position)
        navigateTo(R.id.taskAddParamsFragment, args)
    }
}
