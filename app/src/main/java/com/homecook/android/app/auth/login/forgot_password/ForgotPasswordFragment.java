package com.homecook.android.app.auth.login.forgot_password;

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

import static android.view.View.GONE;

/**
 * @author rohansaigaonkar
 */
public class ForgotPasswordFragment extends Fragment implements ForgotPasswordContract.View {

    public interface Callback {

        void onRecoverySuccess();

    }

    private static final String TAG = ForgotPasswordFragment.class.getSimpleName();
    private ForgotPasswordContract.Presenter presenter;
    private FirebaseAuth mAuth;
    private EditText emailField;
    private TextView invalidEmailMessage;
    private Callback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ForgotPasswordFragment.Callback) {
            callback = (ForgotPasswordFragment.Callback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Callback");
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        presenter = new ForgotPasswordPresenter(this);

        emailField = view.findViewById(R.id.fpw_email);
        invalidEmailMessage = view.findViewById(R.id.fpw_invalid_email);
        final Button passwordResetButton = view.findViewById(R.id.fpw_recovery_button);

        emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordResetButton.setEnabled(s.length() > 0);
                invalidEmailMessage.setVisibility(GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString();
                presenter.onSendPasswordResetPressed(email);
            }
        });
        return view;
    }

    @Override
    public void setPresenter(@NonNull ForgotPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onPasswordEmailSent() {
        callback.onRecoverySuccess();
    }

    @Override
    public void showInvalidEmail() {
        invalidEmailMessage.setVisibility(View.VISIBLE);
    }
}
