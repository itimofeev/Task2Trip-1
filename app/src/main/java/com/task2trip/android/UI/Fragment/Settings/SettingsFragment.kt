package com.task2trip.android.UI.Fragment.Settings

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.User.UserSettingItem
import com.task2trip.android.Presenter.PushPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ProfileSettingAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.BaseView
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(), ItemClickListener<UserSettingItem>, BaseView {
    private lateinit var presenterPush: PushPresenter

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
        groupExitApp.setOnClickListener {view ->
            onProfileExitClick(view)
        }
        ivTypeExit.setOnClickListener { view ->
            onProfileExitClick(view)
        }
        tvTypeExit.setOnClickListener {view ->
            onProfileExitClick(view)
        }
    }

    private fun onAboutAppClick() {
        //
    }

    private fun onProfileExitClick(view: View) {
        presenterPush = PushPresenter(this, view.context)
        //presenterPush.unRegisterPushToken("132")//TODO: getToken from settings
        super.logoutUser()
    }

    private fun initAboutUserCategoryList(@NonNull view: View) {
        val items = ArrayList<UserSettingItem>()
        items.add(UserSettingItem(R.id.noContentFragment, getString(R.string.setting_black_list), R.drawable.vector_ic_user_block))
        items.add(UserSettingItem(R.id.noContentFragment, getString(R.string.setting_change_password), R.drawable.vector_ic_change_password))
        items.add(UserSettingItem(R.id.noContentFragment, getString(R.string.setting_notifications), R.drawable.vector_ic_notifications))
        items.add(UserSettingItem(R.id.noContentFragment, getString(R.string.setting_ask_question), R.drawable.vector_ic_questions_about))

        val adapter = ProfileSettingAdapter(items)
        adapter.setClickListener(this)
        rvSettingList?.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(view.context)
            it.adapter = adapter
        }
    }

    override fun onItemClick(item: UserSettingItem, position: Int) {
        navigateTo(item.id, Bundle())
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}
