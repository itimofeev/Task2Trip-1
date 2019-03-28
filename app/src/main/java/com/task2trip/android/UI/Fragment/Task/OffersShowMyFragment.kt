package com.task2trip.android.UI.Fragment.Task

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TabAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_offers_show_my.*

class OffersShowMyFragment : BaseFragment() {
    private var taskId = ""
    private var isMyOffers = false
    private var selectedPage = 0

    override fun getArgs(args: Bundle?) {
        args?.let {
            taskId = it.getString(Constants.EXTRA_TASK_ID, "")
            isMyOffers = it.getBoolean(Constants.EXTRA_OFFER_IS_SHOW_MY, false)
            selectedPage = it.getInt(Constants.EXTRA_SELECTED_PAGE, 0)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_offers_show_my
    }

    override fun initComponents(view: View) {
        setToolbar(toolbar)
        initViewPager()
        btCreateTask.setOnClickListener {
            navigateTo(R.id.taskCategoryFragment, Bundle())
        }
    }

    private fun initViewPager() {
        childFragmentManager.let {
            val adapter = TabAdapter(it)
            adapter.addItem(TabFragmentTitle(TaskOffersFragment.getInstance(taskId, isMyOffers), getString(R.string.title_you_select)))
            adapter.addItem(TabFragmentTitle(TaskOffersFragment.getInstance(taskId, isMyOffers), getString(R.string.title_pending)))
            vpOfferList.adapter = adapter
            vpOfferList.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    //
                }
            })
            vpOfferList.currentItem = selectedPage
            tabOfferList.setupWithViewPager(vpOfferList)
        }
    }
}
