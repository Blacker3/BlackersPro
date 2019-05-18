package com.example.neeraj.blackerspro.timelineWork.Attendance;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.timelineWork.messagesWroking.messageActivity;
import com.example.neeraj.blackerspro.timelineWork.tutorials.C_Language_MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Attendance_Main extends AppCompatActivity {

    private RecyclerView attendanceRV;
    private RecyclerView.Adapter adapter;
    private List<GetAttendanceData> AttendanceData;
    private FloatingActionButton floatmsg;
    String name[] ={"Neeraj singh Rautela",
            "Ankit Verma" ,"Ankit Bisht",
            "Ankit Bhuguna","Vishu Saini",
            "Govind singh","Vikas kukreti "
            , " Ayush Rawat","Monika Rana",
            " Sonali Maurya", "Ajeet Rawat"};
    int image[] = {R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user,R.drawable.user};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance__show_rv);
        attendanceRV = findViewById(R.id.show_attedance_RV);
        floatmsg = findViewById(R.id.floatmsgBT);
        attendanceRV.setHasFixedSize(true);
        attendanceRV.setLayoutManager(new LinearLayoutManager(this));

        AttendanceData = new ArrayList<>();
        for(int i =0 ;i<name.length;i++){

            GetAttendanceData getAttendanceData = new GetAttendanceData(name[i],image[i]);
            AttendanceData.add(getAttendanceData);
        }
        floatmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), messageActivity.class);
                startActivity(intent);
            }
        });

        adapter = new AttendaceAdapter(AttendanceData,this);
        attendanceRV.setAdapter(adapter);

    }
}
