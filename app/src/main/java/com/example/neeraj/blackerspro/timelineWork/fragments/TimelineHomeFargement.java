package com.example.neeraj.blackerspro.timelineWork.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.neeraj.blackerspro.MainActivity;
import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.timelineWork.Activities.AddUser;


public class TimelineHomeFargement extends Fragment {

    View view;
    CardView govtpolyCardView, principalCardView, facilitiesCardView, courseCardView,syllabusCardView,aboutCardView;
     FrameLayout fagment_container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.timeline_home, container, false);
        findInit();
        onClickWorking();
        return view;

    }


    private void findInit() {
        govtpolyCardView = view.findViewById(R.id.govtpolyCardView);
        principalCardView = view.findViewById(R.id.principalCardView);
        facilitiesCardView = view.findViewById(R.id.facilitiesCardView);
        courseCardView = view.findViewById(R.id.courseCardView);
        syllabusCardView = view.findViewById(R.id.syllabusCardView);
        aboutCardView  = view.findViewById(R.id.aboutCardView);
        fagment_container  = view.findViewById(R.id.fargment_container);

    }


    private void onClickWorking() {
        govtpolyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("layout","govt");
                startActivity(intent);
            }
        });
        principalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("layout","principal");
                startActivity(intent);


            }
        });
        facilitiesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("layout","facilities");
                startActivity(intent);
            }
        });
        courseCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("layout","course");
                startActivity(intent);


            }
        });

        syllabusCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("layout","syllabus");
                startActivity(intent);

            }
        });
        aboutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("layout","about");
                startActivity(intent);

            }
        });

    }

}




