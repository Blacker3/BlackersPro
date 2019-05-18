package com.example.neeraj.blackerspro.timelineWork.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.addusers.AddHodMainActivity;
import com.example.neeraj.blackerspro.addusers.collegeStaff.AddCollegeStaffMainActivity;
import com.example.neeraj.blackerspro.addusers.student.StudentMain;

public class AddUser extends AppCompatActivity {
   private CardView addhodCardView, addstudentCardView, addfacultyCardView, addcollegestaffCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add-Users");
        setContentView(R.layout.adduserfragment);
        findInit();
        onClickWorking();
       }

    private void findInit() {
        addhodCardView = findViewById(R.id.addhodCardView);
        addstudentCardView = findViewById(R.id.addstudentCardView);
        addcollegestaffCardView = findViewById(R.id.addcollegestaffCardView);
        addfacultyCardView = findViewById(R.id.addfacultyCardView);
    }



    private void onClickWorking() {
        addhodCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AddHodMainActivity.class);
                startActivity(intent);

            }
        });
        addfacultyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCollegeStaffMainActivity.class);
                startActivity(intent);

            }
        });
        addstudentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentMain.class);
                startActivity(intent);

            }
        });
        addcollegestaffCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCollegeStaffMainActivity.class);
                startActivity(intent);



            }
        });
    }

}
