package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile_user.*

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
        if (user.getId().isNotEmpty()) {
            tvUserName.text = user.getName()
            tvAboutMe.text = user.getProfile().getAbout()
            tvUserNameLocation.text = user.getProfile().getLocation().name
            tvLikeCount.text = "0"
            tvDisLikeCount.text = "0"
            ratingBar.rating = 0f
            ratingBar.isEnabled = false
        }
    }
}