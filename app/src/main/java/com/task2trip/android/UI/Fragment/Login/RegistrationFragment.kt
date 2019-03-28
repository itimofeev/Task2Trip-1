package com.task2trip.android.UI.Fragment.Login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.User.UserLoginResp
import com.task2trip.android.Presenter.UserAuthPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Dialog.SimpleFragmentDialog
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.UserAuthView
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : BaseFragment(), UserAuthView {
    private lateinit var presenter: UserAuthPresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_registration
    }

    override fun initComponents(view: View) {
        setToolbar(toolbar)
        presenter = UserAuthPresenter(this, view.context)
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
        if (name.isEmpty() || name.length < Constants.MIN_NAME_LENGTH) {
            onMessage("Имя должно быть более ${Constants.MIN_NAME_LENGTH} символов!")
            return false
        }
        return true
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onRegisterResult(user: Void?) {
        // Если ответ пустой, то все ОК. Нужно сообщение, что на почту отправлен пароль
        val dialog = SimpleFragmentDialog.getInstance("Регистрация",
            "Вы успешно зарегистрированы!\nНа указанную почту пришло письмо с подтверждением регистрации. Перейдите по ссылке и войдите в приложение.",
            "Ок",
            "")
        val fManager = fragmentManager
        if (fManager != null) {
            dialog.setTargetFragment(this, Constants.REQUEST_DIALOG_SIMPLE)
            dialog.show(fManager, SimpleFragmentDialog.TAG)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_DIALOG_SIMPLE) {
            if (resultCode == Activity.RESULT_OK) {
                navigateTo(R.id.loginFragment, Bundle())
            }
        }
    }

    override fun onLoginResult(userToken: UserLoginResp) {
        //
    }
}