package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.SocialNetwork
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.SocialNetworkHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class SocialNetworkAdapter(items: List<SocialNetwork>) : BaseListAdapter<SocialNetworkHolder, SocialNetwork>(items) {
    private var listener: ItemClickListener<SocialNetwork>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_social
    }

    override fun returnViewHolder(view: View): SocialNetworkHolder {
        return SocialNetworkHolder(view)
    }

    override fun onItemClicked(item: SocialNetwork, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<SocialNetwork>?) {
        this.listener = listener
    }
}