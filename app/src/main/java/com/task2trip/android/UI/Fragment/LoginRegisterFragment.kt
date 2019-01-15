package com.task2trip.android.UI.Fragment

import android.os.Bundle
import android.view.View
import com.task2trip.android.R
import kotlinx.android.synthetic.main.fragment_login_register.*

class LoginRegisterFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_login_register
    }

    override fun initComponents(view: View) {
        btReg?.setOnClickListener {
            navigateTo(R.id.registrationFragment, Bundle())
        }
        btLogin?.setOnClickListener {
            navigateTo(R.id.loginFragment, Bundle())
        }
    }
}