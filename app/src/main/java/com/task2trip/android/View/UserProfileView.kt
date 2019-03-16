package com.task2trip.android.View

import com.task2trip.android.Model.User.ProfileImpl

interface UserProfileView: BaseView {
    fun onUserProfileResult(profile: ProfileImpl)
}