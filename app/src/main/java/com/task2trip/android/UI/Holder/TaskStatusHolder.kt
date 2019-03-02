package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.TextView
import com.task2trip.android.Model.Task.TaskStatus
import com.task2trip.android.R

class TaskStatusHolder(itemView: View) : BaseHolder<TaskStatus>(itemView) {
    private var tvStatusName: TextView = itemView.findViewById(R.id.tvStatusName)

    override fun setData(item: TaskStatus) {
        tvStatusName.text = item.name
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        //
    }
}