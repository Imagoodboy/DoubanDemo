package com.example.mt.doubandemo.common;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mt.doubandemo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mt on 2017/5/29.
 */

public class ComingMoviesAdapter extends RecyclerView.Adapter<ComingMoviesAdapter.ViewHolder> implements View.OnClickListener{
    private List<String> mMoviesUrls = new ArrayList<>();
    private List<String> mMoviesNames = new ArrayList<>();
    private List<String> mMoviesDirectors = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener = null;
    public ComingMoviesAdapter(List<String> urls,List<String> Names,List<String> Directors){
        this.mMoviesUrls = urls;
        this.mMoviesNames = Names;
        this.mMoviesDirectors = Directors;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    @Override
    public ComingMoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_coming_item,null);
        //创建ViewHoler
        ViewHolder viewHolder = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ComingMoviesAdapter.ViewHolder holder, int position) {
        Uri uri = Uri.parse(mMoviesUrls.get(position));
        holder.img_pic2.setImageURI(uri);
        holder.tv_name2.setText(mMoviesNames.get(position));
        holder.tv_Direcotrs.setText(mMoviesDirectors.get(position));
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMoviesUrls.size();
    }
    public static class ViewHolder  extends  RecyclerView.ViewHolder{
        SimpleDraweeView img_pic2;
        TextView tv_name2;
        TextView tv_Direcotrs;
        public ViewHolder(View view){
            super(view);
            img_pic2 = (SimpleDraweeView)view.findViewById(R.id.imgView_pic2);
            tv_name2 = (TextView)view.findViewById(R.id.tv_coming_name);
            tv_Direcotrs = (TextView)view.findViewById(R.id.tv_coming_directors);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
