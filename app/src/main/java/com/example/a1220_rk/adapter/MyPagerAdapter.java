package com.example.a1220_rk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a1220_rk.view.MainFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {
    String[] titles;



    public MyPagerAdapter(FragmentManager supportFragmentManager, String[] titles) {
        super(supportFragmentManager);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return MainFragment.getinstance(titles[i]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
