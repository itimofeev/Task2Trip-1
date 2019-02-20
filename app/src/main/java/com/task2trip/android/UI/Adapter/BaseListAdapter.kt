package com.task2trip.android.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.task2trip.android.UI.Holder.BaseHolder
import com.task2trip.android.UI.Listener.ItemClickListener

abstract class BaseListAdapter<VH: BaseHolder<IL>, IL: Any>(@NonNull private val items: List<IL>): RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(setLayoutRes(), parent, false)
        return returnViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: IL = items[position]
        val listener = View.OnClickListener {
            onItemClicked(item, position)
        }
        holder.setData(item)
        holder.setItemClickListener(listener)
    }

    fun getItems(): List<IL> {
        return items
    }

    abstract fun setLayoutRes(): Int
    abstract fun returnViewHolder(view: View): VH
    abstract fun onItemClicked(item: IL, position: Int)
    abstract fun setClickListener(listener: ItemClickListener<IL>?)
}