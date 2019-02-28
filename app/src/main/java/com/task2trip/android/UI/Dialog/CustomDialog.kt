package com.task2trip.android.UI.Dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

open class CustomDialog: DialogFragment() {
    private var title: String? = null
    private var message: String = ""
    private var positiveButton: String = ""
    private var negativeButton: String = ""
    private var dialogLayout: Int = 0
    private var activityRequestCode: Int = 0
    private val BUTTON_PADDING = 40
    private var isCancelableDialog = false
    @StyleRes
    private var dialogTheme: Int = 0
    @ColorInt
    private var positiveButtonColor: Int = 0
    @ColorInt
    private var negativeButtonColor: Int = 0

    companion object {
        fun getInstance(builder: Builder): CustomDialog {
            val args = Bundle()
            if (builder.title != null)
                args.putString("AppExtras.EXTRA_DIALOG_TITLE", builder.title)
            if (builder.message != null)
                args.putString("AppExtras.EXTRA_DIALOG_DESCRIPTION", builder.message)
            if (builder.positiveButton != null)
                args.putString("AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_TITLE", builder.positiveButton)
            if (builder.negativeButton != null)
                args.putString("AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_TITLE", builder.negativeButton)
            if (builder.dialogLayout > 0)
                args.putInt("AppExtras.EXTRA_DIALOG_LAYOUT", builder.dialogLayout)
            if (builder.positiveButtonColor != 0)
                args.putInt("AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_COLOR", builder.positiveButtonColor)
            if (builder.negativeButtonColor != 0)
                args.putInt("AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_COLOR", builder.negativeButtonColor)
            if (builder.activityRequestCode > 0)
                args.putInt("AppExtras.EXTRA_DIALOG_REQUEST_CODE", builder.activityRequestCode)
            args.putBoolean("AppExtras.EXTRA_DIALOG_CANCELABLE", builder.cancelable)
            val fragment = CustomDialog()
            fragment.arguments = args
            return fragment
        }

        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readArguments(arguments)
    }

    private fun readArguments(arguments: Bundle?) {
        if (arguments == null) return
        title = arguments.getString("AppExtras.EXTRA_DIALOG_TITLE", "")
        message = arguments.getString("AppExtras.EXTRA_DIALOG_DESCRIPTION", "")
        positiveButton = arguments.getString("AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_TITLE", "")
        negativeButton = arguments.getString("AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_TITLE", "")
        dialogLayout = arguments.getInt("AppExtras.EXTRA_DIALOG_LAYOUT", 0)
        positiveButtonColor = arguments.getInt("AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_COLOR", 0)
        negativeButtonColor = arguments.getInt("AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_COLOR", 0)
        isCancelableDialog = arguments.getBoolean("AppExtras.EXTRA_DIALOG_CANCELABLE", false)
        activityRequestCode = arguments.getInt("AppExtras.EXTRA_DIALOG_REQUEST_CODE")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = isCancelableDialog
        context?.let {
            val builder = AlertDialog.Builder(it, dialogTheme)
            builder.setTitle(title)
            if (dialogLayout > 0) {
                builder.setView(dialogLayout)
            } else {
                builder.setMessage(message)
            }
            if (!positiveButton.isEmpty()) {
                builder.setPositiveButton(positiveButton) { dialog, id ->
                    dialog.cancel()
                    onResult(Activity.RESULT_OK)
                }
            }
            if (!negativeButton.isEmpty()) {
                builder.setNegativeButton(negativeButton) { dialog, id ->
                    dialog.cancel()
                    onResult(Activity.RESULT_CANCELED)
                }
            }

            val dialog = builder.create()
            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setPadding(BUTTON_PADDING, 0, 0, 0)
                if (positiveButtonColor != 0) {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(positiveButtonColor)
                }
                if (negativeButtonColor != 0) {
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(negativeButtonColor)
                }
            }
            return dialog
        }
        return super.onCreateDialog(savedInstanceState)
    }

    private fun onResult(resultCode: Int) {
        val targetFragment = targetFragment
        if (targetFragment != null) {
            targetFragment.onActivityResult(targetRequestCode, resultCode, null)
        } else if (activityRequestCode > 0 && activity is CustomDialogListener) {
            val listener = activity as CustomDialogListener?
            listener!!.onDialogResult(activityRequestCode, resultCode, null)
        }
    }

    class Builder {
        var title: String? = null
        var message: String? = null
        var positiveButton: String? = null
        var negativeButton: String? = null
        @LayoutRes var dialogLayout: Int = 0
        @StyleRes var dialogTheme: Int = 0
        @ColorInt var positiveButtonColor: Int = 0
        @ColorInt var negativeButtonColor: Int = 0
        var cancelable: Boolean = false
        var activityRequestCode: Int = 0

        init {
            dialogLayout = 0
            cancelable = false
        }

        fun build(): CustomDialog {
            return CustomDialog.getInstance(this)
        }
    }

    interface CustomDialogListener {
        fun onDialogResult(requestCode: Int, resultCode: Int, bundle: Bundle?)
    }
}
