package com.homecook.android.app.auth.login;

import androidx.annotation.NonNull;

import com.homecook.android.app.common.MvpPresenter;
import com.homecook.android.app.common.MvpView;

/**
 * @author rohansaigaonkar
 */
public interface SignInContract {
    public interface View extends MvpView<Presenter> {
        void login();

        void forgotPassword();

        void showInvalidCreds();
    }

    public interface Presenter extends MvpPresenter {
        void onLoginPressed(@NonNull String email, @NonNull String password);

        void onForgotPasswordPressed();
    }
}
