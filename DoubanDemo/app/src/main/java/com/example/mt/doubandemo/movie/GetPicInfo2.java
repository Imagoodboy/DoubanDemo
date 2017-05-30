package com.example.mt.doubandemo.movie;

import com.example.mt.doubandemo.movie.ComingMovieInfo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 2017/5/29.
 */

public class GetPicInfo2 {
    private List<SubjectsBean> mMovies = new ArrayList<>();
    private static List<String> mMoviesUrl = new ArrayList<>();
    private static List<String> mMoviesName = new ArrayList<>();
    private static List<String> mMoviesDirectors = new ArrayList<>();
    public GetPicInfo2(List<SubjectsBean> m){
        mMovies = m;
        init();
    }
    private void init()
    {
        mMoviesUrl.clear();
        mMoviesName.clear();
        mMoviesDirectors.clear();
        setUrl();
        setName();
        setDirectors();
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
    private void setDirectors()
    {
        for(SubjectsBean movies : mMovies)
        {
            for(SubjectsBean.DirectorsBean dir : movies.getDirectors()) {
                mMoviesDirectors.add(dir.getName());
                break;
            }
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
    public static  List<String> getDirectors() { return mMoviesDirectors;}
}
