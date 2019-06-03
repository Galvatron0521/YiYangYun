package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.yiyangyun.R;

import java.util.List;

public class HomeGridAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> list;

    public HomeGridAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        if(view == null){
            view = View.inflate(mContext,R.layout.home_grid_item,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.grid_tv.setText(list.get(i));

        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView grid_iv;
        public TextView grid_tv;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.grid_iv =  rootView.findViewById(R.id.grid_iv);
            this.grid_tv = (TextView) rootView.findViewById(R.id.grid_tv);
        }

    }

}
