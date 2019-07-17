package com.homecook.android.app.auth.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @author rohansaigaonkar
 */

public class SignInPresenter implements SignInContract.Presenter {
    private static final String TAG = SignInPresenter.class.getSimpleName();

    @NonNull private SignInContract.View view;
    @NonNull private FirebaseAuth mAuth;

    public SignInPresenter(@NonNull SignInContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void start() {

    }
    @Override
    public void onLoginPressed(@NonNull String email, @NonNull String password) {

        if (email.isEmpty() || password.isEmpty()) {
            return;
        }
        if (((SignInFragment) view).getActivity() == null) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(((SignInFragment) view).getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            view.login();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            view.showInvalidCreds();
                        }
                    }
                });
    }

    @Override
    public void onForgotPasswordPressed() {
        view.forgotPassword();
    }
}
