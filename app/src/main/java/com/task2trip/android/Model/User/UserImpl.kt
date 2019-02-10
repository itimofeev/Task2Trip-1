package com.task2trip.android.Model.User

import android.content.Context
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.LocalStoreManager

class UserImpl: User {
    private var login: String = ""
    private var name: String = ""
    private var email: String = ""
    private var role: UserRole = UserRole.NOT_AUTHORIZED
    private var token: String = ""

    override fun isAuthorized(): Boolean {
        return token.isNotEmpty()
    }

    override fun getLogin(): String {
        return login
    }

    override fun getName(): String {
        return name
    }

    override fun getEmail(): String {
        return email
    }

    override fun getRole(): UserRole {
        return role
    }

    override fun getToken(): String {
        return token
    }

    override fun setName(userName: String) {
        this.name = userName
    }

    override fun setEmail(email: String) {
        this.email = email
    }

    override fun setRole(role: UserRole) {
        this.role = role
    }

    override fun setToken(token: String) {
        this.token = token
    }

    override fun saveUserData(context: Context) {
        userData(true, context)
    }

    override fun deleteUserData(context: Context) {
        userData(false, context)
    }

    private fun initStorage(context: Context): LocalStoreManager {
        return LocalStoreManager(context)
    }

    private fun userData(isSave: Boolean, context: Context) {
        val storage = initStorage(context)
        if (isSave) {
            with(storage) {
                set(Constants.EXTRA_USER_LOGIN, login)
                set(Constants.EXTRA_USER_NAME, name)
                set(Constants.EXTRA_USER_EMAIL, email)
                set(Constants.EXTRA_USER_ROLE, role.name)
                set(Constants.EXTRA_USER_TOKEN, token)
            }
        } else {
            with(storage) {
                set(Constants.EXTRA_USER_LOGIN, "")
                set(Constants.EXTRA_USER_NAME, "")
                set(Constants.EXTRA_USER_EMAIL, "")
                set(Constants.EXTRA_USER_ROLE, UserRole.NOT_AUTHORIZED.name)
                set(Constants.EXTRA_USER_TOKEN, "")
            }
        }
    }

    override fun initStorageData(context: Context) {
        val storage = initStorage(context)
        login = storage.get(Constants.EXTRA_USER_LOGIN, "")!!
        name = storage.get(Constants.EXTRA_USER_NAME, "")!!
        email = storage.get(Constants.EXTRA_USER_EMAIL, "")!!
        role = UserRole.valueOf(storage.get(Constants.EXTRA_USER_ROLE, UserRole.NOT_AUTHORIZED.name)!!)
        token = storage.get(Constants.EXTRA_USER_TOKEN, "")!!
    }
}