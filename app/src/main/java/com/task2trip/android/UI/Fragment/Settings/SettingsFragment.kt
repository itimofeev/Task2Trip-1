package com.task2trip.android.UI.Fragment.Settings

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.User.UserSettingItem
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ProfileSettingAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(), ItemClickListener<UserSettingItem> {

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_settings
    }

    override fun initComponents(view: View) {
        initAboutUserCategoryList(view)
        initSettingAbout()
        initSettingExit()
    }

    private fun initSettingAbout() {
        ivTypeAbout.setOnClickListener {
            onAboutAppClick()
        }
        tvTypeAbout.setOnClickListener {
            onAboutAppClick()
        }
        groupAboutApp.setOnClickListener {
            onAboutAppClick()
        }
    }

    private fun initSettingExit() {
        groupExitApp.setOnClickListener {
            onProfileExitClick()
        }
        ivTypeExit.setOnClickListener {
            onProfileExitClick()
        }
        tvTypeExit.setOnClickListener {
            onProfileExitClick()
        }
    }

    private fun onAboutAppClick() {
        //
    }

    private fun onProfileExitClick() {
        super.logoutUser()
    }

    private fun initAboutUserCategoryList(@NonNull view: View) {
        val items = ArrayList<UserSettingItem>()
        items.add(UserSettingItem(R.id.profileCategoryFragment, getString(R.string.setting_black_list), R.drawable.vector_ic_user_block))
        items.add(UserSettingItem(R.id.profileMainInfoFragment, getString(R.string.setting_change_password), R.drawable.vector_ic_change_password))
        items.add(UserSettingItem(R.id.profileContactsFragment, getString(R.string.setting_notifications), R.drawable.vector_ic_notifications))
        items.add(UserSettingItem(R.id.profileAboutFragment, getString(R.string.setting_ask_question), R.drawable.vector_ic_questions_about))

        val adapter = ProfileSettingAdapter(items)
        adapter.setClickListener(this)
        rvSettingList.setHasFixedSize(true)
        rvSettingList.layoutManager = LinearLayoutManager(view.context)
        rvSettingList.adapter = adapter
    }

    override fun onItemClick(item: UserSettingItem, position: Int) {
        navigateTo(item.id, Bundle())
    }
}
