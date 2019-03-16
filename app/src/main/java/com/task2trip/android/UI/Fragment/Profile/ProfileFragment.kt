package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Model.User.UserCategoryForUsed
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Presenter.UserPresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ProfileMainCategoryAdapter
import com.task2trip.android.UI.Dialog.SimpleFragmentDialog
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.UserView
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment(), UserView, ItemClickListener<UserCategoryForUsed> {
    private lateinit var presenter: UserPresenter
    private var user: UserImpl = MockData.getEmptyUser()

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        tvProfilePhoto.setOnClickListener {
            onChangePhotoClick()
        }
        ivProfilePhoto.setOnClickListener {
            onChangePhotoClick()
        }
        btShowMyTasks.setOnClickListener {
            onShowMyTasksClick()
        }
        groupLevelUp.setOnClickListener {
            onUserLevelUp()
        }
        tvExecuteTasks.setOnClickListener {
            onUserLevelUp()
        }
        tvTaskExecuteDescription.setOnClickListener {
            onUserLevelUp()
        }
        initAboutUserCategoryList(view)
    }

    private fun initPresenter(view: View) {
        presenter = UserPresenter(this, view.context)
        presenter.getUserInfo()
    }

    private fun initAboutUserCategoryList(view: View) {
        val items = ArrayList<UserCategoryForUsed>()
        items.add(UserCategoryForUsed(R.id.profileMainInfoFragment, getString(R.string.profile_main), ""))
        items.add(UserCategoryForUsed(R.id.profileContactsFragment, getString(R.string.profile_contacts), ""))
        items.add(UserCategoryForUsed(R.id.profileAboutFragment, getString(R.string.profile_about), ""))

        val adapter = ProfileMainCategoryAdapter(items)
        adapter.setClickListener(this)
        rvUserCategoryList.setHasFixedSize(true)
        rvUserCategoryList.layoutManager = LinearLayoutManager(view.context)
        rvUserCategoryList.adapter = adapter
    }

    override fun onItemClick(item: UserCategoryForUsed, position: Int) {
        val args = Bundle()
        val profile: ProfileImpl = user.getProfile() as ProfileImpl
        args.putParcelable(Constants.EXTRA_PROFILE, profile)
        navigateTo(item.id, args)
    }

    private fun onChangePhotoClick() {
        val dialog = SimpleFragmentDialog()
        fragmentManager?.let {
            dialog.show(it, SimpleFragmentDialog.TAG)
        }
    }

    override fun onUserInfoResult(user: UserImpl) {
        this.user = user
    }

    override fun onProgress(isProgress: Boolean) {
        viewLoadAndMessage.setProgress(isProgress)
    }

    private fun onUserLevelUp() {
        navigateTo(R.id.profileCategoryFragment, Bundle())
    }

    private fun onShowMyTasksClick() {
        navigateTo(R.id.taskListPerformerFragment, Bundle())
    }
}
