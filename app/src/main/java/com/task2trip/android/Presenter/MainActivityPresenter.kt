package com.task2trip.android.Presenter

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.task2trip.android.Model.Notification.UserDeviceToken
import com.task2trip.android.UI.Activity.MainActivity
import retrofit2.Call

class MainActivityPresenter(private val view: MainActivity, context: Context) : BasePresenter(view, context) {
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