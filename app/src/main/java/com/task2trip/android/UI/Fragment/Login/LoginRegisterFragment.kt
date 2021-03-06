package com.task2trip.android.UI.Fragment.Login

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.LocalStoreManager
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.Model.User.User
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Model.User.UserRole
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TabAdapter
import com.task2trip.android.UI.Fragment.BaseFragment
import com.task2trip.android.UI.Fragment.OnBoardingFragment
import kotlinx.android.synthetic.main.fragment_login_register.*

class LoginRegisterFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_login_register
    }

    override fun initComponents(view: View) {
        val storeManager = context?.let { LocalStoreManager(it) }
        storeManager?.let {
            if (it.get(Constants.EXTRA_FIRST_START_APP, true) != false) {
                initViewPager()
                initRadioButtons(3)
                it.set(Constants.EXTRA_FIRST_START_APP, false)
            } else {
                rgDotted.visibility = View.GONE
            }
        }
        btReg?.setOnClickListener {
            navigateTo(R.id.registrationFragment, Bundle())
        }
        btLogin?.setOnClickListener {
            navigateTo(R.id.loginFragment, Bundle())
        }
        tvSkip?.setOnClickListener {
            val user: User = UserImpl()
            with(user) {
                setName("Гость")
                setRole(UserRole.NOT_AUTHORIZED)
                context?.let { saveUserData(it) }
            }
            navigateTo(R.id.taskListPerformerFragment, Bundle())
        }
    }

    private fun initViewPager() {
        fragmentManager?.let {
            val adapter = TabAdapter(it)
            adapter.addItem(TabFragmentTitle(
                OnBoardingFragment.instance(
                    getString(R.string.board_title_01),
                    getString(R.string.board_msg_01),
                    R.drawable.image_board_01), "01"))
            adapter.addItem(TabFragmentTitle(
                OnBoardingFragment.instance(
                    getString(R.string.board_title_02),
                    getString(R.string.board_msg_02),
                    R.drawable.image_board_02), "02"))
            adapter.addItem(TabFragmentTitle(
                OnBoardingFragment.instance(
                    getString(R.string.board_title_03),
                    getString(R.string.board_msg_03),
                    R.drawable.image_board_03), "03"))
            vpOnBoardPages.adapter = adapter
            vpOnBoardPages.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {}
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                    override fun onPageSelected(position: Int) {
                        rgDotted.setCheckedPosition(position)
                    }
                }
            )
        }
    }
    
    private fun initRadioButtons(count: Int) {
        rgDotted.init(count)
    }
}