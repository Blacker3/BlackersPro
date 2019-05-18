package com.example.neeraj.blackerspro.timelineWork.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.timelineWork.tutorials.C_Language_MainActivity;

public class InformationTutorials extends AppCompatActivity {
   private CardView cprogrammingCardView, oopsCardView, javaCardView, csharpView
           ,datastructureCardView,microprocessorCardView,opticalCardView,microwaveCardView,microsysCardView,advancedCardView;


     private String branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        branch = getIntent().getStringExtra("branch");


            setTitle("IT-Subjects");
            setContentView(R.layout.information_technology_tutorials);
        findInit();

        iTonClickWorking();

       /* else  if(branch.equalsIgnoreCase("elex_tech")){
            setTitle("Elecx-Subjects");
            setContentView(R.layout.electronics_tutorials);
            elexonClickWorking();

        }*/

       }

    private void findInit() {
        cprogrammingCardView = findViewById(R.id.cprogrammingCardView);
        oopsCardView = findViewById(R.id.oopsCardView);
        javaCardView = findViewById(R.id.javaCardView);
        csharpView = findViewById(R.id.csharpView);
        datastructureCardView = findViewById(R.id.datastructureCardView);
        microprocessorCardView = findViewById(R.id.microprocessorCardView);
        opticalCardView = findViewById(R.id.opticalCardView);
        microwaveCardView = findViewById(R.id.microprocessorCardView);
        microsysCardView = findViewById(R.id.microsysCardView);
        advancedCardView = findViewById(R.id.advancedCardView);

    }



    private void iTonClickWorking() {
        cprogrammingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","clanguage");
                startActivity(intent);


            }
        });
        oopsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","oopslanguage");
                startActivity(intent);




            }
        });
        javaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","java");
                startActivity(intent);
            }
        });
        csharpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","csharp");
                startActivity(intent);




            }
        });
        datastructureCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","datastruct");
                startActivity(intent);




            }
        });
        microprocessorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","micropro");
                startActivity(intent);




            }
        });
    }



    private void elexonClickWorking() {
        advancedCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","advancedmicro");
                startActivity(intent);


            }
        });
        microsysCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","microsys");
                startActivity(intent);


            }
        });
        microwaveCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","microwave");
                startActivity(intent);
            }
        });
        opticalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), C_Language_MainActivity.class);

                intent.putExtra("tutorial","optical");
                startActivity(intent);




            }
        });



    }

}
