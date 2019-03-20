package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.TaskCategoryHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class TaskCategoryAdapter(items: List<TaskCategory>): BaseListAdapter<TaskCategoryHolder, TaskCategory>(items) {
    private var listener: ItemClickListener<TaskCategory>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_task_add_category
    }

    override fun returnViewHolder(view: View): TaskCategoryHolder {
        return TaskCategoryHolder(view)
    }

    override fun onItemClicked(item: TaskCategory, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<TaskCategory>?) {
        this.listener = listener
    }
}