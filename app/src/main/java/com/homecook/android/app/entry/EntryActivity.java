package com.homecook.android.app.entry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.homecook.android.app.R;
import com.homecook.android.app.common.Keys;
import com.homecook.android.app.common.MainActivity;
import com.homecook.android.app.feed.FeedActivity;
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

    private String TAG = this.getClass().getSimpleName();

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

        navigateToScreen(new EntryFragment());
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
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
    public void signIn(@NonNull String email, @NonNull String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(EntryActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void signUp(@NonNull String email, @NonNull String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(EntryActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void sendPasswordReset() {
        //todo: send password reset
    }

    @Override
    public void forgotPassword() {
        navigateToScreen(new ForgotPasswordFragment());
    }

    @Override
    public void onInfoClicked() {

    }

    private void navigateToScreen(@NonNull Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.entry_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
    private void updateUI(@Nullable FirebaseUser user) {
        if (user != null) {
            enterApplication(user);
        }
    }
    private void enterApplication(@NonNull FirebaseUser user) {
        Intent intent = new Intent(this, FeedActivity.class);
        intent.putExtra(Keys.LOGIN_INFO_KEY, user);
        startActivity(intent);
    }
}
