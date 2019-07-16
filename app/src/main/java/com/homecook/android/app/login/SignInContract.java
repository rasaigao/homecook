package com.homecook.android.app.login;

import com.homecook.android.app.common.MvpPresenter;
import com.homecook.android.app.common.MvpView;

/**
 * @author rohansaigaonkar
 */
public interface SignInContract {
    public interface View extends MvpView<Presenter> {
        void login();

        void forgotPassword();
    }

    public interface Presenter extends MvpPresenter {
        void onLoginPressed();

        void onForgotPasswordPressed();
    }
}
