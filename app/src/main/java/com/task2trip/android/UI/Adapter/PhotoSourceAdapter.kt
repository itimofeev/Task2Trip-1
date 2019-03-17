package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.PhotoSourceHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class PhotoSourceAdapter(items: List<String>) : BaseListAdapter<PhotoSourceHolder, String>(items) {
    private var listener: ItemClickListener<String>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_photo_source
    }

    override fun returnViewHolder(view: View): PhotoSourceHolder {
        return PhotoSourceHolder(view)
    }

    override fun onItemClicked(item: String, position: Int) {
        listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<String>?) {
        this.listener = listener
    }
}