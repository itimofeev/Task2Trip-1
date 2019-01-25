package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.UserCategory
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.ProfileCategoryHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class ProfileCategoryAdapter(items: List<UserCategory>) : BaseListAdapter<ProfileCategoryHolder, UserCategory>(items) {
    private var listener: ItemClickListener<UserCategory>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_profile_category
    }

    override fun returnViewHolder(view: View): ProfileCategoryHolder {
        return ProfileCategoryHolder(view)
    }

    override fun onItemClicked(item: UserCategory, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<UserCategory>?) {
        this.listener = listener
    }
}