package com.homecook.android.app.entry;

import androidx.annotation.NonNull;

/**
 * @author rohansaigaonkar
 */
public class EntryPresenter implements EntryContract.Presenter {

    @NonNull
    EntryContract.View view;

    public EntryPresenter(@NonNull EntryContract.View view) {
        this.view = view;

    }

    @Override
    public void onSignUpWithGoogleClicked() {
        view.goToSSOGoogleScreen();
    }

    @Override
    public void onSignUpWithEmailClicked() {
        view.goToEmailSignUpScreen();
    }

    @Override
    public void onAccountExistsClicked() {
        view.goToLoginScreen();
    }

    @Override
    public void start() {

    }
}
