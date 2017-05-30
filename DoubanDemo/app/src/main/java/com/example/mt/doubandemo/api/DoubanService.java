package com.example.mt.doubandemo.api;

import com.example.mt.doubandemo.movie.ComingMovieInfo;
import com.example.mt.doubandemo.movie.HotMoviesInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mt on 2017/5/21.
 */

public interface DoubanService {
    String BASE_URL = "https://api.douban.com/v2/";

    @GET("movie/in_theaters")
    Call<HotMoviesInfo> serchHotMovies();

    @GET("movie/coming_soon")
    Call<ComingMovieInfo> serchComingMovies();
}
