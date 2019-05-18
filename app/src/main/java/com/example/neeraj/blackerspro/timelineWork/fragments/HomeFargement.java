package com.example.neeraj.blackerspro.timelineWork.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.addusers.AddHodMainActivity;
import com.example.neeraj.blackerspro.timelineWork.Activities.AddUser;
import com.example.neeraj.blackerspro.timelineWork.Attendance.Attendance_Main;
import com.example.neeraj.blackerspro.timelineWork.library.Library_Main;


public class HomeFargement extends Fragment {

    View view;
    CardView attendaceCardView, libraryCardView, storeCardView, placementcellCardView, timetableCardView, adduserCardView;
    FrameLayout fagment_container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homelayout, container, false);
        findInit();
        onClickWorking();
        return view;

    }


    private void findInit() {
        attendaceCardView = view.findViewById(R.id.attendanceCardView);
        libraryCardView = view.findViewById(R.id.libraryCardView);
        storeCardView = view.findViewById(R.id.storeCardView);
        placementcellCardView = view.findViewById(R.id.placementcellCardView);
        timetableCardView = view.findViewById(R.id.timetableCardView);
        adduserCardView = view.findViewById(R.id.adduserCardView);
        fagment_container = view.findViewById(R.id.fargment_container);

    }


    private void onClickWorking() {
        attendaceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Attendance_Main.class);
                startActivity(intent);
            }
        });
        libraryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Library_Main.class);
                startActivity(intent);


            }
        });
        storeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Team working on it", Toast.LENGTH_SHORT).show();

            }
        });
        placementcellCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Team working on it", Toast.LENGTH_SHORT).show();


            }
        });

        adduserCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddUser.class);
                startActivity(intent);


            }
        });

    }

}




