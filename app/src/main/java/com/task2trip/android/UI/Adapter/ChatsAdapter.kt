package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.R

class ChatsAdapter(items: List<Chat>):
    BaseListAdapter<ChatsHolder, Chat>(items) {
    private var listener: ItemClickListener<Chat>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_task_add_category
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
