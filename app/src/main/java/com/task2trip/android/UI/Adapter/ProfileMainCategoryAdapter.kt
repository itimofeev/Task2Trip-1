package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.UserCategoryForUsed
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.ProfileMainCategoryHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class ProfileMainCategoryAdapter(items: List<UserCategoryForUsed>) :
    BaseListAdapter<ProfileMainCategoryHolder, UserCategoryForUsed>(items) {

    private var listener: ItemClickListener<UserCategoryForUsed>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_profile_main_category
    }

    override fun returnViewHolder(view: View): ProfileMainCategoryHolder {
        return ProfileMainCategoryHolder(view)
    }

    override fun onItemClicked(item: UserCategoryForUsed, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<UserCategoryForUsed>?) {
        this.listener = listener
    }
}