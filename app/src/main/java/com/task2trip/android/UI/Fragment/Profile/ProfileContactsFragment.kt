package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.SocialNetwork
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Model.User.UserLevelUp
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.Presenter.UserProfileLevelUpPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.SocialNetworkAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.UserProfileLevelUpView
import com.task2trip.widgetlibrary.LoadingAndMessage
import com.task2trip.widgetlibrary.MessageFinishShowCallback
import kotlinx.android.synthetic.main.fragment_profile_contacts.*

class ProfileContactsFragment : BaseFragment(), UserProfileLevelUpView, ItemClickListener<SocialNetwork> {
    private lateinit var presenter: UserProfileLevelUpPresenter
    private var profile: ProfileImpl = MockData.getEmptyProfile()
    private var isLevelUp = false

    override fun getArgs(args: Bundle?) {
        args?.let {
            profile = args.getParcelable(Constants.EXTRA_PROFILE) ?: MockData.getEmptyProfile()
            isLevelUp = args.getBoolean(Constants.EXTRA_USER_LEVEL_UP, false)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile_contacts
    }

    override fun initComponents(view: View) {
        presenter = UserProfileLevelUpPresenter(this, view.context)
        viewLoadAndMessage.setMessageCloseCallback(object: MessageFinishShowCallback {
            override fun onCloseMessage() {
                if (isLevelUp) {
                    val args = Bundle()
                    args.putParcelable(Constants.EXTRA_PROFILE, profile)
                    args.putBoolean(Constants.EXTRA_USER_LEVEL_UP, isLevelUp)
                    navigateTo(R.id.profileFragment, args)
                } else {
                    navigateTo(R.id.profileFragment, Bundle())
                }
            }
        })
        if (isLevelUp) {
            groupStepLevelUp.visibility = View.VISIBLE
            btNext.text = getString(R.string.next_finish)
        } else {
            groupStepLevelUp.visibility = View.GONE
            btNext.text = getString(R.string.save)
        }
        initRecycleView(view)
        val country = etCountry.text.toString()
        val city = etCity.text.toString()
        btNext.setOnClickListener {
            presenter.setLevelUpUserProfile(UserLevelUp(UserRole.LOCAL.name.toLowerCase()))
        }
    }

    private fun initRecycleView(view: View) {
        rvSocialAccounts.setHasFixedSize(true)
        rvSocialAccounts.layoutManager = LinearLayoutManager(view.context)
        val items = ArrayList<SocialNetwork>()
        items.add(SocialNetwork("Вконтакте", R.drawable.vector_ic_vk, ""))
        items.add(SocialNetwork("Facebook", R.drawable.vector_ic_facebook, ""))
        val adapter = SocialNetworkAdapter(items)
        adapter.setClickListener(this)
        rvSocialAccounts.adapter = adapter
    }

    override fun onItemClick(item: SocialNetwork, position: Int) {
        //
    }

    override fun onUserLevelUpResult(user: UserImpl) {
        viewLoadAndMessage.show()
        if (user.getId().isNotEmpty()) {
            setUserRole(user.getRole().name)
            viewLoadAndMessage.setMessage("Профиль успешно обновлен", LoadingAndMessage.SHOW_SHORT)
        } else {
            viewLoadAndMessage.setMessage("Проверьте корректность данных", LoadingAndMessage.SHOW_MIDDLE)
        }
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