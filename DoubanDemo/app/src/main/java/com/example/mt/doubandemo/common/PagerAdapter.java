package com.example.mt.doubandemo.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 2017/5/28.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private List<String> mtitle;
    private List<Fragment> mFragments = new ArrayList<>();
    public PagerAdapter(FragmentManager fm,List<String> list,List<Fragment>list2){
        super(fm);
        this.mtitle = list;
        this.mFragments = list2;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mtitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitle.get(position);
    }
}
