package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.bean.ZhuanInBean;
import com.wd.yiyangyun.mvp.home.bean.ZhuanZhenInfoBean;
import com.wd.yiyangyun.utils.HcUtils;

import java.util.List;

public class ZhuanZhenAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZhuanZhenInfoBean.DataBean.ListBean> mDatas;

    public ZhuanZhenAdapter(Context mContext, List<ZhuanZhenInfoBean.DataBean.ListBean> mDatas) {
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
            view = View.inflate(mContext, R.layout.item_zhuanzhen, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ZhuanZhenInfoBean.DataBean.ListBean listBean = mDatas.get(i);
        //转出
        viewHolder.zhuanchu_hosit_tv.setText("转出医院:"+listBean.getFromHospitalName());
        viewHolder.zhuanchu_keshi_tv.setText("转出科室:"+listBean.getFromDepartmentName());
        viewHolder.zhuanchu_doctor_tv.setText("操作医生:"+listBean.getFromUserName());
        viewHolder.zhuanchu_time_tv.setText("转出时间:"+HcUtils.getData(listBean.getCreateTime()));
        //转入
        viewHolder.zhuanru_hosit_tv.setText("转入医院:"+listBean.getToHospitalName());
        viewHolder.zhuanru_keshi_tv.setText("转入科室:"+listBean.getToDepartmentName());
        viewHolder.zhuanru_doctor_tv.setText("接受医生:"+listBean.getToUserName());
        //状态，原因
        if(listBean.getReceiptState()==0){
            viewHolder.zhuanchu_status_tv.setText("未接诊");
        }
        if(listBean.getReceiptState()==1){
            viewHolder.zhuanchu_status_tv.setText("已接诊");
            viewHolder.zhuanru_time_tv.setText("接受时间:"+ HcUtils.getData(Long.parseLong(listBean.getReceiptTime())));

        }
        if(listBean.getReceiptState()==2){
            viewHolder.zhuanchu_status_tv.setText("已拒绝");
            viewHolder.zhuanru_time_tv.setText("拒绝时间:"+HcUtils.getData(Long.parseLong(listBean.getUnReceiptTime())));

            viewHolder.jujue_tv.setVisibility(View.VISIBLE);
            viewHolder.jujue_tv.setText("拒绝原因:"+listBean.getRefuseReason());
        }
//        viewHolder.zhuanzhen_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onButtonClickListener.onClick(i);
//            }
//        });
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public LinearLayout zhuanzhen_item;
        public TextView zhuanchu_hosit_tv;
        public TextView zhuanchu_keshi_tv;
        public TextView zhuanchu_doctor_tv;
        public TextView zhuanchu_time_tv;
        public TextView zhuanru_hosit_tv;
        public TextView zhuanru_keshi_tv;
        public TextView zhuanru_doctor_tv;
        public TextView zhuanru_time_tv;
        public TextView zhuanchu_status_tv;
        public TextView jujue_tv;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.zhuanzhen_item = rootView.findViewById(R.id.zhuanzhen_item);
            this.zhuanchu_hosit_tv = rootView.findViewById(R.id.zhuanchu_hosit_tv);
            this.zhuanchu_keshi_tv = rootView.findViewById(R.id.zhuanchu_keshi_tv);
            this.zhuanchu_doctor_tv = rootView.findViewById(R.id.zhuanchu_doctor_tv);
            this.zhuanchu_time_tv = rootView.findViewById(R.id.zhuanchu_time_tv);
            this.zhuanru_hosit_tv = rootView.findViewById(R.id.zhuanru_hosit_tv);
            this.zhuanru_keshi_tv = rootView.findViewById(R.id.zhuanru_keshi_tv);
            this.zhuanru_doctor_tv = rootView.findViewById(R.id.zhuanru_doctor_tv);
            this.zhuanru_time_tv = rootView.findViewById(R.id.zhuanru_time_tv);
            this.zhuanchu_status_tv = rootView.findViewById(R.id.zhuanchu_status_tv);
            this.jujue_tv = rootView.findViewById(R.id.jujue_tv);
        }
    }

    private onButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(onButtonClickListener
                                                 onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public interface onButtonClickListener {
        void onClick(int position);
    }
}
