package com.task2trip.android.UI.Fragment.Message

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TabAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : BaseFragment() {
    private var userLocalId: String = ""
    private var userId: String = ""
    private var isGotoMessages: Boolean = false

    override fun getArgs(args: Bundle?) {
        args?.let {
            userLocalId = it.getString(Constants.EXTRA_USER_LOCAL_ID, "")
            userId = it.getString(Constants.EXTRA_USER_ID, "")
            isGotoMessages = it.getBoolean(Constants.EXTRA_DIALOG_IS_GOTO_MESSAGE, false)
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_message
    }

    override fun initComponents(view: View) {
        setToolbar(toolbar, true, getString(R.string.title_inbox), false)
        initViewPager()
    }

    private fun initViewPager() {
        childFragmentManager.let {
            val adapter = TabAdapter(it)
            adapter.addItem(TabFragmentTitle(MessageChatListFragment.getInstance(userLocalId, userId, isGotoMessages), getString(R.string.title_messages)))
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
