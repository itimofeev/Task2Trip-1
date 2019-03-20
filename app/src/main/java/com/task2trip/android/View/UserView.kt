package com.task2trip.android.View

import com.task2trip.android.Model.User.UserImpl

interface UserView: BaseView {
    fun onMySelfInfoResult(user: UserImpl)
    fun onUserResult(user: UserImpl)
    fun onUploadImageAvatarResult(isSuccess: Boolean)
}