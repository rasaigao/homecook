package com.homecook.android.app.entry;

import com.homecook.android.app.common.MvpPresenter;
import com.homecook.android.app.common.MvpView;

/**
 * @author rohansaigaonkar
 */
public interface EntryContract {

    interface View extends MvpView<Presenter> {

        void goToSSOGoogleScreen();

        void goToEmailSignUpScreen();

        void goToLoginScreen();
    }

    interface Presenter extends MvpPresenter {

        void onSignUpWithGoogleClicked();

        void onSignUpWithEmailClicked();

        void onAccountExistsClicked();

    }
}