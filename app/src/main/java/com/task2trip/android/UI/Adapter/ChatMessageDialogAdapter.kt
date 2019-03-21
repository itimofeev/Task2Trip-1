package com.task2trip.android.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.task2trip.android.Model.Chat.ChatMessage
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.ChatMessageDialogHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class ChatMessageDialogAdapter(items: List<ChatMessage>, private val userOwnerId: String):
    BaseListAdapter<ChatMessageDialogHolder, ChatMessage>(items) {

    private var listener: ItemClickListener<ChatMessage>? = null
    private val messageGuest = 0
    private val messageMy = 1

    override fun setLayoutRes(): Int {
        return R.layout.item_chat_message_guest
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageDialogHolder {
        val view = when(viewType) {
            messageGuest -> LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message_guest, parent, false)
            messageMy -> LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message_my, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(setLayoutRes(), parent, false)
        }
        return returnViewHolder(view)
    }

    override fun returnViewHolder(view: View): ChatMessageDialogHolder {
        return ChatMessageDialogHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val chat: ChatMessage = getItemByPosition(position)
        return if (chat.user.getId() == userOwnerId) {
            messageMy
        } else {
            messageGuest
        }
    }

    override fun onItemClicked(item: ChatMessage, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<ChatMessage>?) {
        this.listener = listener
    }
}
