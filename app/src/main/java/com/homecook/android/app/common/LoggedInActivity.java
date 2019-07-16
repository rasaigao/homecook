package com.homecook.android.app.common;

import com.google.firebase.auth.FirebaseAuth;

/**
 * @author rohansaigaonkar
 */
public abstract class LoggedInActivity extends MainActivity {

    @Override
    public void onInfoClicked() {

    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }


}
