package com.example.neeraj.blackerspro.singUp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;




public class Login extends AppCompatActivity {

    EditText login_emailET, login_passET;
    Button loginBT;
    TextView loginTV;
    String email, password;
    String regEx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    boolean validateEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);
        findInit();


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


    }


    protected void validateFields() {

        // Getting text from edit text
        email = login_emailET.getText().toString();
        password = login_emailET.getText().toString();

        //validation

        if (email.isEmpty()) {
            login_emailET.setError("Email is required");
            login_emailET.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_emailET.setError("Please enter a valid email");
            login_emailET.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            login_passET.setError("Please enter password");
            login_passET.requestFocus();
            return;
        }

        Toast.makeText(Login.this, "Welcome to Gpd Diary....", Toast.LENGTH_SHORT).show();
    }


}


