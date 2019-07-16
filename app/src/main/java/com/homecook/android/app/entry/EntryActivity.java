package com.homecook.android.app.entry;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crashlytics.android.Crashlytics;
import com.homecook.android.app.R;
import com.homecook.android.app.common.MainActivity;
import com.homecook.android.app.login.ForgotPasswordFragment;
import com.homecook.android.app.login.SignInFragment;
import com.homecook.android.app.login.SignUpFragment;

import io.fabric.sdk.android.Fabric;

/**
 * @author rohansaigaonkar
 */
public class EntryActivity extends MainActivity implements
        EntryFragment.Callback,
        SignInFragment.Callback,
        SignUpFragment.Callback,
        ForgotPasswordFragment.Callback {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        navigateToScreen(new EntryFragment());
    }

    @Override
    public void goToSignUpWithEmailScreen() {
        navigateToScreen(new SignUpFragment());
    }

    @Override
    public void goToLoginCredentialScreen() {
        navigateToScreen(new SignInFragment());
    }

    @Override
    public void goToSSOLoginScreen() {

    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void signIn() {
        //todo sign in behaviour
    }

    @Override
    public void signUp() {
        //todo sign up behaviour
    }

    @Override
    public void sendPasswordReset() {
        //todo: send password reset
    }

    private void navigateToScreen(@NonNull Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.entry_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

}
