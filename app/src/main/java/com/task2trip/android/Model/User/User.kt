package com.task2trip.android.Model.User

import android.content.Context

interface User {
    fun isAuthorized(): Boolean

    fun getId(): String
    fun getRole(): UserRole
    fun getToken(): String
    fun getProfile(): Profile
    fun getName(): String

    fun setRole(role: UserRole)
    fun setToken(token: String)
    fun setProfile(profile: ProfileImpl)

    fun saveUserData(context: Context)
    fun deleteUserData(context: Context)
    fun initStorageData(context: Context)
}