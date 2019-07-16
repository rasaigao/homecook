package com.homecook.android.app.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.homecook.android.app.R;

/**
 * @author rohansaigaonkar
 */
public class SignInFragment extends Fragment implements SignInContract.View {
    public interface Callback {
        /**
         * Callback for going to sign login
         */
        void signIn(@NonNull String email, @NonNull String password);

        void forgotPassword();
    }

    private Callback callback;
    private SignInContract.Presenter presenter;
    private EditText emailField;
    private EditText passwordField;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignInFragment.Callback) {
            callback = (SignInFragment.Callback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Callback");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        presenter = new SignInPresenter(this);
        emailField = view.findViewById(R.id.email_signin);
        passwordField = view.findViewById(R.id.password_signin);
        Button loginButton = view.findViewById(R.id.login_button);
        TextView forgotPassword = view.findViewById(R.id.forgot_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginPressed();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onForgotPasswordPressed();
            }
        });
        return view;
    }

    @Override
    public void login() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        callback.signIn(email, password);
    }

    @Override
    public void setPresenter(@NonNull SignInContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void forgotPassword() {
        callback.forgotPassword();
    }
}
