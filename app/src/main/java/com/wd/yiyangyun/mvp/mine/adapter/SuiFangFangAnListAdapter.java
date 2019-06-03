package com.wd.yiyangyun.mvp.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;

import java.util.List;


/**
 * Created by chong.han on 2018/8/23.
 */

public class SuiFangFangAnListAdapter extends BaseAdapter {
    private Context mContext;
    private List<MyPlanListBean.FollowlistBean> mDatas;

    public SuiFangFangAnListAdapter(Context mContext, List<MyPlanListBean.FollowlistBean> mDatas) {
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
            view = View.inflate(mContext, R.layout.item_select_suifang_fangan_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        MyPlanListBean.FollowlistBean listBean = mDatas.get(i);
        viewHolder.item_suifang_fangan_name.setText("随访方案名称：" + listBean.planName);
//        viewHolder.item_suifang_fangan_miaoshu.setText("随访方案描述：" + (TextUtils.isEmpty(listBean
//                .followDescript) ? "暂无描述信息" : listBean.followDescript));
        viewHolder.item_bingzheng_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(i);
            }
        });
        return view;
    }

    private MyPlanListAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyPlanListAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder {
        public View rootView;
        public TextView item_suifang_fangan_name;
        public TextView item_suifang_fangan_miaoshu;
        public LinearLayout item_bingzheng_container;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_suifang_fangan_name = (TextView) rootView.findViewById(R.id
                    .item_suifang_fangan_name);
            this.item_suifang_fangan_miaoshu = (TextView) rootView.findViewById(R.id
                    .item_suifang_fangan_miaoshu);
            this.item_bingzheng_container = (LinearLayout) rootView.findViewById(R.id.item_bingzheng_container);

        }

    }
}