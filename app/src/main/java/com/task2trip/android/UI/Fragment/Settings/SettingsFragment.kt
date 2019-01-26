package com.task2trip.android.UI.Fragment.Settings

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.UserCategoryForUsed
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ProfileMainCategoryAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_settings
    }

    override fun initComponents(view: View) {
        initAboutUserCategoryList(view)
        btProfileExit.setOnClickListener { onProfileExitClick() }
    }

    private fun onProfileExitClick() {
        //
    }

    private fun initAboutUserCategoryList(@NonNull view: View) {
        val items = ArrayList<UserCategoryForUsed>()
        items.add(UserCategoryForUsed(R.id.profileCategoryFragment, "Черный список", emptyList()))
        items.add(UserCategoryForUsed(R.id.profileMainInfoFragment, "Изменить пароль", emptyList()))
        items.add(UserCategoryForUsed(R.id.profileContactsFragment, "Уведомления", emptyList()))
        items.add(UserCategoryForUsed(R.id.profileAboutFragment, "Задать вопрос", emptyList()))

        val adapter = ProfileMainCategoryAdapter(items)
        //adapter.setClickListener(this)
        rvSettingList.setHasFixedSize(true)
        rvSettingList.layoutManager = LinearLayoutManager(view.context)
        rvSettingList.adapter = adapter
    }
}
