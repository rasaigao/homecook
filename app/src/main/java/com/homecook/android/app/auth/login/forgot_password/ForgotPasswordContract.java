package com.homecook.android.app.auth.login.forgot_password;

import androidx.annotation.NonNull;

import com.homecook.android.app.common.MvpPresenter;
import com.homecook.android.app.common.MvpView;

/**
 * @author rohansaigaonkar
 */
public interface ForgotPasswordContract {
    public interface View extends MvpView<Presenter> {

        void onPasswordEmailSent();

        void showInvalidEmail();
    }

    public interface Presenter extends MvpPresenter {
        void onSendPasswordResetPressed(@NonNull String email);
    }
}
