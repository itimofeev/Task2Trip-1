package com.task2trip.android.View

import com.task2trip.android.Model.User.UserLoginResp

interface UserAuthView: BaseView {
    fun onRegisterResult(user: Void?)
    fun onLoginResult(userToken: UserLoginResp)
}