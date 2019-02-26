package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.Offer
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.TaskOfferHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class TaskOfferListAdapter(items: List<Offer>): BaseListAdapter<TaskOfferHolder, Offer>(items) {
    private var listener: ItemClickListener<Offer>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_offer
    }

    override fun returnViewHolder(view: View): TaskOfferHolder {
        return TaskOfferHolder(view)
    }

    override fun onItemClicked(item: Offer, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<Offer>?) {
        this.listener = listener
    }
}