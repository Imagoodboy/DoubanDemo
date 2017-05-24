package com.example.mt.doubandemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.mt.doubandemo.api.DoubanRetrofit;
import com.example.mt.doubandemo.common.ImageAdapter;
import com.example.mt.doubandemo.movie.GetPicInfo;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DebugMovie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        DoubanRetrofit retrofit = new DoubanRetrofit(this,gridView);
        retrofit.query();
    }
}
