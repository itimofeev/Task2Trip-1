package com.task2trip.android.UI.Fragment

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.LocalStoreManager
import com.task2trip.android.Model.UserInfoResp
import com.task2trip.android.Model.UserLoginResp
import com.task2trip.android.Presenter.UserPresenter
import com.task2trip.android.R
import com.task2trip.android.View.UserView
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(), UserView {
    private lateinit var presenter: UserPresenter
    private lateinit var localStoreManager: LocalStoreManager

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_login
    }

    override fun initComponents(view: View) {
        presenter = UserPresenter(this, view.context)
        localStoreManager = LocalStoreManager(view.context)
        btLogin.setOnClickListener {
            presenter.userLogin(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onRegisterResult(user: UserInfoResp) {
        //
    }

    override fun onLoginResult(userToken: UserLoginResp) {
        if (userToken.authToken.isNotEmpty()) {
            localStoreManager.set(Constants.EXTRA_USER_TOKEN, userToken.authToken)
            navigateTo(R.id.taskInfoFragment, Bundle())
        }
    }

    override fun onUserInfoResult(user: UserInfoResp) {
        //
    }
}
