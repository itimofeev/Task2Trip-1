package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.User.UserSettingItem
import com.task2trip.android.R

class ProfileSettingHolder(itemView: View): BaseHolder<UserSettingItem>(itemView) {
    private var ivSettingIcon: ImageView? = null
    private var tvSettingName: TextView? = null

    init {
        ivSettingIcon = itemView.findViewById<ImageView>(R.id.ivSettingType)
        tvSettingName = itemView.findViewById<TextView>(R.id.tvSettingType)
    }

    override fun setData(item: UserSettingItem) {
        ivSettingIcon?.setImageResource(item.icon)
        tvSettingName?.text = item.settingName
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        ivSettingIcon?.setOnClickListener(listener)
        tvSettingName?.setOnClickListener(listener)
    }
}