package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.User.UserCategoryForUsed
import com.task2trip.android.R

class ProfileMainCategoryHolder(itemView: View) : BaseHolder<UserCategoryForUsed>(itemView) {
    private var tvCategoryName: TextView? = null
    private var tvCategoryDescription: TextView? = null
    private var ivArrowRight: ImageView? = null

    init {
        tvCategoryName = itemView.findViewById<TextView>(R.id.tvCategoryName)
        tvCategoryDescription = itemView.findViewById<TextView>(R.id.tvCategoryDescription)
        ivArrowRight = itemView.findViewById<ImageView>(R.id.ivArrowRight)
    }

    override fun setData(item: UserCategoryForUsed) {
        tvCategoryName?.text = item.catName
        tvCategoryDescription?.text = item.shortText
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        tvCategoryName?.setOnClickListener(listener)
        tvCategoryDescription?.setOnClickListener(listener)
        ivArrowRight?.setOnClickListener(listener)
    }
}