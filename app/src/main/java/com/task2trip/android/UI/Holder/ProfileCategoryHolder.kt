package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.task2trip.android.Model.UserCategory
import com.task2trip.android.R

class ProfileCategoryHolder(itemView: View) : BaseHolder<UserCategory>(itemView) {
    private var tvCategoryName: TextView? = null
    private var chIsSelected: CheckBox? = null

    init{
        tvCategoryName = itemView.findViewById<TextView>(R.id.tvCategoryName)
        chIsSelected = itemView.findViewById<CheckBox>(R.id.chIsSelected)
    }

    override fun setData(item: UserCategory) {
        tvCategoryName?.text = item.catName
        chIsSelected?.isChecked = item.isChecked
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
    }
}