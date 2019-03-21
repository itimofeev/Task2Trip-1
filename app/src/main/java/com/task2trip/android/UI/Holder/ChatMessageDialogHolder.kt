package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.TextView
import com.task2trip.android.Common.toCalendar
import com.task2trip.android.Common.toPattern
import com.task2trip.android.Model.Chat.ChatMessage
import com.task2trip.android.R

class ChatMessageDialogHolder(itemView: View) : BaseHolder<ChatMessage>(itemView) {
    private var tvMessage: TextView? = null
    private var tvMessageDate: TextView? = null

    init {
        tvMessage = itemView.findViewById(R.id.tvMessage)
        tvMessageDate = itemView.findViewById(R.id.tvMessageDate)
    }

    override fun setData(item: ChatMessage) {
        tvMessage?.text = item.value
        tvMessageDate?.text = item.time.toCalendar().toPattern("HH:mm")
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        tvMessage?.setOnClickListener(listener)
        tvMessageDate?.setOnClickListener(listener)
    }
}