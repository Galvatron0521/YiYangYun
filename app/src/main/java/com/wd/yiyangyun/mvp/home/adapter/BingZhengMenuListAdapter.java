package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.bean.BianZhengBean;

import java.util.List;


/**
 * Created by chong.han on 2018/8/31.
 */

public class BingZhengMenuListAdapter extends BaseAdapter {
    private List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean> mDatas;
    private Context mContext;

    public BingZhengMenuListAdapter(List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean> mDatas,
                                    Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_bianzheng_menu_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
//        if (mDatas.get(i).tagModule.isSelect) {
//            viewHolder.menu_name_tv.setBackgroundColor(Color.parseColor("#f8b203"));
//        } else{
//            viewHolder.menu_name_tv.setBackgroundColor(Color.WHITE);
//        }
        viewHolder.menu_name_tv.setText(mDatas.get(i).getTagModule().getModuleName());
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView menu_name_tv;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.menu_name_tv = (TextView) rootView.findViewById(R.id.menu_name_tv);
        }

    }
}
