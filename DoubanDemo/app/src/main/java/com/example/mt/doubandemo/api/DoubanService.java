package com.example.mt.doubandemo.api;

import com.example.mt.doubandemo.movie.HotMoviesInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mt on 2017/5/21.
 */

public interface DoubanService {
    @GET("movie/in_theaters")
    Call<HotMoviesInfo> serchHotMovies();
}
