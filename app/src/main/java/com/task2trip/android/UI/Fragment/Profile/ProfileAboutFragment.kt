package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Presenter.UserProfilePresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.UserProfileView
import com.task2trip.widgetlibrary.LoadingAndMessage
import kotlinx.android.synthetic.main.fragment_profile_about.*
import com.task2trip.widgetlibrary.MessageFinishShowCallback

class ProfileAboutFragment : BaseFragment(), UserProfileView {
    private var profile: ProfileImpl = MockData.getEmptyProfile()
    private var isLevelUp = false
    private lateinit var presenter: UserProfilePresenter

    override fun getArgs(args: Bundle?) {
        args?.let {
            profile = args.getParcelable(Constants.EXTRA_PROFILE) ?: MockData.getEmptyProfile()
            isLevelUp = args.getBoolean(Constants.EXTRA_USER_LEVEL_UP, false)
        }
    }

    override fun setResourceLayout(): Int {
        return com.task2trip.android.R.layout.fragment_profile_about
    }

    override fun initComponents(view: View) {
        presenter = UserProfilePresenter(this, view.context)
        viewLoadAndMessage.setMessageCloseCallback(object : MessageFinishShowCallback {
            override fun onCloseMessage() {
                if (isLevelUp) {
                    val args = Bundle()
                    args.putParcelable(Constants.EXTRA_PROFILE, profile)
                    args.putBoolean(Constants.EXTRA_USER_LEVEL_UP, isLevelUp)
                    navigateTo(R.id.profileContactsFragment, args)
                } else {
                    navigateTo(R.id.profileFragment, Bundle())
                }
            }
        })
        initProfileData(profile)
        if (isLevelUp) {
            groupStepLevelUp.visibility = View.VISIBLE
            btNext.text = getString(R.string.next_step)
        } else {
            groupStepLevelUp.visibility = View.GONE
            btNext.text = getString(R.string.save)
        }
        btNext.setOnClickListener {
            saveProfileAboutInfo()
        }
    }

    private fun initProfileData(profile: ProfileImpl) {
        etWorkAndActivity.setText(profile.getFieldOfActivity())
        etInterest.setText(profile.getInterests())
        etAboutMe.setText(profile.getAbout())
        etWhyUse.setText(profile.getWhyUse())
    }

    private fun saveProfileAboutInfo() {
        profile.setFieldOfActivity(etWorkAndActivity.text.toString())
        profile.setInterests(etInterest.text.toString())
        profile.setAbout(etAboutMe.text.toString())
        profile.setWhyUse(etWhyUse.text.toString())
        presenter.updateUserProfile(profile)
    }

    override fun onUserProfileResult(profile: ProfileImpl) {
        setUserProfile(profile)
        viewLoadAndMessage.show()
        viewLoadAndMessage.setMessage("Профиль успешно обновлен", LoadingAndMessage.SHOW_SHORT)
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            viewLoadAndMessage.show()
        } else {
            viewLoadAndMessage.hide()
        }
        viewLoadAndMessage
    }
}