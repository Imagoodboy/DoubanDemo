package com.example.mt.doubandemo.movie;

import java.util.ArrayList;
import java.util.List;

import com.example.mt.doubandemo.movie.HotMoviesInfo.*;
/**
 * Created by mt on 2017/5/21.
 */

public class GetPicInfo {
    private List<SubjectsBean> mMovies = new ArrayList<>();
    private static List<String> mMoviesUrl = new ArrayList<>();
    private static List<String> mMoviesName = new ArrayList<>();
    private static List<Double> mMoviesRating = new ArrayList<>();
    public GetPicInfo(List<SubjectsBean> m){
        mMovies = m;
        init();
    }
    private void init()
    {
        mMoviesUrl.clear();
        mMoviesName.clear();
        mMoviesRating.clear();
        setUrl();
        setName();
        setRating();
    }
    private void setUrl()
    {
        for(SubjectsBean movies : mMovies)
        {
            mMoviesUrl.add(movies.getImages().getSmall());
        }
    }
    private void setName()
    {
        for(SubjectsBean movies : mMovies)
        {
            mMoviesName.add(movies.getTitle());
        }
    }
    private void setRating()
    {
        for(SubjectsBean movies :mMovies)
        {
            mMoviesRating.add(movies.getRating().getAverage());
        }
    }
    public static  List<String> getUrls()
    {
        return mMoviesUrl;
    }
    public static  List<String> getNames()
    {
        return mMoviesName;
    }
    public static  List<Double> getRating() { return mMoviesRating;}
}
