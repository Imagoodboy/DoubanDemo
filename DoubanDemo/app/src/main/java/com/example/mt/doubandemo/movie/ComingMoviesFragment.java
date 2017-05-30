package com.example.mt.doubandemo.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mt.doubandemo.ui.ItemInfoActivity2;
import com.example.mt.doubandemo.ui.MainActivity;
import com.example.mt.doubandemo.R;
import com.example.mt.doubandemo.api.DoubanRetrofit;
import com.example.mt.doubandemo.api.DoubanService;
import com.example.mt.doubandemo.common.ComingMoviesAdapter;
import com.example.mt.doubandemo.common.LazyFragment;
import com.example.mt.doubandemo.movie.ComingMovieInfo.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mt on 2017/5/28.
 */

public class ComingMoviesFragment extends LazyFragment {
    private boolean isPrepared;
    private List<SubjectsBean> mMoviesList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ComingMoviesAdapter comingMoviesAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coming_movies_item, container, false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_coming_movies);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible){
            return;
        }
        loadMovies(new Callback<ComingMovieInfo>() {
            @Override
            public void onResponse(Call<ComingMovieInfo> call, Response<ComingMovieInfo> response) {
                if(response.isSuccessful()){
                    ComingMovieInfo result = response.body();
                    if(result!=null){
                        //debug
                        Log.d(MainActivity.TAG, "===> onResponse: Thread.Id = " + Thread.currentThread().getId());
                        //

                        mMoviesList = result.getSubjects();
                        GetPicInfo2 getPicInfo = new GetPicInfo2(mMoviesList);
                        RecyclerView.LayoutManager layout = new GridLayoutManager(getActivity(),3);
                        mRecyclerView.setLayoutManager(layout);
                        //mRecyclerView.setHasFixedSize(true);
                        comingMoviesAdapter = new ComingMoviesAdapter(GetPicInfo2.getUrls(),GetPicInfo2.getNames(),GetPicInfo2.getDirectors());
                        mRecyclerView.setAdapter(comingMoviesAdapter);
                        comingMoviesAdapter.setOnItemClickListener(new ComingMoviesAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent i = new Intent(view.getContext() ,ItemInfoActivity2.class);
                                i.putExtra("moviesInfo",mMoviesList.get(position));
                                view.getContext().startActivity(i);
                            }
                        });
                        //debug
                        Log.e(MainActivity.TAG, "===> Response, size = " + mMoviesList.size());
                        for (SubjectsBean movies : mMoviesList) {
                            Log.e(MainActivity.TAG, "title = " + movies.getTitle() + " " + movies.getRating().getAverage());
                        }
                        //
                    }
                }
            }

            @Override
            public void onFailure(Call<ComingMovieInfo> call, Throwable t) {
                //debug
                Log.d(MainActivity.TAG, "===> onFailure: Thread.Id = "
                        + Thread.currentThread().getId() + ", Error: " + t.getMessage());
            }
        });
        //  add something..
    }
    private void loadMovies(Callback<ComingMovieInfo> callback){
        DoubanService movieService = DoubanRetrofit.createDoubanService();
        movieService.serchComingMovies().enqueue(callback);
    }

}
