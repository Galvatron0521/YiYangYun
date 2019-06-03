package com.wd.yiyangyun.mvp.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.patient.bean.BingZhengListBean;
import com.wd.yiyangyun.utils.HcUtils;

import java.util.List;


/**
 * Created by chong.han on 2018/8/28.
 */

public class BingZhangListAdapter2 extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    private List<BingZhengListBean.ListBean> mDatas;
    private PopupWindow pop;
    public BingZhangListAdapter2(Context mContext, List<BingZhengListBean.ListBean> mDatas,
                                 Activity activity) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.activity = activity;
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
            view = View.inflate(mContext, R.layout.item_bingzhang_list2, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final BingZhengListBean.ListBean listBean = mDatas.get(i);
        if (!TextUtils.isEmpty(listBean.fieldRecordDate)) {
            viewHolder.item_bingzheng_time_tv.setText("时间：" + HcUtils.getData(Long.parseLong
                    (listBean.fieldRecordDate)));
        }
        viewHolder.item_xaizai_tv.setText("");
        viewHolder.item_xaizai_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClickListener.onDeleteClick(i);
            }
        });
        viewHolder.item_bingzheng_time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(i);
            }
        });
        return view;
    }

    private OnDeleteClickListener onDeleteClickListener;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
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
        public TextView item_bingzheng_time_tv;
        public TextView item_xaizai_tv;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_bingzheng_time_tv = (TextView) rootView.findViewById(R.id.item_bingzheng_time_tv);
            this.item_xaizai_tv = (TextView) rootView.findViewById(R.id.item_xaizai_tv);
        }
    }

}
