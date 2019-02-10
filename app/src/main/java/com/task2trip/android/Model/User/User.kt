package com.task2trip.android.Model.User

import android.content.Context

interface User {
    fun isAuthorized(): Boolean
    fun getLogin(): String
    fun getName(): String
    fun getEmail(): String
    fun getRole(): UserRole
    fun getToken(): String

    fun setName(userName: String)
    fun setEmail(email: String)
    fun setRole(role: UserRole)
    fun setToken(token: String)

    fun saveUserData(context: Context)
    fun deleteUserData(context: Context)
    fun initStorageData(context: Context)
}