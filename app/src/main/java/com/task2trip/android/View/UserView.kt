package com.task2trip.android.View

import com.task2trip.android.Model.User.UserImpl

interface UserView: BaseView {
    fun onUserInfoResult(user: UserImpl)
    fun onUploadImageAvatarResult()
}