package com.homecook.android.app.auth.login;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.homecook.android.app.R;
import com.homecook.android.app.auth.common.LoginCallback;

/**
 * @author rohansaigaonkar
 */
public class SignInFragment extends Fragment implements SignInContract.View {
    private static final String TAG = SignInFragment.class.getSimpleName();

    public interface Callback extends LoginCallback {
        void forgotPassword();
    }

    private Callback callback;
    private SignInContract.Presenter presenter;
    private FirebaseAuth mAuth;
    private EditText emailField;
    private EditText passwordField;
    private TextView invalidCreds;

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
        invalidCreds = view.findViewById(R.id.invalid_creds);
        mAuth = FirebaseAuth.getInstance();

        Button loginButton = view.findViewById(R.id.login_button);
        TextView forgotPassword = view.findViewById(R.id.forgot_password);

        invalidCreds.setVisibility(View.GONE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginPressed(emailField.getText().toString(), passwordField.getText().toString());
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onForgotPasswordPressed();
            }
        });
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                invalidCreds.setVisibility(View.GONE);
            }
        };
        emailField.addTextChangedListener(watcher);
        passwordField.addTextChangedListener(watcher);

        return view;
    }

    @Override
    public void login() {
        callback.enterApp();
    }

    @Override
    public void setPresenter(@NonNull SignInContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void forgotPassword() {
        callback.forgotPassword();
    }

    @Override
    public void showInvalidCreds() {
        invalidCreds.setVisibility(View.VISIBLE);
    }
}
