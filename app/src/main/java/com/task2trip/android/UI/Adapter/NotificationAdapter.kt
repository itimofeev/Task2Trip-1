package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.Notification.NotificationData
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.NotificationHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class NotificationAdapter(items: List<NotificationData>) : BaseListAdapter<NotificationHolder, NotificationData>(items) {
    private var listener: ItemClickListener<NotificationData>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_notification
    }

    override fun returnViewHolder(view: View): NotificationHolder {
        return NotificationHolder(view)
    }

    override fun onItemClicked(item: NotificationData, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<NotificationData>?) {
        this.listener = listener
    }
}