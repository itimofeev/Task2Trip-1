package com.task2trip.android.Presenter

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import com.task2trip.android.UI.Activity.MainActivity

class MainActivityPresenter(private val view: MainActivity) : BasePresenter() {
    private var lastMenuIdRes: Int = 0

    fun setNavigation(@IdRes resourceId: Int) {
        view.navigateTo(resourceId, Bundle())
    }

    fun setLastMenu(@NonNull menu: Int) {
        this.lastMenuIdRes = menu
    }

    fun getLastMenu(): Int {
        return this.lastMenuIdRes
    }
}