package mz.co.uda_urdailyactivities.OtherActivities.My_Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mz.co.uda_urdailyactivities.R;


public class InfoFragment extends Fragment {

    private TextView link_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeFormWidgetAndUse(view);
    }

    //// Here I initialize the connected components to this Activity.
    private void initializeFormWidgetAndUse(View view){

        link_text = view.findViewById(R.id.link_text);

        link_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/khalidy6")));
            }
        });

    }

}