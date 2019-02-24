package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.User.UserCategoryForUsed
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ProfileMainCategoryAdapter
import com.task2trip.android.UI.Dialog.SimpleFragmentDialog
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Listener.ItemClickListener
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment(), ItemClickListener<UserCategoryForUsed> {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun initComponents(@NonNull view: View) {
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

    private fun initAboutUserCategoryList(@NonNull view: View) {
        val items = ArrayList<UserCategoryForUsed>()
        items.add(UserCategoryForUsed(R.id.profileMainInfoFragment, "Основное", emptyList()))
        items.add(UserCategoryForUsed(R.id.profileContactsFragment, "Контакты", emptyList()))
        items.add(UserCategoryForUsed(R.id.profileAboutFragment, "Обо мне", emptyList()))

        val adapter = ProfileMainCategoryAdapter(items)
        adapter.setClickListener(this)
        rvUserCategoryList.setHasFixedSize(true)
        rvUserCategoryList.layoutManager = LinearLayoutManager(view.context)
        rvUserCategoryList.adapter = adapter
    }

    override fun onItemClick(item: UserCategoryForUsed, position: Int) {
        navigateTo(item.id, Bundle())
    }

    private fun onChangePhotoClick() {
        val dialog = SimpleFragmentDialog()
        fragmentManager?.let {
            dialog.show(it, SimpleFragmentDialog.TAG)
        }
    }

    private fun onUserLevelUp() {
        navigateTo(R.id.profileCategoryFragment, Bundle())
    }

    private fun onShowMyTasksClick() {
        navigateTo(R.id.taskListPerformerFragment, Bundle())
    }
}
