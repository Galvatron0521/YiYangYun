package com.wd.yiyangyun.mvp.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.mine.bean.MySuggestionListBean;
import com.wd.yiyangyun.utils.HcUtils;

import java.util.List;


/**
 * Created by chong.han on 2018/8/23.
 * <p>
 * 和我的计划用的一个layout
 */

public class MySuggestionListAdapter extends BaseAdapter {
    private Context mContext;
    private List<MySuggestionListBean.ListBean> mDatas;

    public MySuggestionListAdapter(Context mContext, List<MySuggestionListBean.ListBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_yijian, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MySuggestionListBean.ListBean listBean = mDatas.get(i);
        viewHolder.item_suifang_fangan_name.setText("意见内容：" + listBean.myOpinion);
        viewHolder.item_suifang_fangan_miaoshu.setText("提交时间：" + HcUtils.getData(listBean
                .createTime, "yyyy-MM-dd HH:mm"));
        return view;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView item_suifang_fangan_name;
        public TextView item_suifang_fangan_miaoshu;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_suifang_fangan_name = (TextView) rootView.findViewById(R.id
                    .item_suifang_fangan_name);
            this.item_suifang_fangan_miaoshu = (TextView) rootView.findViewById(R.id
                    .item_suifang_fangan_miaoshu);
        }

    }
}