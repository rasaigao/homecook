package com.homecook.android.app.entry;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crashlytics.android.Crashlytics;
import com.homecook.android.app.R;
import com.homecook.android.app.common.MainActivity;
import io.fabric.sdk.android.Fabric;

/**
 * @author rohansaigaonkar
 */
public class EntryActivity extends MainActivity implements EntryFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        navigateToScreen(new EntryFragment());
    }

    @Override
    public void goToSignUpWithEmailScreen() {

    }

    @Override
    public void goToLoginCredentialScreen() {

    }

    @Override
    public void goToSSOLoginScreen() {

    }

    @Override
    public void exit() {
        finish();
    }

    private void navigateToScreen(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.entry_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

}
