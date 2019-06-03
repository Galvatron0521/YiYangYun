package com.wd.yiyangyun.mvp.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.mvp.mine.bean.GroupListBean;
import com.wd.yiyangyun.mvp.patient.bean.TagBean;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chong.han on 2018/8/20.
 */

public class TagListAdapter extends BaseAdapter {
    private Context mContext;
    public int ON_EDIT_CLICK = 0;
    public int ON_DELETE_CLICK = 1;
    private List<GroupListBean.DataBean.ListBean> mDatas;

    public TagListAdapter(Context mContext, List<GroupListBean.DataBean.ListBean> mDatas) {
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
            view = View.inflate(mContext, R.layout.item_tag_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        try {
            viewHolder.item_tag_name.setText(mDatas.get(i).getGroupName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.item_tag_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickListener.onClick(ON_EDIT_CLICK, i);
            }
        });
        viewHolder.item_tag_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickListener.onClick(ON_DELETE_CLICK, i);
            }
        });
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView item_tag_name;
        public TextView item_tag_delete;
        public TextView item_tag_edit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_tag_name = (TextView) rootView.findViewById(R.id.item_tag_name);
            this.item_tag_delete = (TextView) rootView.findViewById(R.id.item_tag_delete);
            this.item_tag_edit = (TextView) rootView.findViewById(R.id.item_tag_edit);
        }
    }

    private onButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(TagListAdapter.onButtonClickListener
                                                 onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public interface onButtonClickListener {
        void onClick(int type, int position);
    }

}
