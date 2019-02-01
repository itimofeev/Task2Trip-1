package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.TaskAddCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.TaskAddCategoryHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class TaskAddCategoryAdapter(items: List<TaskAddCategory>):
    BaseListAdapter<TaskAddCategoryHolder, TaskAddCategory>(items) {
    private var listener: ItemClickListener<TaskAddCategory>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_task_add_category
    }

    override fun returnViewHolder(view: View): TaskAddCategoryHolder {
        return TaskAddCategoryHolder(view)
    }

    override fun onItemClicked(item: TaskAddCategory, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<TaskAddCategory>?) {
        this.listener = listener
    }
}