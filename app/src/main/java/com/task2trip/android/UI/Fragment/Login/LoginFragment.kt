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
            //presenter.userLogin(etEmail.text.toString(), etPassword.text.toString())
            val login = etEmail.text.toString()
            when {
                login.equals("TRAVELER", true) -> onLoginResult(UserLoginResp(1, "GFVJHBKL98yu87huyg6"))
                login.equals("PERFORMER", true) -> onLoginResult(UserLoginResp(2, "GFVJHBKL98yu87huyg6"))
                else -> onLoginResult(UserLoginResp(0, "GFVJHBKL98yu87huyg6"))
            }
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
            val user: User = UserImpl()
            with(user) {
                setName("Test user")
                setEmail("test@email.com")
                when {
                    userToken.id == 1L -> setRole(UserRole.TRAVELER)
                    userToken.id == 2L -> setRole(UserRole.PERFORMER)
                    else -> setRole(UserRole.NOT_AUTHORIZED)
                }
                setToken(userToken.authToken)
                context?.let { saveUserData(it) }
            }
            super.setUser(user)
            navigateTo(R.id.taskGetMyListFragment, Bundle())
        }
    }

    override fun onUserInfoResult(user: UserInfoResp) {
        //
    }
}
