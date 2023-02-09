package mz.co.uda_urdailyactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import mz.co.uda_urdailyactivities.LoginAndSignUpActivities.LoginActivity;
import mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.Settings_Fragment;

public class MainActivity extends AppCompatActivity {

    Button welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.btn_getin);

        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

    }
}