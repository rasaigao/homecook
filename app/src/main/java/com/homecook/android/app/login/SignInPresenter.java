package com.homecook.android.app.login;

import androidx.annotation.NonNull;

/**
 * @author rohansaigaonkar
 */

public class SignInPresenter implements SignInContract.Presenter {
    @NonNull private SignInContract.View view;

    public SignInPresenter(@NonNull SignInContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
    @Override
    public void start() {

    }
    @Override
    public void onLoginPressed() {
        view.login();
    }

    @Override
    public void onForgotPasswordPressed() {
        view.forgotPassword();
    }
}
