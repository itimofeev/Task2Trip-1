package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.TaskAddCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskAddCategoryAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import kotlinx.android.synthetic.main.fragment_task_list_traveler.*

class TaskListTravelerFragment : BaseFragment(), ItemClickListener<TaskAddCategory> {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_list_traveler
    }

    override fun initComponents(view: View) {
        initCategoryRecycleView(view)
    }

    private fun initCategoryRecycleView(view: View) {
        val adapter = TaskAddCategoryAdapter(MockData.dataTaskAddCategory())
        adapter.setClickListener(this)
        rvTaskList.setHasFixedSize(true)
        rvTaskList.layoutManager = LinearLayoutManager(view.context)
        rvTaskList.adapter = adapter
    }

    override fun onItemClick(item: TaskAddCategory, position: Int) {
        val args = Bundle()
        args.putParcelableArrayList(Constants.EXTRA_TASK_CATEGORY_LIST, ArrayList(MockData.dataTaskAddCategory()))
        args.putInt(Constants.EXTRA_TASK_CATEGORY_SELECTED_POSITION, position)
        navigateTo(R.id.taskAddParamsFragment, args)
    }
}
