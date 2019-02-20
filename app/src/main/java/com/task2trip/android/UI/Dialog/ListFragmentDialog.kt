package com.task2trip.android.UI.Dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.task2trip.android.Common.Constants

class ListFragmentDialog: DialogFragment() {
    var layoutId = 0

    companion object {
        fun getInstance() {
            //
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layoutId = it.getInt(Constants.EXTRA_LAYOUT_RESOURCE, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (layoutId == 0) {
            super.onCreateView(inflater, container, savedInstanceState)
        } else {
            inflater.inflate(layoutId, container, false)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    //DialogFragment dialogFragment = new ListFragmentDialog();
    //dialogFragment.show(ft, "dialog");
}