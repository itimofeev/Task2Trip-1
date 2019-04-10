package com.task2trip.android.UI.Widget

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioGroup
import android.os.Build
import android.widget.RadioButton
import android.util.TypedValue
import androidx.annotation.NonNull
import com.task2trip.android.R

class RadioSwipeView(@NonNull context: Context, attrs: AttributeSet? = null) : RadioGroup(context, attrs) {
    private var lastPosition = 0

    fun init(@NonNull mCount: Int) {
        val radioButton = ArrayList<RadioButton>()
        for (i in 0 until mCount) {
            try {
                val hw = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics).toInt()
                val rb = RadioButton(context)
                rb.id = i
                rb.buttonDrawable = null
                radioButton.add(rb)
                val params = RadioGroup.LayoutParams(hw, hw)
                params.leftMargin = hw / 4
                params.rightMargin = hw / 4
                radioButton[i].layoutParams = params
                if (i == 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        radioButton[i].background = context.resources.getDrawable(R.drawable.bg_swipe_rb_active, null)
                    } else {
                        radioButton[i].background = context.resources.getDrawable(R.drawable.bg_swipe_rb_active)
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        radioButton[i].background = context.resources.getDrawable(R.drawable.bg_swipe_rb_no_active, null)
                    } else {
                        radioButton[i].background = context.resources.getDrawable(R.drawable.bg_swipe_rb_no_active)
                    }
                }
                if (i == 0) {
                    radioButton[i].isChecked = true    // Установим, что первый элемент выбран
                    lastPosition = 0
                }
                this.addView(radioButton[i])
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        //setRadioButtonClickListener()
    }

    fun setCheckedPosition(position: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getChildAt(position).background = context.resources.getDrawable(R.drawable.bg_swipe_rb_active, null)
            getChildAt(lastPosition).background = context.resources.getDrawable(R.drawable.bg_swipe_rb_no_active, null)
        } else {
            getChildAt(position).background = context.resources.getDrawable(R.drawable.bg_swipe_rb_active)
            getChildAt(lastPosition).background = context.resources.getDrawable(R.drawable.bg_swipe_rb_no_active)
        }
        lastPosition = position;
    }
}