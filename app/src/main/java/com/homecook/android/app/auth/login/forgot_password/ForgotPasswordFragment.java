package com.homecook.android.app.auth.login.forgot_password;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.homecook.android.app.R;

/**
 * @author rohansaigaonkar
 */
public class ForgotPasswordFragment extends Fragment implements ForgotPasswordContract.View {
    public interface Callback {
        /**
         * Callback for sending password reset
         */
        void sendPasswordReset();
    }
    private Callback callback;
    private ForgotPasswordContract.Presenter presenter;

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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        presenter = new ForgotPasswordPresenter(this);
        Button passwordResetButton = view.findViewById(R.id.register_button);

        passwordResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSendPasswordResetPressed();
            }
        });
        return view;
    }

    @Override
    public void sendPasswordReset() {
        callback.sendPasswordReset();
    }

    @Override
    public void setPresenter(@NonNull ForgotPasswordContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
