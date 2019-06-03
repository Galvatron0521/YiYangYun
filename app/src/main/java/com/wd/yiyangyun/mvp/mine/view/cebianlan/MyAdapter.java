package com.wd.yiyangyun.mvp.mine.view.cebianlan;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wd.yiyangyun.R;

import java.util.List;

/**
 * Created by wk on 2016/6/14.
 */
public class MyAdapter extends BaseAdapter {


    private Context mContext;
    private List<GoodMan> datas;

    public MyAdapter(Context context, List<GoodMan> datas){
        this.mContext = context;
        this.datas = datas;
    }


    @Override
    public int getCount() {
        if(datas!=null){
            return datas.size();
        }
        return 0;
    }

    @Override
    public GoodMan getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_man,null);
            holder.tv_index = (TextView) convertView.findViewById(R.id.tv_index);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //判断当前条目的拼音的首字母和上一条目的首字母是否相同
        //如果相同则不显示，不同则显示索引

        String firstLetter = datas.get(position).pinyin.charAt(0)+"";
        if(position == 0){
            holder.tv_index.setVisibility(View.VISIBLE);
            holder.tv_index.setText(firstLetter);

        }else{
            String preLetter = datas.get(position-1).pinyin.charAt(0)+"";
            if(TextUtils.equals(firstLetter,preLetter)){
                holder.tv_index.setVisibility(View.GONE);
                holder.tv_index.setText(firstLetter);
            }else{
                holder.tv_index.setVisibility(View.VISIBLE);
                holder.tv_index.setText(firstLetter);
            }

        }
        holder.tv_name.setText(datas.get(position).name);

        return convertView;
    }
    public class ViewHolder{
        private TextView tv_index;
        private TextView tv_name;
    }
}