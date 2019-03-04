package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment

class ProfileUserFragment : BaseFragment() {
    private var user: UserImpl = MockData.getEmptyUser()

    override fun getArgs(args: Bundle?) {
        args?.let {
            user = it.getParcelable(Constants.EXTRA_USER) ?: MockData.getEmptyUser()
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile_user
    }

    override fun initComponents(view: View) {
        //
    }
}