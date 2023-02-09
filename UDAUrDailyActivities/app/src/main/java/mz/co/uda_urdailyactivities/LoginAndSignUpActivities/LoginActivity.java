package mz.co.uda_urdailyactivities.LoginAndSignUpActivities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Timer;
import java.util.TimerTask;

import mz.co.uda_urdailyactivities.OtherActivities.Models.MenuActivity;
import mz.co.uda_urdailyactivities.OtherActivities.My_Fragments.Settings_Fragment;
import mz.co.uda_urdailyactivities.R;

public class LoginActivity extends AppCompatActivity {

    //// Variable declarations using OOP for Java.

    /// FireBase autentication
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;

    //// Components
    private EditText ed_email_input, ed_password_input;
    private ProgressBar progressBar;
    private Button bt_btn_signin;
    private TextView tx_login_text_redirection;

    //// Other
    private int counter = 0;
    private Timer timer;

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

        progressBar = findViewById(R.id.my_progressbar);

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

        timer = new Timer();

        firebaseFirestore = FirebaseFirestore.getInstance();

        //// Verify if all the fields are filled
        if (user_email.isEmpty())
            Toast.makeText(this, "Por favor, preencha seu e-mail para prosseguir. ", Toast.LENGTH_SHORT).show();
        else if (user_password.isEmpty())
            Toast.makeText(this, "Por favor, insira a sua senha para prosseguir. ", Toast.LENGTH_SHORT).show();
        else {
            //// Verification
            gettingUser(user_email, user_password);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
            finish();
        }
    }

    //// SignUp redirection text
    private void goToSignUp(View view){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void gettingUser(String user_email, String user_password){

        DocumentReference documentReference = firebaseFirestore.collection("Users").document("user_data");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){

                    progressBar.setVisibility(View.VISIBLE);
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            counter ++;
                            progressBar.setProgress(counter);

                            if(counter == 100){
                                timer.cancel();
                            }
                        }
                    };
                    timer.schedule(timerTask, 0, 100);

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
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Erro no login, linha desconhecida!", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Erro no login, "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}