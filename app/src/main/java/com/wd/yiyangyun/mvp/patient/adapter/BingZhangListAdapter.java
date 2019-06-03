package com.wd.yiyangyun.mvp.patient.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.patient.bean.BingZhengListBean;
import com.wd.yiyangyun.utils.HcUtils;

import java.util.List;

import cn.finalteam.galleryfinal.widget.HorizontalListView;


/**
 * Created by chong.han on 2018/8/28.
 */

public class BingZhangListAdapter extends BaseAdapter {
    private Context mContext;
    private Activity activity;
    private List<BingZhengListBean.ListBean> mDatas;
    private PopupWindow pop;
    public BingZhangListAdapter(Context mContext, List<BingZhengListBean.ListBean> mDatas,
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
            view = View.inflate(mContext, R.layout.item_bingzhang_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final BingZhengListBean.ListBean listBean = mDatas.get(i);
        // 0 复诊 1 首诊 2 无
        String beizhu = "";
        if (listBean.recordFlag.equals("0")) {
            beizhu = "复诊";
        } else if (listBean.recordFlag.equals("1")) {
            beizhu = "首诊";
        } else if (listBean.recordFlag.equals("2")) {
            beizhu = "无";
        }
        viewHolder.item_bingzheng_beizhu_tv.setText("备注：" + beizhu);
        if (!TextUtils.isEmpty(listBean.fieldRecordDate)) {
            viewHolder.item_bingzheng_time_tv.setText("时间：" + HcUtils.getData(Long.parseLong
                    (listBean.fieldRecordDate)));
        }
        viewHolder.item_bingzheng_no_tv.setText("编号：" + listBean.fieldRecordSign);

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.item_bingzheng_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClickListener.onDeleteClick(i);
                finalViewHolder.swipe_menulayout.quickClose();
            }
        });
        viewHolder.item_bingzheng_container.setOnClickListener(new View.OnClickListener() {
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
        public ImageView item_bingzheng_list_iv;
        public TextView item_bingzheng_no_tv;
        public TextView item_bingzheng_time_tv;
        public TextView item_bingzheng_beizhu_tv;
        public HorizontalListView item_bingzheng_hlist;
        public Button item_bingzheng_delete;
        public LinearLayout item_bingzheng_container;
        public SwipeMenuLayout swipe_menulayout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_bingzheng_list_iv = (ImageView) rootView.findViewById(R.id
                    .item_bingzheng_list_iv);
            this.item_bingzheng_no_tv = (TextView) rootView.findViewById(R.id.item_bingzheng_no_tv);
            this.item_bingzheng_time_tv = (TextView) rootView.findViewById(R.id
                    .item_bingzheng_time_tv);
            this.item_bingzheng_beizhu_tv = (TextView) rootView.findViewById(R.id
                    .item_bingzheng_beizhu_tv);
            this.item_bingzheng_hlist = (HorizontalListView) rootView.findViewById(R.id
                    .item_bingzheng_hlist);
            this.item_bingzheng_delete = (Button) rootView.findViewById(R.id.item_bingzheng_delete);
            this.item_bingzheng_container = (LinearLayout) rootView.findViewById(R.id.item_bingzheng_container);
            this.swipe_menulayout = (SwipeMenuLayout) rootView.findViewById(R.id.swipe_menulayout);
        }

    }

}
