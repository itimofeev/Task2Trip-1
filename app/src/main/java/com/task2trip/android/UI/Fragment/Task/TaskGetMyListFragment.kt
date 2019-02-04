package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_get_my_list.*

class TaskGetMyListFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_get_my_list
    }

    override fun initComponents(view: View) {
        btCreateTask.setOnClickListener {
            createTaskOnClick()
        }
        btMyTask.setOnClickListener {
            getMyTaskOnClick()
        }
    }

    private fun createTaskOnClick() {
        navigateTo(R.id.taskCategoryFragment, Bundle())
    }

    private fun getMyTaskOnClick() {
        navigateTo(R.id.taskListFragment, Bundle())
    }
}
