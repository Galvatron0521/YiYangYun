package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.yiyangyun.R;

import java.util.List;

public class HomeRecycGridAdapter extends RecyclerView.Adapter {

    private HomeRecycViewHolder homeRecycViewHolder;
    private Context mContext;
    private List<String> list;

    public HomeRecycGridAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_grid_item,parent,false);
        homeRecycViewHolder = new HomeRecycViewHolder(view);
        return homeRecycViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        homeRecycViewHolder.grid_tv.setText(list.get(position));
        homeRecycViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setOnClick!=null){
                    setOnClick.onClick(position);
                }
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
        public ImageView grid_iv;
        public TextView grid_tv;

        public HomeRecycViewHolder(View view) {
            super(view);
            grid_iv =  view.findViewById(R.id.grid_iv);
            grid_tv =  view.findViewById(R.id.grid_tv);
        }
    }
}
