package com.example.mt.doubandemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mt.doubandemo.movie.HotMoviesInfo.SubjectsBean;

import java.util.ArrayList;
import java.util.List;

public class ItemInfoActivity extends AppCompatActivity {
    private ImageView pic;
    private SubjectsBean mMoviesInfo;
    private TextView cast,title,genre,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        Init();
    }
    private void Init()
    {
        pic = (ImageView) findViewById(R.id.ImagePic);
        cast = (TextView) findViewById(R.id.casts);
        title = (TextView) findViewById(R.id.original_title);
        genre = (TextView) findViewById(R.id.genres);
        year = (TextView) findViewById(R.id.year);
        Intent intent = getIntent();
        mMoviesInfo = (SubjectsBean)intent.getSerializableExtra("moviesInfo");
        inPutPic();
        setCast();
        setTitle();
        setGenre();
        setYear();
    }
    private void inPutPic()
    {
        Glide.with(this)
                .load(mMoviesInfo.getImages().getMedium())
                .placeholder(R.drawable.loading)
                .dontAnimate()
                //.fitCenter()
                .into(pic);
    }
    private void setCast()
    {
        int flag = 0;
        cast.setText("主演:");
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
        title.setText("又名:" + mMoviesInfo.getOriginal_title());
    }
    private void setGenre()
    {
        int flag = 0;
        List<String> genres = new ArrayList<>();
        genres = mMoviesInfo.getGenres();
        genre.setText("类型:");
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
        year.setText("上映时间:" + mMoviesInfo.getYear());
    }
}
