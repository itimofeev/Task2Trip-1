package com.task2trip.android

import android.os.Bundle
import android.view.View
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment : BaseFragment() {
    var isPerformer = false

    override fun getArgs(args: Bundle?) {
        args?.let {
            isPerformer = it.getBoolean("", false)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_details
    }

    override fun initComponents(view: View) {
        initApplyButton()
    }

    private fun initApplyButton() {
        if (isPerformer) {
            btApplyTask.visibility = View.VISIBLE
            btApplyTask.setOnClickListener {
                onApplyTaskClick("1234")
            }
        } else {
            btApplyTask.visibility = View.GONE
        }
    }

    private fun onApplyTaskClick(taskId: String) {
        //
    }
}
