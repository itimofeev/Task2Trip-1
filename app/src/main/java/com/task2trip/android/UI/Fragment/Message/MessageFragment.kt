package com.task2trip.android.UI.Fragment.Message

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TabAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_message
    }

    override fun initComponents(view: View) {
        initViewPager()
    }

    private fun initViewPager() {
        fragmentManager?.let {
            val adapter = TabAdapter(it)
            adapter.addItem(TabFragmentTitle(MessageChatListFragment(), getString(R.string.title_messages)))
            adapter.addItem(TabFragmentTitle(MessageNotificationFragment(), getString(R.string.title_notifications)))
            vpMessage.adapter = adapter
            vpMessage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    //
                }
            })
            tabMessage.setupWithViewPager(vpMessage)
        }
    }
}
