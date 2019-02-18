package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TabAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_task_list_performer.*

class TaskListPerformerFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_task_list_performer
    }

    override fun initComponents(view: View) {
        initViewPager()
    }

    private fun initViewPager() {
        fragmentManager?.let {
            val adapter = TabAdapter(it)
            adapter.addItem(TabFragmentTitle(TaskListTravelerFragment(), "Путешественник"))
            adapter.addItem(TabFragmentTitle(TaskListTravelerFragment(), "Исполнитель"))
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