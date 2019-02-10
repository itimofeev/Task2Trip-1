package com.task2trip.android.View

import com.task2trip.android.Model.User.UserInfoResp
import com.task2trip.android.Model.User.UserLoginResp

interface UserView: BaseView {
    fun onRegisterResult(user: UserInfoResp)
    fun onLoginResult(userToken: UserLoginResp)
    fun onUserInfoResult(user: UserInfoResp)
}