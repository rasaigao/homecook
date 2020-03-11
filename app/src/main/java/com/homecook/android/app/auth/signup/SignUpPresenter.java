package com.homecook.android.app.auth.signup;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.homecook.android.app.R;

/**
 * @author rohansaigaonkar
 */

public class SignUpPresenter implements SignUpContract.Presenter {
    private static final String TAG = SignUpPresenter.class.getSimpleName();
    @NonNull private SignUpContract.View view;
    @NonNull private FirebaseAuth mAuth;

    public SignUpPresenter(@NonNull SignUpContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void onSignUpPressed(@NonNull String email,
                                @NonNull String password,
                                @NonNull final String firstName,
                                @NonNull final String lastName) {
        if (email.isEmpty() || password.isEmpty()) {
            return;
        }

        if (((SignUpFragment) view).getActivity() == null) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(((SignUpFragment) view).getActivity(), new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(((SignUpFragment) view).getResources().getString(R.string.user_profile_name, firstName, lastName))
                                    .build();
                            mAuth.getCurrentUser().updateProfile(profileUpdate);
                            FirebaseUser user;
                            view.signUp();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(((SignUpFragment) view).getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
