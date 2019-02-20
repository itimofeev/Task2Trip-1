package com.task2trip.android.Model.User

import android.content.Context
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.LocalStoreManager

class UserImpl: User {
    private var id: String = ""
    private var login: String = ""
    private var name: String = ""
    private var email: String = ""
    private var imageUrl: String = ""
    private var role: String = ""
    private var token: String = ""

    override fun isAuthorized(): Boolean {
        return token.isNotEmpty()
    }

    override fun getId(): String {
        return id
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
        return UserRole.getName(role)
    }

    override fun getToken(): String {
        return token
    }

    override fun getImage(): String {
        return imageUrl
    }

    override fun setName(userName: String) {
        this.name = userName
    }

    override fun setEmail(email: String) {
        this.email = email
    }

    override fun setRole(role: UserRole) {
        this.role = role.name
    }

    override fun setToken(token: String) {
        this.token = token
    }

    override fun setImage(imageUrl: String) {
        this.imageUrl = imageUrl
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
                set(Constants.EXTRA_USER_ID, id)
                set(Constants.EXTRA_USER_LOGIN, login)
                set(Constants.EXTRA_USER_NAME, name)
                set(Constants.EXTRA_USER_EMAIL, email)
                set(Constants.EXTRA_USER_ROLE, role)
                set(Constants.EXTRA_USER_TOKEN, token)
                set(Constants.EXTRA_USER_IMAGE_URL, imageUrl)
            }
        } else {
            with(storage) {
                set(Constants.EXTRA_USER_ID, "")
                set(Constants.EXTRA_USER_LOGIN, "")
                set(Constants.EXTRA_USER_NAME, "")
                set(Constants.EXTRA_USER_EMAIL, "")
                set(Constants.EXTRA_USER_ROLE, UserRole.NOT_AUTHORIZED.name)
                set(Constants.EXTRA_USER_TOKEN, "")
                set(Constants.EXTRA_USER_IMAGE_URL, "")
            }
        }
    }

    override fun initStorageData(context: Context) {
        val storage = initStorage(context)
        with(storage) {
            id = get(Constants.EXTRA_USER_ID, "")!!
            login = get(Constants.EXTRA_USER_LOGIN, "")!!
            name = get(Constants.EXTRA_USER_NAME, "")!!
            email = get(Constants.EXTRA_USER_EMAIL, "")!!
            role = get(Constants.EXTRA_USER_ROLE, UserRole.NOT_AUTHORIZED.name)!!
            token = get(Constants.EXTRA_USER_TOKEN, "")!!
            imageUrl = get(Constants.EXTRA_USER_IMAGE_URL, "")!!
        }
    }
}