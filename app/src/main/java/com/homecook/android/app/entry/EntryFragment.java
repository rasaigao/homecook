package com.homecook.android.app.entry;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.homecook.android.app.R;

/**
 * @author rohansaigaonkar
 */
public class EntryFragment extends Fragment implements EntryContract.View {

    public interface Callback {

        /**
         * Callback for onBackButton pressed
         */
        void exit();

        /**
         * Callback for going to sign up with email pressed
         */
        void goToSignUpWithEmailScreen();

        /**
         * Callback for navigating to the enter credentials screen
         */
        void goToLoginCredentialScreen();

        /**
         * Callback for navigating to the SSO login screen
         */
        void goToSSOLoginScreen();
    }

    private EntryContract.Presenter presenter;
    private Callback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback) {
            callback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Callback");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credentials, container, false);
        presenter =  new EntryPresenter(this);
        Button googleSignInButton = view.findViewById(R.id.signup_google);
        Button emailSignInButton = view.findViewById(R.id.signup_email);
        TextView loginButton = view.findViewById(R.id.sign_in_account_exists);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpWithGoogleClicked();
            }
        });

        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpWithEmailClicked();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAccountExistsClicked();
            }
        });

        return view;
    }

    @Override
    public void goToSSOGoogleScreen() {
        callback.goToSSOLoginScreen();
    }

    @Override
    public void goToEmailSignUpScreen() {
        callback.goToSignUpWithEmailScreen();
    }

    @Override
    public void goToLoginScreen() {
        callback.goToLoginCredentialScreen();
    }

    @Override
    public void setPresenter(@NonNull EntryContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
