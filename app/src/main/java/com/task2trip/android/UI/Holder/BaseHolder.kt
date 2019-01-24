package com.task2trip.android.UI.Holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun setData(item: Any)
}