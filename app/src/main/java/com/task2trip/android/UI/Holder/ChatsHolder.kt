package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Common.toCalendar
import com.task2trip.android.Common.toPattern
import com.task2trip.android.Model.Chat.Chat
import com.task2trip.android.Model.ImageLoader.ImageCropType
import com.task2trip.android.Model.ImageLoader.ImageLoader
import com.task2trip.android.R

class ChatsHolder(itemView: View) : BaseHolder<Chat>(itemView) {
    private var ivAvatar: ImageView? = null
    private var tvUserName: TextView? = null
    private var tvMessageDateTime: TextView? = null
    private var tvMessageLast: TextView? = null
    private var tvMessageCount: TextView? = null

    init {
        ivAvatar = itemView.findViewById(R.id.ivAvatar)
        tvUserName = itemView.findViewById(R.id.tvUserName)
        tvMessageDateTime = itemView.findViewById(R.id.tvMessageDateTime)
        tvMessageLast = itemView.findViewById(R.id.tvMessageLast)
        tvMessageCount = itemView.findViewById(R.id.tvMessageCount)
    }

    override fun setData(item: Chat) {
        ivAvatar?.let {
            ImageLoader(item.user.getProfile().getImageAvatarUrl(), it, ImageCropType.CROP_CIRCLE)
        }
        tvUserName?.text = item.user.getName()
        tvMessageDateTime?.text = item.lastMessage.time.toCalendar().toPattern("HH:mm")
        tvMessageLast?.text = item.lastMessage.value
        val unreadMsg = item.unreadCount
        if (unreadMsg > 0) {
            tvMessageCount?.text = unreadMsg.toString()
            tvMessageCount?.visibility = View.VISIBLE
        } else {
            tvMessageCount?.visibility = View.GONE
        }
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        ivAvatar?.setOnClickListener(listener)
        tvUserName?.setOnClickListener(listener)
        tvMessageDateTime?.setOnClickListener(listener)
        tvMessageLast?.setOnClickListener(listener)
        tvMessageCount?.setOnClickListener(listener)
    }
}
