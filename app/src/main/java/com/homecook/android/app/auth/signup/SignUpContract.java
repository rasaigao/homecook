package com.homecook.android.app.auth.signup;

import androidx.annotation.NonNull;

import com.homecook.android.app.common.MvpPresenter;
import com.homecook.android.app.common.MvpView;

/**
 * @author rohansaigaonkar
 */
public interface SignUpContract {
    interface View extends MvpView<Presenter> {
        void signUp();
    }

    interface Presenter extends MvpPresenter {
        void onSignUpPressed(@NonNull String email,
                             @NonNull String password,
                             @NonNull final String firstName,
                             @NonNull final String lastName);
    }
}
