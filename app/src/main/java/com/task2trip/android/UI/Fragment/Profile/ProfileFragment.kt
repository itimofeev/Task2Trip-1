package com.task2trip.android.UI.Fragment.Profile

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.User.UserCategoryForUsed
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.ProfileMainCategoryAdapter
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
        tvPhotoAdd.setOnClickListener { onPhotoAddClick() }
        btRevertUserRole.setOnClickListener { onRevertUserRoleClick() }
        initPhotoList(view)
        initAboutUserCategoryList(view)
    }

    private fun initPhotoList(@NonNull view: View) {
        rvProfilePhotoList.setHasFixedSize(true)
        rvProfilePhotoList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        rvProfilePhotoList.adapter = null
    }

    private fun initAboutUserCategoryList(@NonNull view: View) {
        val items = ArrayList<UserCategoryForUsed>()
        items.add(
            UserCategoryForUsed(
                R.id.profileCategoryFragment,
                "Категории",
                emptyList()
            )
        )
        items.add(
            UserCategoryForUsed(
                R.id.profileMainInfoFragment,
                "Основное",
                emptyList()
            )
        )
        items.add(
            UserCategoryForUsed(
                R.id.profileContactsFragment,
                "Контакты",
                emptyList()
            )
        )
        items.add(
            UserCategoryForUsed(
                R.id.profileAboutFragment,
                "Обо мне",
                emptyList()
            )
        )

        val adapter = ProfileMainCategoryAdapter(items)
        adapter.setClickListener(this)
        rvAboutCategoryList.setHasFixedSize(true)
        rvAboutCategoryList.layoutManager = LinearLayoutManager(view.context)
        rvAboutCategoryList.adapter = adapter
    }

    override fun onItemClick(item: UserCategoryForUsed, position: Int) {
        navigateTo(item.id, Bundle())
    }

    private fun onPhotoAddClick() {
        //
    }

    private fun onRevertUserRoleClick() {
        //
    }
}
