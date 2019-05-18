package com.example.neeraj.blackerspro.signUp;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.neeraj.blackerspro.R;

public class SingUpMainActivity extends AppCompatActivity {

    TabLayout tablayoutTL;
    AppBarLayout appbarABL;
    ViewPager viewpagerVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sign-Up");
        setContentView(R.layout.sign_up_activity_main);
        tablayoutTL = findViewById(R.id.tablayoutTL);
        appbarABL = findViewById(R.id.appbarABL);
        viewpagerVP = findViewById(R.id.viewpagerVP);

        SingUpViewPagerAdapter adapter = new SingUpViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new SingUpCollegeStudent_Fragment(), "College Student");
        adapter.AddFragment(new SingUpCollegeStaff_Fragment(), "College Staff");
        viewpagerVP.setAdapter(adapter);

        tablayoutTL.setupWithViewPager(viewpagerVP);
    }
}
