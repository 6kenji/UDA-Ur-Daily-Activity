package mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.FragmentsClasses;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
/// import com.google.firebase.database.FirebaseStorage;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import mz.co.uda_urdailyactivities.Objects.User_Activity;
import mz.co.uda_urdailyactivities.OtherActivities.Models.MenuActivity;
import mz.co.uda_urdailyactivities.OtherActivities.Models.MyAlarmReciever;
import mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.ToDoListFragment;
import mz.co.uda_urdailyactivities.R;
import mz.co.uda_urdailyactivities.databinding.ActivityCreateBinding;

public class CreateActivity extends AppCompatActivity {

    //// Variable declarations using OOP for Java.
    private FirebaseDatabase database;
    private DatabaseReference reference;

    //// Components
    private EditText ed_name, ed_description;
    private TextView setTime;
    private Button bt_AddActivity;
    private Switch my_switch;
    private LinearLayout enable_dates;

    private MaterialTimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    //// Other
    User_Activity user_activity;
    int value = 0;
    String object;
    String activityID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initializeFormWidgetAndUse();
    }

    //// Here I initialize the connected components to this Activity.
    private void initializeFormWidgetAndUse(){
        bt_AddActivity = findViewById(R.id.btn_createActivity);

        enable_dates = findViewById(R.id.days_layout);

        my_switch = findViewById(R.id.repeat_switch);

        ed_name = findViewById(R.id.ed_activityName);
        ed_description = findViewById(R.id.ed_activityDescription);

        setTime = findViewById(R.id.set_Time);

        bt_AddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createActivity();
            }
        });

        my_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (my_switch.isChecked()){
                    enable_dates.setVisibility(View.VISIBLE);
                } else {
                    enable_dates.setVisibility(View.INVISIBLE);
                }
            }
        });

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H).setHour(12).setMinute(0).setTitleText("Defina a hora").build();
                timePicker.show(getSupportFragmentManager(), "U.D.A notification");
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (timePicker.getHour() > 12) {
                            setTime.setText(
                                    String.format("%02d", (timePicker.getHour() - 12)) + ":" +
                                            String.format("%02d", timePicker.getMinute())+"PM");
                        } else {
                            setTime.setText(timePicker.getHour() + ":" + timePicker.getMinute()+"PM");
                        }
                        calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                    }

                });
            }
        });
    }

    private void notificationCreation(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = ed_name.getText().toString();
            String desc = ed_description.getText().toString();
            int imp = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("U.D.A notification", name, imp);
            channel.setDescription(desc);


            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    ///////////////////////////////////////////////// --- MY LOGIC --- /////////////////////////////////////////////////

    ////User Insertion to database
    private void createActivity(){
        saveData();
    }

    ////1. FireBase Connection

    ////2. 365 Or 366 Years
    private boolean yearValidation(String year){

        ////Local variable to make a test verification
        boolean value = false;

        try{
            //// Here if the int division with 4 equals to zero the year has 366 days
            if((Integer.parseInt(year) % 4) == 0){
                return value;
            }

        }catch(NumberFormatException ex){
            Toast.makeText(CreateActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return value;
    }


    ////3. Saving data
    private void saveData() {

        if (ed_name.getText().toString().isEmpty() || ed_description.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha todos campos", Toast.LENGTH_SHORT).show();
        } else {

            try {
                String activity_name = ed_name.getText().toString();
                String description = ed_description.getText().toString();

                activityID = activity_name;

                user_activity = new User_Activity(activity_name, activityID, description);

                reference = FirebaseDatabase.getInstance().getReference().child("user_activities");
                reference.push().setValue(user_activity);

                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent in = new Intent(CreateActivity.this, MyAlarmReciever.class);
                pendingIntent = PendingIntent.getBroadcast(CreateActivity.this, 0, in, 0);

                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                Toast.makeText(getApplicationContext(), "Atividade criada com sucesso! ", Toast.LENGTH_SHORT).show();
                finish();
                ed_name.setText("");
                ed_description.setText("");
                startActivity(new Intent(CreateActivity.this, MenuActivity.class));

            } catch (Exception exception) {
                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

}