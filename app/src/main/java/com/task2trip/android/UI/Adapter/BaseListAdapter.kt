package com.task2trip.android.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task2trip.android.UI.Holder.BaseHolder

abstract class BaseListAdapter<VH: BaseHolder, IL: Any>(private val items: List<IL>): RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(setLayoutRes(), parent, false)
        return returnViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: IL = items[position]
        holder.setData(item)
    }

    abstract fun setLayoutRes(): Int
    abstract fun returnViewHolder(view: View): VH
}