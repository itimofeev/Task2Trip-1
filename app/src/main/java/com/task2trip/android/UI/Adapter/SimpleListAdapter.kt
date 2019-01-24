package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.UserCategoryForUsed
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.SimpleViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class SimpleListAdapter(items: List<UserCategoryForUsed>): BaseListAdapter<SimpleViewHolder, UserCategoryForUsed>(items) {
    override fun setLayoutRes(): Int {
        return R.layout.fragment_login
    }

    override fun returnViewHolder(view: View): SimpleViewHolder {
        return SimpleViewHolder(view)
    }
}