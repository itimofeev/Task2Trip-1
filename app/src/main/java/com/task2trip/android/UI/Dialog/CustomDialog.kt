class CustomDialog: DialogFragment {
/*
public class CustomDialog extends AppCompatDialogFragment {

    private static final int DIALOG_THEME = R.style.CustomDialogTheme;
    private static final int BUTTON_PADDING = 40;

    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private String positiveButton;
    @NonNull
    private String negativeButton;
    @LayoutRes
    private int dialogLayout;
    @StyleRes
    private int dialogTheme;
    @ColorInt
    private int positiveButtonColor;
    @ColorInt
    private int negativeButtonColor;
    private boolean cancelable;
    private int activityRequestCode;

    public static CustomDialog newInstance(Builder builder) {
        Bundle args = new Bundle();
        if (builder.title != null)
            args.putString(AppExtras.EXTRA_DIALOG_TITLE, builder.title);
        if (builder.message != null)
            args.putString(AppExtras.EXTRA_DIALOG_DESCRIPTION, builder.message);
        if (builder.positiveButton != null)
            args.putString(AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_TITLE, builder.positiveButton);
        if (builder.negativeButton != null)
            args.putString(AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_TITLE, builder.negativeButton);
        if (builder.dialogLayout > 0)
            args.putInt(AppExtras.EXTRA_DIALOG_LAYOUT, builder.dialogLayout);
        if (builder.dialogTheme > 0)
            args.putInt(AppExtras.EXTRA_DIALOG_THEME, builder.dialogLayout);
        if (builder.positiveButtonColor != 0)
            args.putInt(AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_COLOR, builder.positiveButtonColor);
        if (builder.negativeButtonColor != 0)
            args.putInt(AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_COLOR, builder.negativeButtonColor);
        if (builder.activityRequestCode > 0)
            args.putInt(AppExtras.EXTRA_DIALOG_REQUEST_CODE, builder.activityRequestCode);
        args.putBoolean(AppExtras.EXTRA_DIALOG_CANCELABLE, builder.cancelable);
        CustomDialog fragment = new CustomDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readArguments(getArguments());
    }

    private void readArguments(@Nullable Bundle arguments) {
        if (arguments == null) return;
        title = arguments.getString(AppExtras.EXTRA_DIALOG_TITLE, "");
        message = arguments.getString(AppExtras.EXTRA_DIALOG_DESCRIPTION, "");
        positiveButton = arguments.getString(AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_TITLE, "");
        negativeButton = arguments.getString(AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_TITLE, "");
        dialogLayout = arguments.getInt(AppExtras.EXTRA_DIALOG_LAYOUT, 0);
        dialogTheme = arguments.getInt(AppExtras.EXTRA_DIALOG_THEME, DIALOG_THEME);
        positiveButtonColor = arguments.getInt(AppExtras.EXTRA_DIALOG_POSITIVE_BUTTON_COLOR, 0);
        negativeButtonColor = arguments.getInt(AppExtras.EXTRA_DIALOG_NEGATIVE_BUTTON_COLOR, 0);
        cancelable = arguments.getBoolean(AppExtras.EXTRA_DIALOG_CANCELABLE, false);
        activityRequestCode = arguments.getInt(AppExtras.EXTRA_DIALOG_REQUEST_CODE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(cancelable);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), dialogTheme);
        builder.setTitle(title);
        if (dialogLayout > 0) {
            builder.setView(dialogLayout);
        } else {
            builder.setMessage(message);
        }
        if (!positiveButton.isEmpty()) {
            builder.setPositiveButton(positiveButton, (dialog, id) -> {
                dialog.cancel();
                onResult(Activity.RESULT_OK);
            });
        }
        if (!negativeButton.isEmpty()) {
            builder.setNegativeButton(negativeButton, (dialog, id) -> {
                dialog.cancel();
                onResult(Activity.RESULT_CANCELED);
            });
        }
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(arg -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setPadding(BUTTON_PADDING, 0, 0, 0);
            if (positiveButtonColor != 0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(positiveButtonColor);
            }
            if (negativeButtonColor != 0) {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(negativeButtonColor);
            }
        });
        return dialog;
    }

    private void onResult(int resultCode) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            targetFragment.onActivityResult(getTargetRequestCode(), resultCode, null);
        } else if (activityRequestCode > 0 && getActivity() instanceof CustomDialogListener) {
            CustomDialogListener listener = (CustomDialogListener) getActivity();
            listener.onDialogResult(activityRequestCode, resultCode, null);
        }
    }

    public static final class Builder {

        @Nullable
        private String title;
        @Nullable
        private String message;
        @Nullable
        private String positiveButton;
        @Nullable
        private String negativeButton;
        @LayoutRes
        private int dialogLayout;
        @StyleRes
        private int dialogTheme;
        @ColorInt
        private int positiveButtonColor;
        @ColorInt
        private int negativeButtonColor;
        private boolean cancelable;
        private int activityRequestCode;

        public Builder() {
            dialogLayout = 0;
            cancelable = false;
        }

        public Builder title(@NonNull String val) {
            title = val;
            return this;
        }

        public Builder message(@NonNull String val) {
            message = val;
            return this;
        }

        public Builder positiveButton(@NonNull String val) {
            positiveButton = val;
            return this;
        }

        public Builder negativeButton(@NonNull String val) {
            negativeButton = val;
            return this;
        }

        public Builder dialogLayout(@LayoutRes int val) {
            dialogLayout = val;
            return this;
        }

        public Builder cancelable(boolean val) {
            cancelable = val;
            return this;
        }

        public Builder dialogTheme(@StyleRes int val) {
            dialogTheme = val;
            return this;
        }

        public Builder positiveButtonColor(@ColorInt int val) {
            positiveButtonColor = val;
            return this;
        }

        public Builder negativeButtonColor(@ColorInt int val) {
            negativeButtonColor = val;
            return this;
        }

        public Builder requestCode(int val) {
            activityRequestCode = val;
            return this;
        }

        public CustomDialog build() {
            return CustomDialog.newInstance(this);
        }

    }

    public interface CustomDialogListener {

        void onDialogResult(int requestCode, int resultCode, @Nullable Bundle bundle);

    }

}
*/
}
