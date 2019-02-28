package com.task2trip.android.Model.User

data class UserLoginReq(val email: String, val password: String)
data class UserSignUpReq(val email: String,
                           val password: String,
                           val locale: String,
                           val name: String)
data class UserInfoResp(val id: String,
                        val name: String,
                        val role: String,
                        val imageUrl: String)
data class UserLoginResp(val authToken: String, val user: UserImpl)
data class UserSignUpResp(val key: String, val description: String)