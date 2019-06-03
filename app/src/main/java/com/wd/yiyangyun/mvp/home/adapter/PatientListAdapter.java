package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.bean.PatientListBean;
import com.wd.yiyangyun.utils.DateUtils;
import com.wd.yiyangyun.utils.GlideCircleTransUtils;

import java.util.Date;
import java.util.List;

public class PatientListAdapter extends BaseAdapter {
    private Context mContext;
    private Boolean isFriendList;
    private List<PatientListBean.ListBean> mDatas;

    public PatientListAdapter(Context mContext, List<PatientListBean.ListBean> mDatas, boolean
            isFriendList) {
        this.mContext = mContext;
        this.isFriendList = isFriendList;
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_patient_layout_new, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final PatientListBean.ListBean listBean = mDatas.get(i);
        String patientIcon = "http://shenkangyun.com:808/attachment/headdoctor/huanzhe.jpg";
        Glide.with(mContext).load(patientIcon).transform(new GlideCircleTransUtils(mContext)).into(viewHolder.item_patient_logo);
        viewHolder.item_patient_name.setText(listBean.name);
        viewHolder.item_patient_time.setText(DateUtils.dateToString(new Date(listBean.createTime)
                , DateUtils.FORMAT_5));
        viewHolder.item_patient_phone.setText(listBean.mobile);

        try {
//            if(listBean.isFocus.equals("1")){
//                viewHolder.item_zd.setVisibility(View.VISIBLE);
//            }
                viewHolder.item_patient_logo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onViewClickLisener.onClick(i, 0);
                    }
                });
                viewHolder.item_patient_rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onViewClickLisener.onClick(i, 0);
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private OnViewClickLisener onViewClickLisener;
    private OnDeleteClickListener onDeleteClickListener;

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public void setOnViewClickLisener(OnViewClickLisener onViewClickLisener) {
        this.onViewClickLisener = onViewClickLisener;
    }

    public interface OnViewClickLisener {
        /**
         * @param position 点击索引
         * @param type     类型 0 头像  1 复诊  2 随访
         */
        void onClick(int position, int type);
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView item_patient_logo;
        public TextView item_patient_name;
        public TextView item_patient_phone;
        public TextView item_patient_time;
        public RelativeLayout item_patient_rl;
        public LinearLayout item_ll;
        public TextView item_zd;


        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.item_patient_logo =  rootView.findViewById(R.id.item_patient_logo);
            this.item_patient_name = (TextView) rootView.findViewById(R.id.item_patient_name);
            this.item_patient_phone = (TextView) rootView.findViewById(R.id.item_patient_phone);
            this.item_patient_time = (TextView) rootView.findViewById(R.id.item_patient_time);
            this.item_patient_rl = (RelativeLayout) rootView.findViewById(R.id.item_patient_rl);
            this.item_ll = rootView.findViewById(R.id.item_ll);
            this.item_zd = rootView.findViewById(R.id.item_zd);

        }

    }
}

