package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.ImageLoader.ImageLoader
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.R

class TaskListHolder(itemView: View) : BaseHolder<Task>(itemView) {
    private var ivTaskCategoryImage: ImageView? = null
    private var tvTaskDescription: TextView? = null
    private var tvTaskPrice: TextView? = null
    private var tvTaskPerformerName: TextView? = null

    init {
        ivTaskCategoryImage = itemView.findViewById<ImageView>(R.id.ivCategoryImage)
        tvTaskDescription = itemView.findViewById<TextView>(R.id.tvTaskDescription)
        tvTaskPrice = itemView.findViewById<TextView>(R.id.tvTaskPrice)
        tvTaskPerformerName = itemView.findViewById<TextView>(R.id.tvTaskPerformerName)
    }

    override fun setData(item: Task) {
        ivTaskCategoryImage?.let {
            if (item.category.imageUrl == null) {
                item.category.imageUrl = ""
            }
            ImageLoader(item.category.imageUrl, it)
        }
        tvTaskDescription?.text = item.description
        tvTaskPrice?.text = "${item.budgetEstimate} Rub"
        tvTaskPerformerName?.text = "имя не указано"
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        ivTaskCategoryImage?.setOnClickListener(listener)
        tvTaskDescription?.setOnClickListener(listener)
        tvTaskPrice?.setOnClickListener(listener)
        tvTaskPerformerName?.setOnClickListener(listener)
    }
}