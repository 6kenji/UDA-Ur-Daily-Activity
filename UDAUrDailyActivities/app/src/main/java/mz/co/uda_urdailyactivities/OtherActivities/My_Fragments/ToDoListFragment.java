package mz.co.uda_urdailyactivities.OtherActivities.My_Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.FragmentsClasses.CreateActivity;

import com.google.firebase.auth.FirebaseAuth;

import mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.FragmentsClasses.EditActivity_Part1;
import mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.FragmentsClasses.SeeActivity;
import mz.co.uda_urdailyactivities.R;

public class ToDoListFragment extends Fragment {

    //// Variable declarations using OOP for Java.

    /// FireBase autentication
    private FirebaseAuth auth;

    //// Components
    private Button bt_Add, bt_Edit, bt_Remove, bt_See;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeFormWidgetAndUse(view);
    }

    //// Here I initialize the connected components to this Activity.
    private void initializeFormWidgetAndUse(View view){
        bt_Add = view.findViewById(R.id.add_Activities);
        bt_Edit = view.findViewById(R.id.edit_Activities);
        bt_See = view.findViewById(R.id.see_Activities);
        bt_Remove = view.findViewById(R.id.delete_Activities);

        bt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createActivity(view);
            }
        });

        bt_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editActivity(v);
            }
        });

        bt_See.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeActivity(v);
            }
        });

        bt_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeActivity(v);
            }
        });
    }

    //// Some logic down here, hehehehe!

    ////1. FireBase Connection
    private void makeConnection(){
        auth = FirebaseAuth.getInstance();
    }

    ////2. Creating a new activity
    private void createActivity(View view){
        startActivity(new Intent(getContext(), CreateActivity.class));
    }

    ////3. Editing a new activity
    private void editActivity(View v) {
        startActivity(new Intent(getContext(), EditActivity_Part1.class));
    }

    ////4. Creating a new activity
    private void removeActivity(View v) {
    }

    ////5. Getting all your activities
    private void seeActivity(View v) {
        startActivity(new Intent(getContext(), SeeActivity.class));
    }
}