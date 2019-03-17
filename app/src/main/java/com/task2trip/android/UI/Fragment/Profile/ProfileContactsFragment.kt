package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.SocialNetwork
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Presenter.UserProfileLevelUpPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.SocialNetworkAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.UserProfileLevelUpView
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
        if (isLevelUp) {
            groupStepLevelUp.visibility = View.VISIBLE
            btNext.text = getString(R.string.save)
        } else {
            groupStepLevelUp.visibility = View.GONE
            btNext.text = getString(R.string.next_finish)
        }
        initRecycleView(view)
        val country = etCountry.text.toString()
        val city = etCity.text.toString()
        btNext.setOnClickListener {
            presenter.setLevelUpUserProfile()
            //navigateTo(R.id.profileFragment)
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
        //
    }

    override fun onProgress(isProgress: Boolean) {
        //
    }
}