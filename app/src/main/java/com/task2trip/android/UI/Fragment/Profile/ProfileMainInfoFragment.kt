package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Model.User.UserSex
import com.task2trip.android.Presenter.UserProfilePresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.View.UserProfileView
import com.task2trip.widgetlibrary.LoadingAndMessage
import com.task2trip.widgetlibrary.MessageFinishShowCallback
import kotlinx.android.synthetic.main.fragment_profile_main_info.*

class ProfileMainInfoFragment : BaseFragment(), UserProfileView {
    private lateinit var presenter: UserProfilePresenter
    private var profile: ProfileImpl = MockData.getEmptyProfile()
    private var isLevelUp = false

    override fun getArgs(args: Bundle?) {
        args?.let {
            profile = args.getParcelable(Constants.EXTRA_PROFILE) ?: MockData.getEmptyProfile()
            isLevelUp = args.getBoolean(Constants.EXTRA_USER_LEVEL_UP, false)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile_main_info
    }

    override fun initComponents(view: View) {
        presenter = UserProfilePresenter(this, view.context)
        viewLoadAndMessage.hide()
        viewLoadAndMessage.setMessageCloseCallback(object: MessageFinishShowCallback {
            override fun onCloseMessage() {
                if (!isLevelUp) {
                    navigateTo(R.id.profileFragment, Bundle())
                }
            }
        })
        initProfileData(profile)
        btSaveMainInfo.setOnClickListener {
            if (validateData(etName.text.toString(), etBirthday.text.toString())) {
                profile.setFirstName(etName.text.toString())
                profile.setBirthDate(etBirthday.text.toString())
                if (rgGender.checkedRadioButtonId == R.id.rbMale) {
                    profile.setSex(UserSex.getUserSex(true).name)
                } else if (rgGender.checkedRadioButtonId == R.id.rbFemale) {
                    profile.setSex(UserSex.getUserSex(false).name)
                }
                presenter.updateUserProfile(profile)
            }
        }
    }

    private fun initProfileData(profile: ProfileImpl) {
        etName.setText(profile.getFirstName())
        etBirthday.setText(profile.getBirthDate())
        if (profile.getSex() == UserSex.male.name) {
            rgGender.check(R.id.rbMale)
        } else if (profile.getSex() == UserSex.female.name) {
            rgGender.check(R.id.rbFemale)
        }
    }

    /**
     * Если что-то ввели, то проверяем
     */
    private fun validateData(firstName: String, birthDate: String): Boolean {
        return true
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
        viewLoadAndMessage.setProgress(isProgress)
    }
}