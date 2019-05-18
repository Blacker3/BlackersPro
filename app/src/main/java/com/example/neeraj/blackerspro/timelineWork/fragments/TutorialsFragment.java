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
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.addusers.AddHodMainActivity;
import com.example.neeraj.blackerspro.timelineWork.Activities.InformationTutorials;


public class TutorialsFragment extends Fragment {

    View view;
    private CardView electronicstutorialsCardView, informationtutorialsCardView, computertechtutorialsCardView, mechautotutorialsCardView,mechprotutorialsCardView,civiltutorialsCardView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tutorials_activity, container, false);
        findInit();
        onClickWorking();
        return view;

    }


    private void findInit() {
        electronicstutorialsCardView = view.findViewById(R.id.electronicstutorialsCardView);
        informationtutorialsCardView = view.findViewById(R.id.informationtutorialsCardView);
        computertechtutorialsCardView = view.findViewById(R.id.computertechtutorialsCardView);
        mechautotutorialsCardView = view.findViewById(R.id.mechautotutorialsCardView);
        mechprotutorialsCardView = view.findViewById(R.id.mechprotutorialsCardView);
        civiltutorialsCardView = view.findViewById(R.id.civiltutorialsCardView);
    }


    private void onClickWorking() {
        electronicstutorialsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Team working on it", Toast.LENGTH_SHORT).show();

              //  Intent intent = new Intent(getActivity(), InformationTutorials.class);
                //startActivity(intent);


            }
        });
        informationtutorialsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InformationTutorials.class);
                startActivity(intent);


            }
        });
        computertechtutorialsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InformationTutorials.class);
                startActivity(intent);

            }
        });
        civiltutorialsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Team working on it", Toast.LENGTH_SHORT).show();



            }
        });
        mechprotutorialsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Team working on it", Toast.LENGTH_SHORT).show();



            }
        });
        mechautotutorialsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Team working on it", Toast.LENGTH_SHORT).show();


            }
        });
    }

}




