package com.task2trip.android.UI.Dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.task2trip.android.Common.Constants

class SimpleFragmentDialog: DialogFragment() {
    private var title: String? = null
    private var description: String? = null
    private var positiveButtonName: String? = null
    private var negativeButtonName: String? = null

    companion object {
        const val TAG: String = "SimpleFragmentDialog"
        fun getInstance(title: String, description: String, positiveButtonName: String,
                        negativeButtonName: String): SimpleFragmentDialog {
            val args = Bundle()
            args.putString(Constants.EXTRA_DIALOG_TITLE, title)
            args.putString(Constants.EXTRA_DIALOG_DESCRIPTION, description)
            args.putString(Constants.EXTRA_DIALOG_POSITIVE_BUTTON_TITLE, positiveButtonName)
            args.putString(Constants.EXTRA_DIALOG_NEGATIVE_BUTTON_TITLE, negativeButtonName)
            val fragment = SimpleFragmentDialog()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readArguments(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = context
        if (context != null) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                .setMessage(description)
                .setCancelable(false)
            if (!positiveButtonName.isNullOrEmpty()) {
                builder.setPositiveButton(positiveButtonName) { dialog, id ->
                    dialog.cancel()
                    onResult(Activity.RESULT_OK)
                }
            }
            if (!negativeButtonName.isNullOrEmpty()) {
                builder.setNegativeButton(negativeButtonName) { dialog, id ->
                    dialog.cancel()
                    onResult(Activity.RESULT_CANCELED)
                }
            }
            return builder.create()
        } else {
            return super.onCreateDialog(savedInstanceState)
        }
    }

    private fun readArguments(arguments: Bundle?) {
        arguments?.let {
            title = it.getString(Constants.EXTRA_DIALOG_TITLE, "")
            description = it.getString(Constants.EXTRA_DIALOG_DESCRIPTION, "")
            positiveButtonName = it.getString(Constants.EXTRA_DIALOG_POSITIVE_BUTTON_TITLE, "")
            negativeButtonName = it.getString(Constants.EXTRA_DIALOG_NEGATIVE_BUTTON_TITLE, "")
        }
    }

    private fun onResult(resultCode: Int) {
        val targetFragment = targetFragment
        targetFragment?.onActivityResult(targetRequestCode, resultCode, null)
    }
}