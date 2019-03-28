package com.task2trip.android.UI.Fragment.Settings

import android.os.Bundle
import android.view.View
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_settings_notification.*

class SettingsNotificationFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_settings_notification
    }

    override fun initComponents(view: View) {
        setToolbar(toolbar)
    }
}
