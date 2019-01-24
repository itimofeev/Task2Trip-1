package com.task2trip.android.UI.Fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.task2trip.android.Model.UserCategoryForUsed
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.SimpleListAdapter
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {
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
        items.add(UserCategoryForUsed("1111"))
        items.add(UserCategoryForUsed("2222"))

        rvAboutCategoryList.setHasFixedSize(true)
        rvAboutCategoryList.layoutManager = LinearLayoutManager(view.context)
        rvAboutCategoryList.adapter = SimpleListAdapter(items)
    }

    private fun onPhotoAddClick() {
        //
    }

    private fun onRevertUserRoleClick() {
        //
    }
}
