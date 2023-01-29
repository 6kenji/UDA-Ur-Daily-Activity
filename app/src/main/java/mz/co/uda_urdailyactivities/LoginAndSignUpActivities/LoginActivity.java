package mz.co.uda_urdailyactivities.LoginAndSignUpActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import mz.co.uda_urdailyactivities.OtherActivities.MenuActivity;
import mz.co.uda_urdailyactivities.R;

public class LoginActivity extends AppCompatActivity {

    //// Variable declarations using OOP for Java.

    /// FireBase autentication
    private FirebaseAuth auth;

    //// Components
    EditText ed_email_input, ed_password_input;
    Button bt_btn_signin;
    TextView tx_login_text_redirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeFormWidgetAndUse();
    }

    //// Here I initialize the connected components to this Activity.
    private void initializeFormWidgetAndUse(){
        makeConnection();
        ed_email_input = findViewById(R.id.email_input);
        ed_password_input = findViewById(R.id.password_input);

        tx_login_text_redirection = findViewById(R.id.welcome_text);

        bt_btn_signin = findViewById(R.id.btn_getin);

        bt_btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login(view);
            }
        });

        tx_login_text_redirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp(v);
            }
        });
    }

    //// Some logic down here, hehehehe!

    ////1. FireBase Connection
    private void makeConnection(){
        auth = FirebaseAuth.getInstance();
    }

    ////2. Login
    private void Login(View view){
        String user_email = ed_email_input.getText().toString().trim();
        String user_password = ed_password_input.getText().toString().trim();

        //// Verify if all the fields are filled
        if (user_email.isEmpty())
            Toast.makeText(this, "Por favor, preencha seu e-mail para prosseguir. ", Toast.LENGTH_SHORT).show();
        else if (user_password.isEmpty())
            Toast.makeText(this, "Por favor, insira a sua senha para prosseguir. ", Toast.LENGTH_SHORT).show();
        else {

            //// Verification
            if(Patterns.EMAIL_ADDRESS.matcher(user_email).matches()){

                auth.signInWithEmailAndPassword(user_email, user_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro no login, credeenciais erradas ou verifique sua conexão e tente novamente!", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Insira um e-mail válido. ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //// SignUp redirection text
    private void goToSignUp(View view){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}