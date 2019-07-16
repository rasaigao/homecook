package com.homecook.android.app.login;

import androidx.annotation.NonNull;

/**
 * @author rohansaigaonkar
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {
    @NonNull private ForgotPasswordContract.View view;

    public ForgotPasswordPresenter(@NonNull ForgotPasswordContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
    @Override
    public void start() {

    }

    @Override
    public void onSendPasswordResetPressed() {
        view.sendPasswordReset();
    }
}
