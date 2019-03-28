package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.Location.GeoCountryCity
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.SocialNetwork
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Model.User.UserLevelUp
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.Presenter.SearchLocationPresenter
import com.task2trip.android.Presenter.UserProfileLevelUpPresenter
import com.task2trip.android.Presenter.UserProfilePresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.SocialNetworkAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.UI.Widget.SearchLocationFieldCallback
import com.task2trip.android.View.SearchLocationView
import com.task2trip.android.View.UserProfileLevelUpView
import com.task2trip.android.View.UserProfileView
import com.task2trip.widgetlibrary.LoadingAndMessage
import com.task2trip.widgetlibrary.MessageFinishShowCallback
import kotlinx.android.synthetic.main.fragment_profile_contacts.*

class ProfileContactsFragment : BaseFragment(), UserProfileLevelUpView, ItemClickListener<SocialNetwork>,
    MessageFinishShowCallback, SearchLocationView, SearchLocationFieldCallback, UserProfileView {

    private lateinit var presenterUserLevel: UserProfileLevelUpPresenter
    private lateinit var presenterUserProfile: UserProfilePresenter
    private lateinit var presenterSearch: SearchLocationPresenter
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
        setToolbar(toolbar)
        initPresenters(view)
        initSearchLocation()
        viewLoadAndMessage.setMessageCloseCallback(this)
        initButtonsByUserLevel()
        initRecycleView(view)
        btNext.setOnClickListener {
            searchLocationView?.let {
                val location: GeoCountryCity = it.getItem()
                if (!location.isEmpty()) {
                    profile.setLocation(location)
                }
            }
            presenterUserProfile.updateUserProfile(profile)
            presenterUserLevel.setLevelUpUserProfile(UserLevelUp(UserRole.LOCAL.name.toLowerCase()))

        }
    }

    private fun initPresenters(view: View) {
        presenterUserLevel = UserProfileLevelUpPresenter(this, view.context)
        presenterSearch = SearchLocationPresenter(this, view.context)
        presenterUserProfile = UserProfilePresenter(this, view.context)
    }

    private fun initSearchLocation() {
        searchLocationView.setHint(getString(R.string.profile_country_title))
        searchLocationView.setSearchLocationFieldCallback(this)
    }

    private fun initButtonsByUserLevel() {
        if (isLevelUp) {
            groupStepLevelUp.visibility = View.VISIBLE
            btNext.text = getString(R.string.next_finish)
        } else {
            groupStepLevelUp.visibility = View.GONE
            btNext.text = getString(R.string.save)
        }
    }

    private fun getInitAdapter(): SocialNetworkAdapter {
        val items = ArrayList<SocialNetwork>()
        items.add(SocialNetwork("Вконтакте", R.drawable.vector_ic_vk, ""))
        items.add(SocialNetwork("Facebook", R.drawable.vector_ic_facebook, ""))
        val adapter = SocialNetworkAdapter(items)
        adapter.setClickListener(this)
        return adapter
    }

    private fun initRecycleView(view: View) {
        rvSocialAccounts.setHasFixedSize(true)
        rvSocialAccounts.layoutManager = LinearLayoutManager(view.context)
        rvSocialAccounts.adapter = getInitAdapter()
    }

    override fun onItemClick(item: SocialNetwork, position: Int) {
        //
    }

    override fun onUserLevelUpResult(user: UserImpl) {
        viewLoadAndMessage?.show()
        if (user.getId().isNotEmpty()) {
            setUserRole(user.getRole().name)
            viewLoadAndMessage?.setMessage("Профиль успешно обновлен", LoadingAndMessage.SHOW_SHORT)
        } else {
            viewLoadAndMessage?.setMessage("Проверьте корректность данных", LoadingAndMessage.SHOW_MIDDLE)
        }
    }

    override fun onUserProfileUpdateResult(profile: ProfileImpl) {
        setUserProfile(profile)
        viewLoadAndMessage?.let {
            it.show()
            it.setMessage("Профиль успешно обновлен", LoadingAndMessage.SHOW_SHORT)
        }
    }

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

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            viewLoadAndMessage?.show()
        } else {
            viewLoadAndMessage?.hide()
        }
        viewLoadAndMessage?.setProgress(isProgress)
    }

    override fun onSearchLocationResult(items: List<GeoCountryCity>) {
        searchLocationView?.setDataForSearch(items)
    }

    override fun onTextLocationChanged(text: String) {
        // Ищем, если хотя бы введено searchLocationView.getMinimumTextSearchSize() символов
        if (text.length >= searchLocationView?.getMinimumTextSearchSize() ?: 0) {
            presenterSearch.getCountryAndCityByText(text)
        }
    }

    override fun onItemLocationChanged(item: GeoCountryCity) {
        etCity?.setText(item.description)
    }

    override fun onLocationClick() {
        //
    }
}