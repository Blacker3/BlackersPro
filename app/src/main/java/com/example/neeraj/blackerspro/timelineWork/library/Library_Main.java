package com.example.neeraj.blackerspro.timelineWork.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.timelineWork.Attendance.AttendaceAdapter;
import com.example.neeraj.blackerspro.timelineWork.Attendance.GetAttendanceData;
import com.example.neeraj.blackerspro.timelineWork.messagesWroking.messageActivity;
import com.example.neeraj.blackerspro.timelineWork.tutorials.C_Language_MainActivity;

import java.util.ArrayList;
import java.util.List;

public class Library_Main extends AppCompatActivity {

    private RecyclerView libraryRV;
    private RecyclerView.Adapter adapter;
    private List<GetLibraryData> LibraryData;
    FloatingActionButton floatmsgBT;
    String bookname[] ={"Let us C", "Let us C++" ,
            "Multimedia"
            ,"Mobile computing","Maths",
            "Chameistry"};
    String bookauthor[] ={"E bala Guruswami", "E bala Guruswami" ,
            "Kumkum Garg","Santosh Sharma",
            "Sarthak publication","Sarthak publication"};
    int image[] = {R.drawable.library,R.drawable.library,
            R.drawable.library,R.drawable.library,
            R.drawable.library,R.drawable.library};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library__show_rv);
        libraryRV = findViewById(R.id.show_library_RV);
        floatmsgBT = findViewById(R.id.floatmsgBT);
        libraryRV.setHasFixedSize(true);
        libraryRV.setLayoutManager(new LinearLayoutManager(this));

        LibraryData = new ArrayList<>();
        for(int i =0 ;i<bookname.length;i++){

            GetLibraryData getLibraryData= new GetLibraryData(bookname[i],bookauthor[i],image[i]);
            LibraryData.add(getLibraryData);
        }

        floatmsgBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), messageActivity.class);
                startActivity(intent);
            }
        });


        adapter = new LibraryAdapter(LibraryData,this);
        libraryRV.setAdapter(adapter);

    }
}
