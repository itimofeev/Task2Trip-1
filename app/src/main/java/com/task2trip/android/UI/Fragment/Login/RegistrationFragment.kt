package com.task2trip.android.UI.Fragment.Login

import android.os.Bundle
import android.view.View
import com.task2trip.android.Model.User.UserInfoResp
import com.task2trip.android.Model.User.UserLoginResp
import com.task2trip.android.Presenter.UserPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.UserView
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : BaseFragment(), UserView {
    private lateinit var presenter: UserPresenter
    private val minEmailLength = 8
    private val minPasswordLength = 5
    private val minNameLength = 3

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_registration
    }

    override fun initComponents(view: View) {
        presenter = UserPresenter(this, view.context)
        btReg.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val name = etName.text.toString().trim()
            if (validateData(email, password, name)) {
                presenter.registerUser(email, password, name, getDefaultLocale())
            }
        }
    }

    private fun validateData(email: String, password: String, name: String): Boolean {
        if (email.isEmpty() || email.length < minEmailLength) {
            onMessage("Почта должна быть более $minEmailLength символов!")
            return false
        }
        if (password.isEmpty() || password.length < minPasswordLength) {
            onMessage("Пароль должен быть более $minPasswordLength символов!")
            tvPassword.error = "Пароль должен быть более $minPasswordLength символов!"
            tvPassword.isErrorEnabled = true
            return false
        } else {
            tvPassword.error = null
            tvPassword.isErrorEnabled = false
        }
        if (name.isEmpty() || name.length < minNameLength) {
            onMessage("Имя должно быть более $minNameLength символов!")
            return false
        }
        return true
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onRegisterResult(user: Void?) {
        // Если ответ пустой, то все ОК. Нужно сообщение, что на почту отправлен пароль
        navigateTo(R.id.loginFragment, Bundle())
    }

    override fun onLoginResult(userToken: UserLoginResp) {
        //
    }

    override fun onUserInfoResult(user: UserInfoResp) {
        //
    }
}