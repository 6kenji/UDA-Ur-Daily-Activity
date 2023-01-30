package mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.FragmentsClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import mz.co.uda_urdailyactivities.Objects.User;
import mz.co.uda_urdailyactivities.Objects.User_Activity;
import mz.co.uda_urdailyactivities.R;

public class CreateActivity extends AppCompatActivity {

    //// Variable declarations using OOP for Java.

    //// Components
    EditText ed_name, ed_periodicity, ed_initdate, ed_enddate;
    Button bt_AddActivity;

    //// Other
    String actual_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initializeFormWidgetAndUse();
    }

    //// Here I initialize the connected components to this Activity.
    private void initializeFormWidgetAndUse(){
        bt_AddActivity = findViewById(R.id.btn_createActivity);
        ed_name = findViewById(R.id.ed_activityName);
        ed_periodicity = findViewById(R.id.ed_activityPeriodicity);
        ed_initdate = findViewById(R.id.ed_initial_date);
        ed_enddate = findViewById(R.id.ed_end_date);

        bt_AddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createActivity();
            }
        });
    }

    ////User Insertion to database
    private void createActivity(){
        User_Activity user_activity = new User_Activity(
                Date.valueOf(ed_initdate.getText().toString()),
                Date.valueOf(ed_enddate.getText().toString()),
                ed_name.getText().toString(),
                Integer.valueOf(ed_periodicity.getText().toString())
        );

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> my_activities = new HashMap<>();
        my_activities.put("Nome", user_activity.getName());
        my_activities.put("Data de inicio", user_activity.getInitial_date());
        my_activities.put("Data de fim", user_activity.getEnd_date());
        my_activities.put("Periodicidade", user_activity.getPeriodicity());

        actual_activity = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        DocumentReference documentReference = db.collection("Activity").document(actual_activity);

        documentReference.set(my_activities).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db_Success","Atividade criada com sucesso. xD");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_Error","Atividade nao criado com sucesso. xD"+e.toString());

            }
        });
    }


}