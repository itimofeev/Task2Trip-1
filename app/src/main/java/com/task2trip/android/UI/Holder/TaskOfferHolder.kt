package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.ImageLoader.ImageLoader
import com.task2trip.android.Model.Offer
import com.task2trip.android.R

class TaskOfferHolder(itemView: View) : BaseHolder<Offer>(itemView) {
    private var ivUserImage: ImageView? = null
    private var tvUserOfferDescription: TextView? = null
    private var tvUserOfferPrice: TextView? = null
    private var tvUserPerformerName: TextView? = null

    init {
        ivUserImage = itemView.findViewById<ImageView>(R.id.ivUserImage)
        tvUserOfferDescription = itemView.findViewById<TextView>(R.id.tvUserOfferDescription)
        tvUserOfferPrice = itemView.findViewById<TextView>(R.id.tvUserOfferPrice)
        tvUserPerformerName = itemView.findViewById<TextView>(R.id.tvUserPerformerName)
    }

    override fun setData(item: Offer) {
        ivUserImage?.let {ImageLoader("image_url", it)}
        tvUserOfferDescription?.text = item.comment
        tvUserOfferPrice?.text = item.price.toString()
        tvUserPerformerName?.text = item.user.getName()
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        ivUserImage?.setOnClickListener(listener)
        tvUserOfferDescription?.setOnClickListener(listener)
        tvUserOfferPrice?.setOnClickListener(listener)
        tvUserPerformerName?.setOnClickListener(listener)
    }
}