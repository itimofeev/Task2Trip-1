package com.task2trip.android.View

import com.task2trip.android.Model.User.UserImpl

interface UserProfileLevelUpView: BaseView {
    fun onUserLevelUpResult(user: UserImpl)
}