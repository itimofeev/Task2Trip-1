package com.task2trip.android.UI.Fragment

import android.os.Bundle
import android.view.View
import com.task2trip.android.Common.Constants
import com.task2trip.android.R
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class OnBoardingFragment : BaseFragment() {
    private var textInfo: String = ""

    companion object {
        @JvmStatic
        fun instance(text: String) =
            OnBoardingFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.EXTRA_USER_TOKEN, text)
                }
            }
    }

    override fun getArgs(args: Bundle?) {
        args.let {
            textInfo = args?.getString(Constants.EXTRA_USER_TOKEN, "").toString()
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_on_boarding
    }

    override fun initComponents(view: View) {
        tvTextInfo.text = textInfo
    }
}