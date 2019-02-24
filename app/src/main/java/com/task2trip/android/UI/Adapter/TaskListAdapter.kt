package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.TaskListHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class TaskListAdapter(items: List<Task>): BaseListAdapter<TaskListHolder, Task>(items) {
    private var listener: ItemClickListener<Task>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_task
    }

    override fun returnViewHolder(view: View): TaskListHolder {
        return TaskListHolder(view)
    }

    override fun onItemClicked(item: Task, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<Task>?) {
        this.listener = listener
    }
}