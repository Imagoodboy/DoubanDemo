package com.example.mt.doubandemo.common;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.mt.doubandemo.MainActivity;
import com.example.mt.doubandemo.R;
import com.example.mt.doubandemo.movie.HotMoviesInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 2017/5/21.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mMoviesUrls = new ArrayList<>();
    private List<String> mMoviesNames = new ArrayList<>();
    private List<Double> mMoviesRating = new ArrayList<>();
    public  ImageAdapter(Context c,List<String> urls,List<String> Names,List<Double> Rating) {
        this.mContext = c;
        this.mMoviesUrls = urls;
        this.mMoviesNames = Names;
        this.mMoviesRating = Rating;
    }

    @Override
    public int getCount() {
        return mMoviesUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return mMoviesUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //----Debug
        Log.e(MainActivity.TAG,
                String.format("getView %s, %s", position, convertView == null));
        //----

        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.grid_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.ItemImage);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.ItemText);
            viewHolder.textView2 = (TextView)convertView.findViewById(R.id.ItemText2);
            viewHolder.ratingBar = (RatingBar)convertView.findViewById(R.id.ratingBar);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext)
                .load(mMoviesUrls.get(position))
                .placeholder(R.drawable.loading)
                .dontAnimate()
                //.fitCenter()
                .into(viewHolder.imageView);
        viewHolder.textView.setText(mMoviesNames.get(position));
        viewHolder.textView2.setText(mMoviesRating.get(position).toString());
        viewHolder.ratingBar.setMax(10);
        viewHolder.ratingBar.setStepSize(0.5f);
        viewHolder.ratingBar.setRating(mMoviesRating.get(position).floatValue()/2);

        return convertView;
    }
    private class ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView2;
        RatingBar ratingBar;
    }

}

