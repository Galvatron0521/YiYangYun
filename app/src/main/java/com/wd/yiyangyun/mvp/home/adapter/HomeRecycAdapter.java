package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.bean.NewsBean;
import com.wd.yiyangyun.utils.GlideCircleTransUtils;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.LogUtil;

import java.util.List;

public class HomeRecycAdapter extends RecyclerView.Adapter {

    private HomeRecycViewHolder homeRecycViewHolder;
    private Context mContext;
    private List<NewsBean.DataBean.InformationListBean> list;

    public HomeRecycAdapter(Context mContext, List<NewsBean.DataBean.InformationListBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        homeRecycViewHolder = new HomeRecycViewHolder(view);
        return homeRecycViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        homeRecycViewHolder.news_title_tv.setText(list.get(position).getName());
        homeRecycViewHolder.news_content_tv.setText(list.get(position).getContent());
        homeRecycViewHolder.news_time_tv.setText(HcUtils.getData(list.get(position).getCreateTime()));
        List<String> photoList = list.get(position).getCoverPhoto();
         for(int i=0; i< photoList.size() ; i++){
             Glide.with(mContext).load("http://shenkangyun.com:808/"+photoList.get(i))
                     .into(homeRecycViewHolder.news_image);
         }

        homeRecycViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    setOnClick.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    setOnClick setOnClick;

    public void setSetOnClick(setOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }

    public interface setOnClick{
        void onClick(int position);
    }

    private class HomeRecycViewHolder extends RecyclerView.ViewHolder{
        public ImageView news_image;
        public TextView news_title_tv;
        public TextView news_content_tv;
        public TextView news_time_tv;

        public HomeRecycViewHolder(View view) {
            super(view);
            news_image =  view.findViewById(R.id.news_image);
            news_title_tv = (TextView) view.findViewById(R.id.news_title_tv);
            news_content_tv = (TextView) view.findViewById(R.id.news_content_tv);
            news_time_tv = (TextView) view.findViewById(R.id.news_time_tv);
        }
    }
}
