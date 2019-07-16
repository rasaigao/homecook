package com.homecook.android.app.login;

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
public class SignUpFragment extends Fragment implements SignUpContract.View {

    public interface Callback {
        /**
         * Callback for going to sign login
         */
        void signUp();
    }

    private Callback callback;
    private SignUpContract.Presenter presenter;

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
        callback.signUp();
    }

    @Override
    public void setPresenter(@NonNull SignUpContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
