package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.Task
import com.task2trip.android.R

class TaskListHolder(itemView: View) : BaseHolder<Task>(itemView) {
    private var tvTaskName: TextView? = null
    private var ivTaskCategoryImage: ImageView? = null

    init {
        tvTaskName = itemView.findViewById<TextView>(R.id.tvTaskName)
        ivTaskCategoryImage = itemView.findViewById<ImageView>(R.id.ivCategoryImage)
    }

    override fun setData(item: Task) {
        tvTaskName?.text = item.name
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        tvTaskName?.setOnClickListener(listener)
    }
}