package com.task2trip.android.UI.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.task2trip.android.Model.Task.TaskStatus
import com.task2trip.android.UI.Holder.TaskStatusHolder

class TaskStatusAdapter(context: Context, resource: Int, statusItems: MutableList<TaskStatus>):
    ArrayAdapter<TaskStatus>(context, resource, statusItems) {

    private val items: MutableList<TaskStatus> = ArrayList()
    private var resIdLayout: Int = 0

    init {
        items.clear()
        items.addAll(statusItems)
        resIdLayout = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view: View? = convertView
        var holder: TaskStatusHolder? = null
        if (view == null) {
            view = LayoutInflater.from(parent.context).inflate(resIdLayout, parent, false)
            holder = TaskStatusHolder(view)
            view?.tag = holder
        }
        holder?.setData(items[position])

        return view
    }

    override fun getItem(position: Int): TaskStatus? {
        return super.getItem(position)
    }

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view: View? = convertView
        var holder: TaskStatusHolder? = null
        if (view == null) {
            view = LayoutInflater.from(parent.context).inflate(resIdLayout, parent, false)
            holder = TaskStatusHolder(view)
            view.tag = holder
        }
        holder?.setData(items[position])

        return view
    }
}
