package com.homecook.android.app.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.homecook.android.app.R;
import com.homecook.android.app.entry.EntryActivity;

/**
 * @author rohansaigaonkar
 */
public class SignUpFragment extends Fragment implements SignUpContract.View {

    private static final String TAG = SignUpFragment.class.getSimpleName();

    public interface Callback extends LoginCallback {

    }

    private Callback callback;
    private SignUpContract.Presenter presenter;
    private FirebaseAuth mAuth;
    private EditText emailField;
    private EditText passwordField;
    private EditText firstNameField;
    private EditText lastNameField;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignUpFragment.Callback) {
            callback = (SignUpFragment.Callback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Callback");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        presenter = new SignUpPresenter(this);
        passwordField = view.findViewById(R.id.password_signup);
        emailField = view.findViewById(R.id.email_signup);
        firstNameField = view.findViewById(R.id.first_name_signup);
        lastNameField = view.findViewById(R.id.last_name_signup);
        mAuth = FirebaseAuth.getInstance();

        Button signUpButton = view.findViewById(R.id.register_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpPressed();
            }
        });
        return view;
    }

    @Override
    public void signUp() {
        final String email = emailField.getText().toString();
        final String password = passwordField.getText().toString();
        final String firstName = firstNameField.getText().toString();
        final String lastName = lastNameField.getText().toString();


    }

    @Override
    public void setPresenter(@NonNull SignUpContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
