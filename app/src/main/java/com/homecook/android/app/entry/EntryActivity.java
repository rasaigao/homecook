package com.homecook.android.app.entry;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.homecook.android.app.R;
import com.homecook.android.app.auth.login.GoogleSignInActivity;
import com.homecook.android.app.auth.login.SignInFragment;
import com.homecook.android.app.auth.login.forgot_password.ForgotPasswordActivity;
import com.homecook.android.app.auth.signup.SignUpFragment;
import com.homecook.android.app.common.Keys;
import com.homecook.android.app.common.MainActivity;
import com.homecook.android.app.feed.FeedActivity;

import io.fabric.sdk.android.Fabric;

/**
 * @author rohansaigaonkar
 */
public class EntryActivity extends MainActivity implements
        EntryFragment.Callback,
        SignInFragment.Callback,
        SignUpFragment.Callback {

    private String TAG = EntryActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private FirebaseAuth mAuth;
    private FirebaseAnalytics mAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        mAuth = FirebaseAuth.getInstance();

        navigateToFragment(new EntryFragment());
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(false);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void goToSignUpWithEmailScreen() {
        navigateToFragment(new SignUpFragment());
    }

    @Override
    public void goToLoginCredentialScreen() {
        navigateToFragment(new SignInFragment());
    }

    @Override
    public void goToSSOLoginScreen() {
        Intent intent = new Intent(this, GoogleSignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void enterApp() {
        updateUI(FirebaseAuth.getInstance().getCurrentUser());
    }

    @Override
    public void forgotPassword() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onInfoClicked() {

    }

    @Override
    public void navigateToFragment(@NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.entry_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }

    private void updateUI(@Nullable FirebaseUser user) {
        if (user != null) {
            enterApplication(user);
        }
    }

    private void enterApplication(@NonNull FirebaseUser user) {
        Intent intent = new Intent(this, FeedActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra(Keys.LOGIN_INFO_KEY, user);
        startActivity(intent);
    }
}
