package com.example.neeraj.blackerspro.signUp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;

import com.example.neeraj.blackerspro.addusers.AddHodMainActivity;
import com.example.neeraj.blackerspro.timelineWork.TimelineMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {

    private EditText login_emailET, login_passET;
    private Button loginBT;
    private TextView loginTV, forget_passwordTV;
    private String email, password;
    private ProgressBar progressbar;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.loginform);
        findInit();
        firebaseUser = firebaseAuth.getCurrentUser();

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();


            }
        });


        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SingUpMainActivity.class);
                startActivity(intent);

            }
        });

        forget_passwordTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassword();

            }
        });

        if(firebaseUser!=null){

            finish();
            Intent intent = new Intent(Login.this, TimelineMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    //Intialiazing fields
    protected void findInit() {


        login_emailET = findViewById(R.id.login_emailET);
        login_passET = findViewById(R.id.login_passET);
        loginBT = findViewById(R.id.loginBT);
        loginTV = findViewById(R.id.loginTV);
        progressbar = findViewById(R.id.progressbar);
        forget_passwordTV = findViewById(R.id.forget_passwordTV);

    }


    protected void validateFields() {

        // Getting text from edit text
        email = login_emailET.getText().toString().trim();
        password = login_passET.getText().toString().trim();
        OnvisibilityProgressBar();
        //validation

        if (email.isEmpty()) {
            login_emailET.setError("Email is required");
            login_emailET.requestFocus();
            OnvisiblityBtn();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_emailET.setError("Please enter a valid email");
            login_emailET.requestFocus();
            OnvisiblityBtn();
            return;
        }
        if (password.isEmpty()) {
            login_passET.setError("Please enter password");
            login_passET.requestFocus();
            OnvisiblityBtn();
            return;
        }

        // login working

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(Login.this, "Welcome to Gpd Diary....", Toast.LENGTH_SHORT).show();

                    finish();
                    Intent intent = new Intent(Login.this, Verification.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else {
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    OnvisiblityBtn();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                OnvisiblityBtn();
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    //Progress bar visibilty methods
    private void OnvisiblityBtn() {
        progressbar.setVisibility(View.GONE);
        loginBT.setVisibility(View.VISIBLE);

    }

    private void OnvisibilityProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
        loginBT.setVisibility(View.GONE);
    }


    // Forget password working

    private void forgetPassword() {
        email = login_emailET.getText().toString().trim();
        OnvisibilityProgressBar();
        if (email.isEmpty()) {
            login_emailET.setError("Email is required");
            login_emailET.requestFocus();
            OnvisiblityBtn();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_emailET.setError("Please enter a valid email");
            login_emailET.requestFocus();
            OnvisiblityBtn();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    OnvisiblityBtn();
                    Toast.makeText(Login.this, "Reset password email is sent to your email account....", Toast.LENGTH_SHORT).show();
                } else {
                    OnvisiblityBtn();
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}


