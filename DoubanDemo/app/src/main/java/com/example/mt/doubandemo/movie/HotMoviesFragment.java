package com.example.mt.doubandemo.movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mt.doubandemo.ui.ItemInfoActivity;
import com.example.mt.doubandemo.ui.MainActivity;
import com.example.mt.doubandemo.R;
import com.example.mt.doubandemo.api.DoubanRetrofit;
import com.example.mt.doubandemo.api.DoubanService;
import com.example.mt.doubandemo.common.HotMoviesAdapter;
import com.example.mt.doubandemo.common.LazyFragment;
import com.example.mt.doubandemo.movie.HotMoviesInfo.SubjectsBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mt on 2017/5/28.
 */

public class HotMoviesFragment extends LazyFragment {

    private List<SubjectsBean> mMoviesList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private boolean isPrepared;
    private HotMoviesAdapter hotMoviesAdapter;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_movies_item, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_hot_movies);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible){
            return;
        }
        loadMovies(new Callback<HotMoviesInfo>() {
            @Override
            public void onResponse(Call<HotMoviesInfo> call, Response<HotMoviesInfo> response) {
                if(response.isSuccessful()){
                    HotMoviesInfo result = response.body();
                    if(result!=null){
                        //debug
                        Log.d(MainActivity.TAG, "===> onResponse: Thread.Id = " + Thread.currentThread().getId());
                        //

                        mMoviesList = result.getSubjects();
                        GetPicInfo getPicInfo = new GetPicInfo(mMoviesList);
                        RecyclerView.LayoutManager layout = new GridLayoutManager(getActivity(),3);
                        mRecyclerView.setLayoutManager(layout);
                        //mRecyclerView.setHasFixedSize(true);
                        hotMoviesAdapter = new HotMoviesAdapter(GetPicInfo.getUrls(),GetPicInfo.getNames(),GetPicInfo.getRating());
                        mRecyclerView.setAdapter(hotMoviesAdapter);
                        hotMoviesAdapter.setOnItemClickListener(new HotMoviesAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(view.getContext() ,ItemInfoActivity.class);
                                i.putExtra("moviesInfo",mMoviesList.get(position));
                                view.getContext().startActivity(i);
                            }
                        });
                        //debug
                        Log.e(MainActivity.TAG, "===> Response, size = " + mMoviesList.size());
                        for (HotMoviesInfo.SubjectsBean movies : mMoviesList) {
                            Log.e(MainActivity.TAG, "title = " + movies.getTitle() + " " + movies.getRating().getAverage());
                        }
                        //
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
    private void loadMovies(Callback<HotMoviesInfo> callback){
        DoubanService movieService = DoubanRetrofit.createDoubanService();
        movieService.serchHotMovies().enqueue(callback);
    }
}
