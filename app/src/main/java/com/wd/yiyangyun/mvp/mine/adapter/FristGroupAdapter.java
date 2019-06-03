package com.wd.yiyangyun.mvp.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.mine.bean.GroupListBean;

import java.util.List;

import retrofit2.http.POST;

/**
 * Created by chong.han on 2018/8/20.
 */

public class FristGroupAdapter extends BaseAdapter {
    private Context mContext;
    private List<GroupListBean.DataBean.ListBean> mDatas;
    boolean isCheck;

    public FristGroupAdapter(Context mContext, List<GroupListBean.DataBean.ListBean> mDatas,boolean isCheck) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.isCheck = isCheck;
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
            view = View.inflate(mContext, R.layout.item_frist_group, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        GroupListBean.DataBean.ListBean bean = mDatas.get(i);
        viewHolder.item_my_plan_crf_name.setText(bean.getGroupName());
        if(isCheck){
            viewHolder.item_my_plan_crf_checkbox.setVisibility(View.VISIBLE);
        }else{
            viewHolder.item_my_plan_crf_checkbox.setVisibility(View.GONE);
        }
        viewHolder.item_my_plan_crf_checkbox.setChecked(bean.isCheck);
        viewHolder.item_my_plan_crf_checkbox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onItemClickListener.onItemClick(i);
            }
        });
        return view;
    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder {
        public View rootView;
        public TextView item_my_plan_crf_name;
        public CheckBox item_my_plan_crf_checkbox;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_my_plan_crf_name = (TextView) rootView.findViewById(R.id
                    .item_my_plan_crf_name);
            this.item_my_plan_crf_checkbox =  rootView.findViewById(R.id
                    .item_my_plan_crf_checkbox);
        }

    }
}
