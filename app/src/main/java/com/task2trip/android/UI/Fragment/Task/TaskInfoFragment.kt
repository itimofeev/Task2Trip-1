package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_info.*

class TaskInfoFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_info
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
