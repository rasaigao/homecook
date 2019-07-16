package com.homecook.android.app.login;

import com.homecook.android.app.common.MvpPresenter;
import com.homecook.android.app.common.MvpView;

/**
 * @author rohansaigaonkar
 */
public interface SignUpContract {
    public interface View extends MvpView<Presenter> {
        void signUp();
    }

    public interface Presenter extends MvpPresenter {
        void onSignUpPressed();
    }
}
