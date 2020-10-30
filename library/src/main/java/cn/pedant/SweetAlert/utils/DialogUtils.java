package cn.pedant.SweetAlert.utils;

import android.content.Context;

import com.blankj.utilcode.util.ObjectUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogUtils {
    private Context context;
    private String title;
    private String content;
    private String okText;
    private String cancelText;
    private boolean enableCancelButton;
    private boolean enableOkButton = true;
    private OnClickListener okListener;
    private OnClickListener cancelListener;

    public static interface OnClickListener {
        void click(SweetAlertDialog dialog);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String content;
        private String okText;
        private String cancelText;
        private boolean enableCancelButton;
        private boolean enableOkButton = true;
        private OnClickListener okListener;
        private OnClickListener cancelListener;

        public Builder setEnableCancelButton(boolean enableCancelButton) {
            this.enableCancelButton = enableCancelButton;
            return this;
        }

        public Builder setEnableOkButton(boolean enableOkButton) {
            this.enableOkButton = enableOkButton;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCancelText(String cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setOkText(String okText) {
            this.okText = okText;
            return this;
        }

        public Builder setCancelListener(OnClickListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public Builder setOkListener(OnClickListener okListener) {
            this.okListener = okListener;
            return this;
        }

        public Builder(Context context) {
            this.context = context;
        }

        private DialogUtils build() {
            return new DialogUtils(this);
        }

        public void show() {
            build().show();
        }
    }

    private void show() {
        new SweetAlertDialog(this.context, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(ObjectUtils.isNotEmpty(this.title) ? this.title : "提示")
                .setContentText(this.content)
                .setCancelText(ObjectUtils.isNotEmpty(this.cancelText) ? this.cancelText : "取消")
                .setConfirmText(ObjectUtils.isNotEmpty(this.okText) ? this.okText : "确定")
                .showCancelButton(this.enableCancelButton)
                .setCancelClickListener(sDialog -> {
                    if (ObjectUtils.isNotEmpty(this.cancelListener)) {
                        this.cancelListener.click(sDialog);
                    }
                    sDialog.dismiss();
                })
                .setConfirmClickListener(sDialog -> {
                    if (ObjectUtils.isNotEmpty(this.okListener))
                        this.okListener.click(sDialog);
                    sDialog.dismiss();
                })
                .show();
    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    public DialogUtils(Builder builder) {
        this.context = builder.context;
        this.title = builder.title;
        this.content = builder.content;
        this.okListener = builder.okListener;
        this.cancelListener = builder.cancelListener;
        this.okText = builder.okText;
        this.cancelText = builder.cancelText;
        this.enableOkButton = builder.enableOkButton;
        this.enableCancelButton = builder.enableCancelButton;
    }
}
