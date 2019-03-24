package com.task2trip.android.UI.Fragment.Login

import android.os.Bundle
import android.view.View
import com.google.firebase.iid.FirebaseInstanceId
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.LocalStoreManager
import com.task2trip.android.Model.User.*
import com.task2trip.android.Presenter.PushPresenter
import com.task2trip.android.Presenter.UserAuthPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.UserAuthView
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(), UserAuthView {
    private lateinit var presenter: UserAuthPresenter
    private lateinit var presenterPush: PushPresenter
    private lateinit var localStoreManager: LocalStoreManager

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_login
    }

    override fun initComponents(view: View) {
        presenter = UserAuthPresenter(this, view.context)
        presenterPush = PushPresenter(this, view.context)
        localStoreManager = LocalStoreManager(view.context)
        btLogin.setOnClickListener {
            super.hideKeyboard()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()
            if (validateData(email, password)) {
                presenter.userLogin(email, password)
            }
        }
    }

    private fun validateData(email: String, password: String): Boolean {
        if (email.isEmpty() || email.length < Constants.MIN_EMAIL_LENGTH) {
            onMessage("Почта должна быть более ${Constants.MIN_EMAIL_LENGTH} символов!")
            return false
        }
        if (password.isEmpty() || password.length < Constants.MIN_PASSWORD_LENGTH) {
            onMessage("Пароль должен быть более ${Constants.MIN_PASSWORD_LENGTH} символов!")
            tvPassword.error = "Пароль должен быть более ${Constants.MIN_PASSWORD_LENGTH} символов!"
            tvPassword.isErrorEnabled = true
            return false
        } else {
            tvPassword.error = null
            tvPassword.isErrorEnabled = false
        }
        return true
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onRegisterResult(user: Void?) {
        //
    }

    override fun onLoginResult(userToken: UserLoginResp) {
        if (userToken.authToken.isNotEmpty()) {
            presenterPush.registerPushToken()
            val user: User = userToken.user
            user.setToken(userToken.authToken)
            context?.let {
                user.saveUserData(it)
            }
            super.setUser(user)
            navigateTo(R.id.taskListPerformerFragment, Bundle())
        }
    }
}
