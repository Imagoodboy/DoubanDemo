package com.example.mt.doubandemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mt.doubandemo.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.example.mt.doubandemo.movie.ComingMovieInfo.SubjectsBean;

import java.util.ArrayList;
import java.util.List;

public class ItemInfoActivity2 extends AppCompatActivity {
    private SimpleDraweeView pic;
    private SubjectsBean mMoviesInfo;
    private TextView cast,title,genre,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info2);
        Init();
    }
    private void Init()
    {
        pic = (SimpleDraweeView) findViewById(R.id.img_info_pic2);
        cast = (TextView) findViewById(R.id.tv_coming_casts);
        title = (TextView) findViewById(R.id.tv_coming_original_title);
        genre = (TextView) findViewById(R.id.tv_coming_genres);
        year = (TextView) findViewById(R.id.tv_coming_year);
        Intent intent = getIntent();
        mMoviesInfo = (SubjectsBean)intent.getSerializableExtra("moviesInfo");
        //debug
        Log.e(MainActivity.TAG, "===> if null = " + mMoviesInfo.getImages().getMedium());
        //
        inPutPic();
        setCast();
        setTitle();
        setGenre();
        setYear();
    }
    private void inPutPic()
    {
        Uri uri = Uri.parse(mMoviesInfo.getImages().getMedium());
        pic.setImageURI(uri);
    }
    private void setCast()
    {
        int flag = 0;
        cast.setText(getString(R.string.casts));
        for(SubjectsBean.CastsBean casts: mMoviesInfo.getCasts())
        {
            if(flag!=0)
            {
                cast.append("/");
            }
            cast.append(casts.getName());
            flag++;
        }
    }
    private void setTitle()
    {
        title.setText(getString(R.string.original_title) + mMoviesInfo.getOriginal_title());
    }
    private void setGenre()
    {
        int flag = 0;
        List<String> genres = new ArrayList<>();
        genres = mMoviesInfo.getGenres();
        genre.setText(R.string.genre);
        for (String name : genres)
        {
            if(flag!=0)
            {
                genre.append("/");
            }
            genre.append(name);
            flag++;
        }
    }
    private void setYear()
    {
        year.setText( getString(R.string.year) + mMoviesInfo.getYear());
    }
}
