package com.task2trip.android.UI.Dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class SimpleFragmentDialog: DialogFragment() {
    companion object {
        val TAG: String = "SimpleFragmentDialog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    inner class Builder {
        //
    }
}
/*
public class SimpleDialogFragment extends DialogFragment {

    @Nullable
    private String title;
    @Nullable
    private String description;
    @Nullable
    private String positiveButtonName;
    @Nullable
    private String negativeButtonName;

    public static SimpleDialogFragment newInstance(@NonNull String title,
                                                   @NonNull String description,
                                                   @NonNull String positiveButtonName,
                                                   @NonNull String negativeButtonName) {
        Bundle args = new Bundle();
        args.putString(AppExtras.EXTRA_DIALOG_TITLE, title);
        args.putString(AppExtras.EXTRA_DIALOG_DESCRIPTION, description);
        args.putString(AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_TITLE, positiveButtonName);
        args.putString(AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_TITLE, negativeButtonName);
        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readArguments(getArguments());
    }

    private void readArguments(@Nullable Bundle arguments) {
        if (arguments != null) {
            title = arguments.getString(AppExtras.EXTRA_DIALOG_TITLE, "");
            description = arguments.getString(AppExtras.EXTRA_DIALOG_DESCRIPTION, "");
            positiveButtonName = arguments.getString(AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_TITLE, "");
            negativeButtonName = arguments.getString(AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_TITLE, "");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setMessage(description)
                .setCancelable(false)
                .setPositiveButton(positiveButtonName, (dialog, id) -> {
                    dialog.cancel();
                    onResult(Activity.RESULT_OK);
                })
                .setNegativeButton(negativeButtonName, (dialog, id) -> {
                    dialog.cancel();
                    onResult(Activity.RESULT_CANCELED);
                });
        return builder.create();
    }

    private void onResult(int resultCode) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            targetFragment.onActivityResult(getTargetRequestCode(), resultCode, null);
        }
    }

}
*/
