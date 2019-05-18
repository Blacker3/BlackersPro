package com.example.neeraj.blackerspro.timelineWork;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.signUp.Login;
import com.example.neeraj.blackerspro.signUp.SingUpCollegeStaff_Fragment;
import com.example.neeraj.blackerspro.signUp.SingUpCollegeStudent_Fragment;
import com.example.neeraj.blackerspro.signUp.SingUpViewPagerAdapter;
import com.example.neeraj.blackerspro.timelineWork.fragments.HomeFargement;
import com.example.neeraj.blackerspro.timelineWork.fragments.TimelineHomeFargement;
import com.example.neeraj.blackerspro.timelineWork.fragments.TutorialsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class TimelineMainActivity extends AppCompatActivity {

    private TabLayout tablayoutTL;
    private AppBarLayout appbarABL;
    private ViewPager viewpagerVP;
    private int iconarr[] = {R.drawable.ic_question, R.drawable.ic_home, R.drawable.ic_school};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gpd Diary");
        setContentView(R.layout.sign_up_activity_main);
        tablayoutTL = findViewById(R.id.tablayoutTL);
        appbarABL = findViewById(R.id.appbarABL);
        viewpagerVP = findViewById(R.id.viewpagerVP);

        SingUpViewPagerAdapter adapter = new SingUpViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new TimelineHomeFargement(), "");
        adapter.AddFragment(new HomeFargement(), "");

        adapter.AddFragment(new TutorialsFragment(), "");


        viewpagerVP.setAdapter(adapter);


        tablayoutTL.setupWithViewPager(viewpagerVP);
        for (int i = 0; i < 3; i++) {
            tablayoutTL.getTabAt(i).setIcon(iconarr[i]);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addhod, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout_hod_details:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                finish();
                return true;
            default:
                Toast.makeText(this, "Something went worng!", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }
}
