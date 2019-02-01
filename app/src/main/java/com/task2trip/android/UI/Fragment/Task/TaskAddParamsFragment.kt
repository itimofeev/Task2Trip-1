package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_add_params.*

class TaskAddParamsFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_add_params
    }

    override fun initComponents(view: View) {
        btAddMyTask.setOnClickListener {
            addMyTaskClick()
        }
    }

    private fun addMyTaskClick() {
        navigateTo(R.id.taskListFragment, Bundle())
    }
}
