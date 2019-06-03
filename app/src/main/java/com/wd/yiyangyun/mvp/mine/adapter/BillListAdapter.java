package com.wd.yiyangyun.mvp.mine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.mine.bean.MyTollBean;

import java.util.List;


/**
 * Created by chong.han on 2018/8/20.
 */

public class BillListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private Boolean isFriendList;
    private List<MyTollBean.DataBean.ListBean> mDatas;
    private MyViewHolder viewHolder;

    public BillListAdapter(Context mContext, List<MyTollBean.DataBean.ListBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bill_item,parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        try {
            viewHolder.bill_hao_tv.setText(mDatas.get(i).getOrderID());
            viewHolder.bill_name_tv.setText(mDatas.get(i).getPatientName());
            viewHolder.bill_fanshi_tv.setText(mDatas.get(i).getPayItemName());
            viewHolder.bill_time_tv.setText(mDatas.get(i).getTimeEnd());
            viewHolder.bill_price_tv.setText(mDatas.get(i).getTotalFee());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bill_hao_tv;
        public TextView bill_name_tv;
        public TextView bill_fanshi_tv;
        public TextView bill_time_tv;
        public TextView bill_price_tv;

        public MyViewHolder (View rootView) {
            super(rootView);
            bill_hao_tv = rootView.findViewById(R.id.bill_hao_tv);
            bill_name_tv = rootView.findViewById(R.id.bill_name_tv);
            bill_fanshi_tv = rootView.findViewById(R.id.bill_fanshi_tv);
            bill_time_tv = rootView.findViewById(R.id.bill_time_tv);
            bill_price_tv = rootView.findViewById(R.id.bill_price_tv);
        }

    }
}
