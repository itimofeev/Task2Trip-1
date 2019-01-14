package com.task2trip.android.Model

import android.content.Context
import android.content.SharedPreferences

class Settings(context: Context) {
    private val settingFile: String = "task2trip"
    private val KEY_LOGIN: String = "login"
    private val KEY_TOKEN: String = "token"
    private val pref: SharedPreferences = context.getSharedPreferences(settingFile, Context.MODE_PRIVATE)

    fun saveAuthData(login: String, token: String) {
        with(pref.edit()) {
            putString(KEY_LOGIN, login)
            putString(KEY_TOKEN, token)
            apply()
        }
    }

    fun getLogin(): String? {
        return pref.getString(KEY_LOGIN, "")
    }

    fun getToken(): String? {
        return pref.getString(KEY_TOKEN, "")
    }

    fun logout() {
        saveAuthData("", "")
    }
}