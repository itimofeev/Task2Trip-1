package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_list_not_authorized.*

class TaskListNotAuthorizedFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_list_not_authorized
    }

    override fun initComponents(view: View) {
        btCreateTask.setOnClickListener {
            navigateTo(R.id.taskCategoryFragment, null)
        }
    }
}