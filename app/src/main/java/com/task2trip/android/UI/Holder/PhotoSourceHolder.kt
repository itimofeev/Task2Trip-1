package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.TextView
import com.task2trip.android.R

class PhotoSourceHolder(itemView: View) : BaseHolder<String>(itemView) {
    private var tvSourceName: TextView? = null

    init {
        tvSourceName = itemView.findViewById<TextView>(R.id.tvSourceName)
    }

    override fun setData(item: String) {
        tvSourceName?.text = item
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        tvSourceName?.setOnClickListener(listener)
    }
}