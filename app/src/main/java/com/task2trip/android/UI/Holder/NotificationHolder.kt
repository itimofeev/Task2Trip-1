package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.ImageLoader.ImageLoader
import com.task2trip.android.Model.Notification.NotificationData
import com.task2trip.android.R

class NotificationHolder(itemView: View) : BaseHolder<NotificationData>(itemView) {
    private var ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
    private var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    private var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    private var tvName: TextView = itemView.findViewById(R.id.tvName)

    override fun setData(item: NotificationData) {
        ImageLoader("", ivAvatar)
        tvDescription.text = item.title
        tvPrice.text = item.subtitle
        tvName.text = item.createTime
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        ivAvatar.setOnClickListener(listener)
        tvDescription.setOnClickListener(listener)
        tvPrice.setOnClickListener(listener)
        tvName.setOnClickListener(listener)
    }
}