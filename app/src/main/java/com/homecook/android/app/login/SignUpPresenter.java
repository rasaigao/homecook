package com.homecook.android.app.login;

import androidx.annotation.NonNull;

/**
 * @author rohansaigaonkar
 */

public class SignUpPresenter implements SignUpContract.Presenter {
    @NonNull private SignUpContract.View view;

    public SignUpPresenter(@NonNull SignUpContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
    @Override
    public void start() {

    }
    @Override
    public void onSignUpPressed() {
        view.signUp();
    }
}
