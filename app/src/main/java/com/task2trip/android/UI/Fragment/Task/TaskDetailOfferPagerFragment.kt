package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.MockData
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TabAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_detail_offer_pager.*

class TaskDetailOfferPagerFragment : BaseFragment() {
    private var isEditTask = false
    private var taskItem: Task = MockData.getEmptyTask()
    private var userRoleName: String = MockData.getEmptyUser().getRole().name

    override fun getArgs(args: Bundle?) {
        args?.let {
            isEditTask = it.getBoolean(Constants.EXTRA_TASK_IS_EDIT, false)
            taskItem = it.getParcelable(Constants.EXTRA_TASK) ?: MockData.getEmptyTask()
            userRoleName = it.getString(Constants.EXTRA_USER_ROLE, UserRole.TRAVELER.name) ?: MockData.getEmptyUser().getRole().name
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_detail_offer_pager
    }

    override fun initComponents(view: View) {
        initViewPager()
    }

    private fun initViewPager() {
        childFragmentManager.let {
            val adapter = TabAdapter(it)
            adapter.addItem(TabFragmentTitle(TaskDetailsFragment.getInstance(taskItem, isEditTask, userRoleName), getString(R.string.title_details)))
            adapter.addItem(TabFragmentTitle(TaskOffersFragment.getInstance(taskItem.id, false), getString(R.string.title_offers)))
            vpOfferList.adapter = adapter
            vpOfferList.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    //
                }
            })
            tabOfferList.setupWithViewPager(vpOfferList)
        }
    }
}
