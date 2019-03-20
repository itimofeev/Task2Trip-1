package com.task2trip.android.UI.Adapter

import android.view.View
import com.task2trip.android.Model.User.UserSettingItem
import com.task2trip.android.R
import com.task2trip.android.UI.Holder.ProfileSettingHolder
import com.task2trip.android.UI.Listener.ItemClickListener

class ProfileSettingAdapter(items: ArrayList<UserSettingItem>): BaseListAdapter<ProfileSettingHolder, UserSettingItem>(items) {
    private var listener: ItemClickListener<UserSettingItem>? = null

    override fun setLayoutRes(): Int {
        return R.layout.item_profile_setting
    }

    override fun returnViewHolder(view: View): ProfileSettingHolder {
        return ProfileSettingHolder(view)
    }

    override fun onItemClicked(item: UserSettingItem, position: Int) {
        this.listener?.onItemClick(item, position)
    }

    override fun setClickListener(listener: ItemClickListener<UserSettingItem>?) {
        this.listener = listener
    }
}