package com.task2trip.android.Presenter

import android.os.Bundle
import androidx.annotation.IdRes
import com.task2trip.android.UI.Activity.MainActivity

class MainActivityPresenter(private val view: MainActivity) : BasePresenter() {
    fun setNavigation(@IdRes resourceId: Int) {
        view.navigateTo(resourceId, Bundle())
    }
}