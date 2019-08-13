package com.homecook.android.app.auth.login.forgot_password;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @author rohansaigaonkar
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {
    private static final String TAG = ForgotPasswordPresenter.class.getSimpleName();
    @NonNull private ForgotPasswordContract.View view;

    public ForgotPasswordPresenter(@NonNull ForgotPasswordContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }
    @Override
    public void start() {

    }

    @Override
    public void onSendPasswordResetPressed(@NonNull String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            view.onPasswordEmailSent();
                        } else {
                            view.showInvalidEmail();
                            Log.w(TAG, "Could not send password recovery email");
                        }
                    }
                });    }
}
