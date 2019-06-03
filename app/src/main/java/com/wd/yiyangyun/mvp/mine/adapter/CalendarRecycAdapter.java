package com.wd.yiyangyun.mvp.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.adapter.HomeRecycAdapter;
import com.wd.yiyangyun.mvp.home.bean.NewsBean;
import com.wd.yiyangyun.mvp.mine.bean.CalendarBean;
import com.wd.yiyangyun.utils.LogUtil;

import java.util.List;

public class CalendarRecycAdapter extends RecyclerView.Adapter {

    private CalendarRecycViewHolder homeRecycViewHolder;
    private Context mContext;
    private List<CalendarBean.DataBean.DetailsBean> list;

    public CalendarRecycAdapter(Context mContext, List<CalendarBean.DataBean.DetailsBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item,parent,false);
        homeRecycViewHolder = new CalendarRecycViewHolder(view);
        return homeRecycViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        try {
            homeRecycViewHolder.date_item.setText(list.get(position).getId());
            homeRecycViewHolder.content_item.setText("地点："+list.get(position).getMenzhenAddress());
            if(list.get(position).getMenzhenTime()==1){
                homeRecycViewHolder.time_item.setText("时间："+"上午");
            }else{
                homeRecycViewHolder.time_item.setText("时间："+"下午");
            }

            homeRecycViewHolder.item_calendar_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        onItemClickListener.onItemClick(position);
                }
            });

            homeRecycViewHolder.item_bingzheng_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteClickListener.onDeleteClick(position);
                    homeRecycViewHolder.swipe_menulayout.quickClose();
                }
            });

            homeRecycViewHolder.item_bingzheng_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteClickListener.onDeleteClick(position);
                    homeRecycViewHolder.swipe_menulayout.quickClose();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnDeleteClickListener onDeleteClickListener;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    private class CalendarRecycViewHolder extends RecyclerView.ViewHolder{
        public TextView date_item;
        public TextView content_item;
        public TextView time_item;
        public TextView status_item;
        public Button item_bingzheng_delete;
        public SwipeMenuLayout swipe_menulayout;
        public LinearLayout item_calendar_container;


        public CalendarRecycViewHolder(View view) {
            super(view);
            date_item =  view.findViewById(R.id.date_item);
            time_item = (TextView) view.findViewById(R.id.time_item);
            content_item = (TextView) view.findViewById(R.id.content_item);
            item_bingzheng_delete = (Button) view.findViewById(R.id.item_bingzheng_delete);
            status_item = (TextView) view.findViewById(R.id.status_item);
            swipe_menulayout = (SwipeMenuLayout) view.findViewById(R.id.swipe_menulayout);
            item_calendar_container = (LinearLayout) view.findViewById(R.id.item_calendar_container);
        }
    }
}
