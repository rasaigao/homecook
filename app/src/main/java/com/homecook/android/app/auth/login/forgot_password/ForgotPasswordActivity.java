package com.homecook.android.app.auth.login.forgot_password;

import android.os.Bundle;

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
public class ForgotPasswordActivity extends MainActivity implements ForgotPasswordFragment.Callback {

    private FragmentManager fragmentManager;
    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();

    @Override
    public void onInfoClicked() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        navigateToFragment(new ForgotPasswordFragment());
    }

    @Override
    public void navigateToFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.entry_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onRecoverySuccess() {
        navigateToFragment(new PasswordResetSuccessFragment());
    }
}
