package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.R

class TaskCategorySearchHolder(itemView: View) : BaseHolder<TaskCategory>(itemView), CompoundButton.OnCheckedChangeListener {
    private var tvCategoryName: TextView? = null
    private var switchCategory: SwitchCompat? = null

    init {
        tvCategoryName = itemView.findViewById<TextView>(R.id.tvCategoryName)
        switchCategory = itemView.findViewById<SwitchCompat>(R.id.switchCategory)
    }

    override fun setData(item: TaskCategory) {
        tvCategoryName?.text = item.defaultValue
        switchCategory?.setOnCheckedChangeListener(this)
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        //
    }
}