package com.example.mt.doubandemo.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.mt.doubandemo.ItemInfoActivity;
import com.example.mt.doubandemo.MainActivity;
import com.example.mt.doubandemo.common.ImageAdapter;
import com.example.mt.doubandemo.movie.GetPicInfo;
import com.example.mt.doubandemo.movie.HotMoviesInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.mt.doubandemo.movie.HotMoviesInfo.*;
/**
 * Created by mt on 2017/5/21.
 */

public class DoubanRetrofit {

    private static final String BASE_URL = "https://api.douban.com/v2/";

    private Context mContext;
    private GridView gridView;
    private List<SubjectsBean> mMoviesList = new ArrayList<>();

    public DoubanRetrofit(Context c,GridView gridview)
    {
        this.mContext = c;
        this.gridView = gridview;
    }
    public void query()
    {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //创建访问API的请求
        DoubanService service = retrofit.create(DoubanService.class);
        Call<HotMoviesInfo> call = service.serchHotMovies();

        //发送请求
        call.enqueue(new Callback<HotMoviesInfo>() {
            @Override
            public void onResponse(Call<HotMoviesInfo> call, Response<HotMoviesInfo> response) {
                if(response.isSuccessful())
                {
                    HotMoviesInfo result = response.body();
                    if(result != null)
                    {
                        //debug
                        Log.d(MainActivity.TAG, "===> onResponse: Thread.Id = " + Thread.currentThread().getId());
                        //

                        mMoviesList = result.getSubjects();

                        //debug
                        Log.e(MainActivity.TAG, "===> Response, size = " + mMoviesList.size());
                        for (HotMoviesInfo.SubjectsBean movies : mMoviesList) {
                            Log.e(MainActivity.TAG, "title = " + movies.getTitle() + " " + movies.getRating().getAverage());
                        }
                        //
                        GetPicInfo getPicInfo = new GetPicInfo(mMoviesList);

                        gridView.setAdapter(new ImageAdapter(mContext, GetPicInfo.getUrls(),GetPicInfo.getNames(),GetPicInfo.getRating()));
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent i = new Intent(mContext, ItemInfoActivity.class);
                                i.putExtra("moviesInfo",mMoviesList.get(position));
                                mContext.startActivity(i);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<HotMoviesInfo> call, Throwable t) {
                //debug
                Log.d(MainActivity.TAG, "===> onFailure: Thread.Id = "
                        + Thread.currentThread().getId() + ", Error: " + t.getMessage());
            }
        });
    }
}
