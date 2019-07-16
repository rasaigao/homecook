package com.homecook.android.app.login;

import com.homecook.android.app.common.MvpPresenter;
import com.homecook.android.app.common.MvpView;

/**
 * @author rohansaigaonkar
 */
public interface ForgotPasswordContract {
    public interface View extends MvpView<Presenter> {
        void sendPasswordReset();
    }

    public interface Presenter extends MvpPresenter {
        void onSendPasswordResetPressed();
    }
}
