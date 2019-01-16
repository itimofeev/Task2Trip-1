package com.task2trip.android.View

import com.task2trip.android.Model.UserInfoResp
import com.task2trip.android.Model.UserLoginResp

interface UserView: BaseView {
    fun onRegisterResult(user: UserInfoResp)
    fun onLoginResult(userToken: UserLoginResp)
    fun onUserInfoResult(user: UserInfoResp)
}