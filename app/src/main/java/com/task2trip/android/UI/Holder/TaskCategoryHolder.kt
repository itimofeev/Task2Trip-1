package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.ImageLoader.ImageLoader
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.R

class TaskCategoryHolder(itemView: View) : BaseHolder<TaskCategory>(itemView) {
    private var tvCategoryName: TextView? = null
    private var ivCategoryImage: ImageView? = null

    init {
        tvCategoryName = itemView.findViewById(R.id.tvCategoryName)
        ivCategoryImage = itemView.findViewById(R.id.ivCategoryImage)
    }

    override fun setData(item: TaskCategory) {
        tvCategoryName?.text = item.defaultValue
        ivCategoryImage?.let {
            ImageLoader(item.imageUrl, it)
        }
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        tvCategoryName?.setOnClickListener(listener)
    }
}