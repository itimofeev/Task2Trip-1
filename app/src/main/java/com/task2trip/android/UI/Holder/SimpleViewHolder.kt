package com.task2trip.android.UI.Holder

import android.view.View
import com.task2trip.android.Model.UserCategoryForUsed

class SimpleViewHolder(itemView: View): BaseHolder(itemView) {
    init {
        //
    }

    override fun setData(item: Any) {
        val data: UserCategoryForUsed = item as UserCategoryForUsed
    }
}