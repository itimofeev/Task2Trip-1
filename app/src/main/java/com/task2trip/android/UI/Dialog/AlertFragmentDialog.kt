package com.task2trip.android.UI.Dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.task2trip.android.Common.Constants

class AlertFragmentDialog: DialogFragment() {
    companion object {
        fun getInstance(title: String, message: String): AlertFragmentDialog {
            val dialog = AlertFragmentDialog()
            val args = Bundle()
            args.putString(Constants.EXTRA_DIALOG_TITLE, title)
            args.putString(Constants.EXTRA_DIALOG_MESSAGE, message)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var title: String = ""
        var message: String = ""
        arguments?.let {
            title = it.getString(Constants.EXTRA_DIALOG_TITLE, "")
            message = it.getString(Constants.EXTRA_DIALOG_MESSAGE, "")
        }
        return if (activity != null) {
            AlertDialog.Builder(activity!!)
                //.setIcon(R.drawable.alert_dialog_icon)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() }
                )
                .create()
        } else {
            super.onCreateDialog(savedInstanceState)
        }
    }

    // TODO: переделать
    fun showDialog() {
        val newFragment = AlertFragmentDialog.getInstance("", "")
        newFragment.show(fragmentManager!!, "dialog")
    }
}