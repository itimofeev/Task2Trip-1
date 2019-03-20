package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.Chat.Chat
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.ChatsHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class ChatsAdapter(items: List<Chat>): BaseListAdapter<ChatsHolder, Chat>(items) {
    private var listener: ItemClickListener<Chat>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_chat
    }

    override fun returnViewHolder(view: View): ChatsHolder {
        return ChatsHolder(view)
    }

    override fun onItemClicked(item: Chat, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<Chat>?) {
        this.listener = listener
    }
}