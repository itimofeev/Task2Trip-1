package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TabAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_list_performer_pager.*

class TaskListPerformerPagerFragment: BaseFragment() {
    private var userId: String = ""
    private var userRole: String = ""
    private var isMessage = false
    private var message = ""

    override fun getArgs(args: Bundle?) {
        args?.let {
            userId = it.getString(Constants.EXTRA_USER_ID, "") ?: ""
            userRole = it.getString(Constants.EXTRA_USER_ROLE, "") ?: ""
            isMessage = it.getBoolean(Constants.EXTRA_IS_MESSAGE, false)
            message = it.getString(Constants.EXTRA_MESSAGE_TEXT, "")
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_list_performer_pager
    }

    override fun initComponents(view: View) {
        initViewPager()
        if (isMessage) {
            onMessage(message)
        }
    }

    private fun initViewPager() {
        childFragmentManager.let {
            val adapter = TabAdapter(it)
            adapter.addItem(
                TabFragmentTitle(TaskListTravelerFragment.getInstance(userId), "Путешественник"))
            adapter.addItem(
                TabFragmentTitle(TaskListPerformerFragment.getInstance(userId), "Местный житель"))
            vpTaskList.adapter = adapter
            vpTaskList.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    //
                }
            })
            tabTaskList.setupWithViewPager(vpTaskList)
        }
    }
}