package com.example.neeraj.blackerspro.singUp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SingUpViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentListTitles = new ArrayList<>();

    public SingUpViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
       return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return fragmentListTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String Title) {

        fragmentListTitles.add(Title);

        fragmentList.add(fragment);
    }

}
