package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.SocialNetwork
import com.task2trip.android.R

class SocialNetworkHolder(itemView: View) : BaseHolder<SocialNetwork>(itemView) {
    private var ivSocialLogo: ImageView? = null
    private var tvSocialName: TextView? = null
    private var tvUserName: TextView? = null

    init{
        ivSocialLogo = itemView.findViewById<ImageView>(R.id.ivSocialLogo)
        tvSocialName = itemView.findViewById<TextView>(R.id.tvSocialName)
        tvUserName = itemView.findViewById<TextView>(R.id.tvUserName)
    }

    override fun setData(item: SocialNetwork) {
        ivSocialLogo?.setImageResource(item.networkImage)
        tvSocialName?.text = item.networkName
        tvUserName?.text = item.userName
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
    }
}