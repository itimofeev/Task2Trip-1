package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Model.User.ProfileImpl
import com.task2trip.android.Presenter.TaskCategoryPresenter
import com.task2trip.android.Presenter.UserProfilePresenter
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TaskCategorySearchAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import com.task2trip.android.View.TaskCategoryView
import com.task2trip.android.View.UserProfileView
import com.task2trip.widgetlibrary.LoadingAndMessage
import com.task2trip.widgetlibrary.MessageFinishShowCallback
import kotlinx.android.synthetic.main.fragment_profile_category.*

class ProfileCategoryFragment : BaseFragment(), TaskCategoryView, UserProfileView, ItemClickListener<TaskCategory> {
    private lateinit var presenterCategory: TaskCategoryPresenter
    private lateinit var presenterProfile: UserProfilePresenter
    private var profile: ProfileImpl = MockData.getEmptyProfile()
    private var isLevelUp = false

    override fun getArgs(args: Bundle?) {
        args?.let {
            profile = args.getParcelable(Constants.EXTRA_PROFILE) ?: MockData.getEmptyProfile()
            isLevelUp = args.getBoolean(Constants.EXTRA_USER_LEVEL_UP, false)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile_category
    }

    override fun initComponents(view: View) {
        initPresenter(view)
        viewLoadAndMessage.setMessageCloseCallback(object : MessageFinishShowCallback {
            override fun onCloseMessage() {
                if (isLevelUp) {
                    val args = Bundle()
                    args.putParcelable(Constants.EXTRA_PROFILE, profile)
                    args.putBoolean(Constants.EXTRA_USER_LEVEL_UP, isLevelUp)
                    navigateTo(R.id.profileAboutFragment, args)
                } else {
                    navigateTo(R.id.profileFragment, Bundle())
                }
            }
        })
        if (isLevelUp) {
            groupStepLevelUp.visibility = View.VISIBLE
            btNextStep.text = getString(R.string.next_step)
        } else {
            groupStepLevelUp.visibility = View.GONE
            btNextStep.text = getString(R.string.save)
        }
        btNextStep.setOnClickListener {
            saveProfileCategories()
        }
        initRecycleView(view)
    }

    private fun initPresenter(view: View) {
        presenterCategory = TaskCategoryPresenter(this, view.context)
        presenterCategory.getCategoryList()
        presenterProfile = UserProfilePresenter(this, view.context)
    }

    private fun initRecycleView(view: View) {
        rvCategoryList.setHasFixedSize(true)
        rvCategoryList.layoutManager = LinearLayoutManager(view.context)
    }

    private fun saveProfileCategories() {
        val adapterItems = rvCategoryList.adapter as TaskCategorySearchAdapter
        val selectedItems = mutableListOf<TaskCategory>()
        for (item in adapterItems.getItems()) {
            if (item.isSelected) {
                selectedItems.add(item)
            }
        }
        profile.setCategories(selectedItems)
        presenterProfile.updateUserProfile(profile)
    }

    override fun onCategoryList(categoryList: List<TaskCategory>) {
        val items = categoryList.toMutableList()
        for ((index, itemAll) in items.withIndex()) {
            for (itemSelected in profile.getCategories()) {
                if (itemAll.id == itemSelected.id) {
                    itemAll.isSelected = true
                    items[index] = itemAll
                    break
                }
            }
        }
        val adapter = TaskCategorySearchAdapter(items)
        adapter.setClickListener(this)
        rvCategoryList.adapter = adapter
    }

    override fun onUserProfileUpdateResult(profile: ProfileImpl) {
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

    override fun onItemClick(item: TaskCategory, position: Int) {
        item.isSelected = !item.isSelected
    }
}