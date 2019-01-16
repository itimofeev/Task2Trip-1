package com.task2trip.android.Model

data class UserDataReq(val email: String, val password: String)
data class UserInfoResp(val id: String, val name: String)
data class UserLoginResp(val authToken: String)