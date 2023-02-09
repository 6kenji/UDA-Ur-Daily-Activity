package mz.co.uda_urdailyactivities.OtherActivities.My_Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mz.co.uda_urdailyactivities.LoginAndSignUpActivities.LoginActivity;
import mz.co.uda_urdailyactivities.OtherActivities.Models.MenuActivity;
import mz.co.uda_urdailyactivities.R;


public class Settings_Fragment extends Fragment {

    //// Variable declarations using OOP for Java.

    //// Components
    private TextView txtTextMe;
    public Switch theme;
    private CheckBox cb_remember_Me;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeFormWidgetAndUse(view);

    }

    //// Here I initialize the connected components to this Activity.
    private void initializeFormWidgetAndUse(View v){

        theme = v.findViewById(R.id.darkTheme);

        txtTextMe = v.findViewById(R.id.text_me);

        cb_remember_Me = v.findViewById(R.id.cb_alwayslogin);

        txtTextMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// Implementation in the future
            }
        });

        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (theme.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });

    }

}