package com.task2trip.android.UI.Fragment.Login

import android.os.Bundle
import android.view.View
import com.task2trip.android.Model.UserInfoResp
import com.task2trip.android.Model.UserLoginResp
import com.task2trip.android.Presenter.UserPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.UserView
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : BaseFragment(), UserView {
    private lateinit var presenter: UserPresenter

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_registration
    }

    override fun initComponents(view: View) {
        presenter = UserPresenter(this, view.context)
        btReg.setOnClickListener {
            presenter.registerUser(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onRegisterResult(user: UserInfoResp) {
        if (!user.id.isEmpty()) {
            navigateTo(R.id.loginFragment, Bundle())
        }
    }

    override fun onLoginResult(userToken: UserLoginResp) {
        //
    }

    override fun onUserInfoResult(user: UserInfoResp) {
        //
    }
}