package com.task2trip.android.UI.Fragment.Login

import android.os.Bundle
import android.view.View
import com.task2trip.android.Model.LocalStoreManager
import com.task2trip.android.Model.User.*
import com.task2trip.android.Presenter.UserPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
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
            super.hideKeyboard()
            presenter.userLogin(etEmail.text.toString().trim(), etPassword.text.toString())
        }
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }

    override fun onRegisterResult(user: Void?) {
        //
    }

    override fun onLoginResult(userToken: UserLoginResp) {
        if (userToken.authToken.isNotEmpty()) {
            val user: User = userToken.user
            user.setToken(userToken.authToken)
            context?.let {
                user.saveUserData(it)
            }
            super.setUser(user)
            navigateTo(R.id.taskListPerformerFragment, Bundle())
        }
    }

    override fun onUserInfoResult(user: UserInfoResp) {
        //
    }
}
