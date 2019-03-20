package com.task2trip.android.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.task2trip.android.Model.ImageLoader.ImageLoader
import com.task2trip.android.R

class ChatsHolder(itemView: View) : BaseHolder<Chat>(itemView) {
    private var ivCategoryImage: ImageView? = null
    private var tvCategoryName: TextView? = null
    private var tvLasMessage: TextView? = null

    init {
        ivCategoryImage = itemView.findViewById(R.id.ivCategoryImage)
        tvCategoryName = itemView.findViewById(R.id.tvCategoryName)
        tvLasMessage = itemView.findViewById(R.id.tvLasMessage)
    }

    override fun setData(item: Chat) {
        ivCategoryImage?.let {
            ImageLoader(item.imageUrl, it)
        }
        tvCategoryName?.text = item.defaultValue
        tvLasMessage?.text = item.defaultValue
    }

    override fun setItemClickListener(listener: View.OnClickListener?) {
        itemView.setOnClickListener(listener)
        tvCategoryName?.setOnClickListener(listener)
        tvLasMessage?.setOnClickListener(listener)
    }
}
