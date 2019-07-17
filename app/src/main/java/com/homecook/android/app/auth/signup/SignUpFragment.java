package com.homecook.android.app.auth.signup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.homecook.android.app.R;
import com.homecook.android.app.auth.common.LoginCallback;

/**
 * @author rohansaigaonkar
 */
public class SignUpFragment extends Fragment implements SignUpContract.View {

    private static final String TAG = SignUpFragment.class.getSimpleName();

    public interface Callback extends LoginCallback {

    }

    private Callback callback;
    private SignUpContract.Presenter presenter;
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

        Button signUpButton = view.findViewById(R.id.register_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpPressed(
                        emailField.getText().toString(),
                        passwordField.getText().toString(),
                        firstNameField.getText().toString(),
                        lastNameField.getText().toString()
                );
            }
        });
        return view;
    }

    @Override
    public void signUp() {
        callback.enterApp();
    }

    @Override
    public void setPresenter(@NonNull SignUpContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
