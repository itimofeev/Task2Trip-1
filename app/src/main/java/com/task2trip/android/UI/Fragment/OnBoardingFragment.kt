package com.task2trip.android.UI.Fragment

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.annotation.DrawableRes
import com.task2trip.android.Common.Constants
import com.task2trip.android.R
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class OnBoardingFragment : BaseFragment() {
    private var textTitle: String = ""
    private var textMessage: String = ""
    private var imageBackground: Int = 0
    private var pageNo: Int = 0

    companion object {
        @JvmStatic
        fun instance(title: String, msg: String, @DrawableRes imageBg: Int, @DrawableRes image2: Int, pageNo: Int) =
            OnBoardingFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.EXTRA_BOARD_TITLE, title)
                    putString(Constants.EXTRA_BOARD_MESSAGE, msg)
                    putInt(Constants.EXTRA_BOARD_IMAGE, imageBg)
                    putInt(Constants.EXTRA_BOARD_IMAGE_2, image2)
                    putInt(Constants.EXTRA_BOARD_PAGE, pageNo)
                }
            }
    }

    override fun getArgs(args: Bundle?) {
        args.let {
            textTitle = args?.getString(Constants.EXTRA_BOARD_TITLE, "").toString()
            textMessage = args?.getString(Constants.EXTRA_BOARD_MESSAGE, "").toString()
            imageBackground = args?.getInt(Constants.EXTRA_BOARD_IMAGE, 0) ?: 0
            pageNo = args?.getInt(Constants.EXTRA_BOARD_PAGE, 0) ?: 0
        }
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_on_boarding
    }

    override fun initComponents(view: View) {
        tvTextTitle.text = textTitle
        tvTextMessage.text = textMessage
        if (pageNo == 2) {
            tvTextMessage.gravity = Gravity.START
        } else {
            tvTextMessage.gravity = Gravity.CENTER
        }
        if (imageBackground != 0) {
            ivBackground.setImageResource(imageBackground)
        }
    }
}