package com.homecook.android.app.entry;

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
public class EntryFragment extends Fragment {
    private Button googleSignInButton;
    private Button emailSignInButton;
    private TextView loginButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credentials, container, false);

        googleSignInButton = view.findViewById(R.id.signup_google);
        emailSignInButton = view.findViewById(R.id.signup_email);
        loginButton = view.findViewById(R.id.sign_in_account_exists);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: auth with google
            }
        });

        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: go to email sign up
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: go to login page
            }
        });

        return view;
    }
}
