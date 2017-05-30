package com.example.mt.doubandemo.common;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mt.doubandemo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 2017/5/29.
 */

public class HotMoviesAdapter extends RecyclerView.Adapter<HotMoviesAdapter.ViewHolder> implements View.OnClickListener{

    private List<String> mMoviesUrls = new ArrayList<>();
    private List<String> mMoviesNames = new ArrayList<>();
    private List<Double> mMoviesRating = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener = null;//接口变量
    public HotMoviesAdapter(List<String> urls,List<String> Names,List<Double> Rating){
        this.mMoviesUrls = urls;
        this.mMoviesNames = Names;
        this.mMoviesRating = Rating;
    }
    //将点击事件转移给外面的调用者
    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    //定义接口,模拟ListView的OnItemClickListener
    public static interface OnItemClickListener{
        void onItemClick(View view , int position);
    }
    @Override
    public HotMoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hot_item,null);
        //创建ViewHoler
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotMoviesAdapter.ViewHolder holder, int position) {
        Uri uri = Uri.parse(mMoviesUrls.get(position));
        holder.img_pic.setImageURI(uri);
        holder.tv_name.setText(mMoviesNames.get(position));
        holder.tv_rating.setText(mMoviesRating.get(position).toString());
        holder.ratingBar.setMax(10);
        holder.ratingBar.setStepSize(0.5f);
        holder.ratingBar.setRating(mMoviesRating.get(position).floatValue()/2);
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMoviesUrls.size();
    }
    public static class ViewHolder  extends  RecyclerView.ViewHolder{
        SimpleDraweeView img_pic;
        TextView tv_name;
        TextView tv_rating;
        RatingBar ratingBar;
        public ViewHolder(View view){
            super(view);
            img_pic = (SimpleDraweeView)view.findViewById(R.id.imgView_pic);
            tv_name = (TextView)view.findViewById(R.id.tv_hot_name);
            tv_rating = (TextView)view.findViewById(R.id.tv_hot_rating);
            ratingBar = (RatingBar) view.findViewById(R.id.ratingBar_hot);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
