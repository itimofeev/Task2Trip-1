package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.Chat.ChatMessage
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.ChatMessageDialogHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class ChatMessageDialogAdapter(items: List<ChatMessage>): BaseListAdapter<ChatMessageDialogHolder, ChatMessage>(items) {
    private var listener: ItemClickListener<ChatMessage>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_chat_message
    }

    override fun returnViewHolder(view: View): ChatMessageDialogHolder {
        return ChatMessageDialogHolder(view)
    }

    override fun onItemClicked(item: ChatMessage, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<ChatMessage>?) {
        this.listener = listener
    }
}
