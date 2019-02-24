package com.task2trip.android.View

import android.os.Bundle
import com.task2trip.android.Model.User.User

interface MainActivityView: BaseView {
    fun navigateTo(resourceId: Int, args: Bundle?)
    fun setUser(user: User)
    fun logoutUser()
}