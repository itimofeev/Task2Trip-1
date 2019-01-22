package com.task2trip.android.UI.Fragment

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.task2trip.android.Model.TabFragmentTitle
import com.task2trip.android.R
import com.task2trip.android.UI.Adapter.TabAdapter
import kotlinx.android.synthetic.main.fragment_login_register.*

class LoginRegisterFragment : BaseFragment() {
    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_login_register
    }

    override fun initComponents(view: View) {
        initViewPager()
        initRadioButtons()
        btReg?.setOnClickListener {
            navigateTo(R.id.registrationFragment, Bundle())
        }
        btLogin?.setOnClickListener {
            navigateTo(R.id.loginFragment, Bundle())
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
                    R.drawable.image_board_01), "03"))
            vpOnBoardPages.adapter = adapter
            vpOnBoardPages.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {}
                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                    override fun onPageSelected(position: Int) {
                        //
                    }
                }
            )
        }
    }
    
    private fun initRadioButtons() {
        //rgDotted.addView(null)
    }
}