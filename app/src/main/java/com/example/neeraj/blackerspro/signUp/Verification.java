package com.example.neeraj.blackerspro.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;

import com.example.neeraj.blackerspro.addusers.AddHodMainActivity;
import com.example.neeraj.blackerspro.timelineWork.TimelineMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Verification extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser;
    private TextView email_verificationTV, sendingTV;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.email_verification);
        firebaseUser = firebaseAuth.getCurrentUser();
        email_verificationTV = findViewById(R.id.email_verificationTV);
        sendingTV = findViewById(R.id.sendingTV);
        progressbar = findViewById(R.id.progressbar);
        if (firebaseUser != null) {

            if (!firebaseUser.isEmailVerified()) {

                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        sendingTV.setVisibility(View.GONE);
                        progressbar.setVisibility(View.GONE);
                        email_verificationTV.setVisibility(View.VISIBLE);
                        Toast.makeText(Verification.this, "Verifiaction mail is sent to your account .......", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        email_verificationTV.setVisibility(View.INVISIBLE);
                        sendingTV.setText("Some error occured while sending email ...... ");
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(Verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            } else {

                finish();
                Intent intent = new Intent(Verification.this, TimelineMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }


        } else {
            finish();
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseUser.isEmailVerified()){
            finish();
            Intent intent = new Intent(Verification.this, TimelineMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }

    }
}
