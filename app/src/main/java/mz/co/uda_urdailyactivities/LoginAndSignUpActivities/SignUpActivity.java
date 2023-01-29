package mz.co.uda_urdailyactivities.LoginAndSignUpActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import mz.co.uda_urdailyactivities.Objects.User;
import mz.co.uda_urdailyactivities.R;

public class SignUpActivity extends AppCompatActivity {

    //// Variable declarations using OOP for Java.

    /// FireBase autentication
    private FirebaseAuth auth;

    //// Components
    EditText ed_name_input, ed_email_input, ed_password_input, ed_password_re_input;
    Button bt_btn_signup;
    TextView tx_login_text_redirection;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeFormWidgetAndUse();
    }

    //// Here I initialize the connected components to this Activity.
    private void initializeFormWidgetAndUse(){
        makeConnection();
        ed_name_input = findViewById(R.id.name_input);
        ed_email_input = findViewById(R.id.email_input);
        ed_password_input = findViewById(R.id.password_input);
        ed_password_re_input = findViewById(R.id.password_re_input);

        tx_login_text_redirection = findViewById(R.id.welcome_text);

        bt_btn_signup = findViewById(R.id.btn_getin);

        bt_btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(view);
            }
        });

        tx_login_text_redirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin(v);
            }
        });
    }

    //// Some logic down here, hehehehe!

    ////1. FireBase Connection
    private void makeConnection(){
        auth = FirebaseAuth.getInstance();
    }

    ////2. SignUP
    private void signUp(View view){
        String user_name = ed_name_input.getText().toString();
        String user_email = ed_email_input.getText().toString();
        String user_password = ed_password_input.getText().toString();
        String user_password_confirm = ed_password_re_input.getText().toString();

        //// Verify if all the fields are filled

        if(user_email.isEmpty()){
            Toast.makeText(
                    getApplicationContext(),
                    "Introduza o seu e-mail.",
                    Toast.LENGTH_SHORT
            ).show();
        } else if(user_name.isEmpty() ){
            Toast.makeText(
                    getApplicationContext(),
                    "Introduza o seu nome.",
                    Toast.LENGTH_SHORT
            ).show();
        } else if (user_password.isEmpty()){
            Toast.makeText(
                    getApplicationContext(),
                    "Introduza uma senha.",
                    Toast.LENGTH_SHORT
            ).show();
        } else if(user_password_confirm.isEmpty()){
            Toast.makeText(
                    getApplicationContext(),
                    "Re-Introduza a sua senha.",
                    Toast.LENGTH_SHORT
            ).show();

        } else {
            if (user_password.equals(user_password_confirm)) {
                //// Creating a new user
                auth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUpActivity.this, "Conta criada com sucesso! ", Toast.LENGTH_SHORT).show();

                            ed_name_input.setText(""); ed_email_input.setText("");
                            ed_password_input.setText(""); ed_password_re_input.setText("");

                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Algo deu errado, tente novamente! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "As senhas devem coincidir! ",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    //// Login redirection text
    private void goToLogin(View view){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}