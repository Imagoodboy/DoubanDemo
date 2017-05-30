package com.example.mt.doubandemo;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mt.doubandemo.common.PagerAdapter;
import com.example.mt.doubandemo.movie.ComingMoviesFragment;
import com.example.mt.doubandemo.movie.HotMoviesFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DebugMovie";

    private TabLayout tab;
    private ViewPager pager;
    private List<String> mtitles;
    private List<Fragment> mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        initViews();
        initData();
        //将ViewPager与Fragment绑定在一起
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager(),mtitles,mFragments));
        //绑定tab和ViewPager绑定
        tab.setupWithViewPager(pager);
    }
    private void initData(){
        mtitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        mtitles.add(getString(R.string.tab_title1));
        mtitles.add(getString(R.string.tab_title2));
        mFragments.add(new HotMoviesFragment());
        mFragments.add(new ComingMoviesFragment());
    }
    private void initViews(){
        this.pager = (ViewPager)findViewById(R.id.pager);
        this.tab = (TabLayout)findViewById(R.id.tab);
    }
}
