package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.bean.ZhuanOutBean;
import com.wd.yiyangyun.utils.HcUtils;

import java.util.List;

public class ZhuanOutAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZhuanOutBean.DataBean.ListBean> mDatas;

    public ZhuanOutAdapter(Context mContext, List<ZhuanOutBean.DataBean.ListBean> mDatas) {
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
            view = View.inflate(mContext, R.layout.item_zhuanchu, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.zhuanzhen_item_name.setText(mDatas.get(i).getName());
        viewHolder.zhuanzhen_item_age.setText(mDatas.get(i).getAge()+"岁");
        viewHolder.zhuanzhen_item_sex.setText(mDatas.get(i).getSex());
        viewHolder.zhuanout_hosit.setText("转入医院："+mDatas.get(i).getToHospitalName());
        viewHolder.zhuanout_keshi.setText("转入科室："+mDatas.get(i).getToDepartmentName());
        viewHolder.zhuanzhen_item_doctor.setText("接受医生："+mDatas.get(i).getToUserName());

        if(mDatas.get(i).getReceiptState()==0){
            viewHolder.zhuangtai_item.setText("未接诊");
            viewHolder.jujue_ll_item.setVisibility(View.GONE);
        }
        if(mDatas.get(i).getReceiptState()==1){
            viewHolder.zhuangtai_item.setText("已接诊");
            viewHolder.jujue_ll_item.setVisibility(View.GONE);
            viewHolder.jiezhen_item_time.setText("接受时间:"+HcUtils.getData(mDatas.get(i).getCreateTime()));
        }
        if(mDatas.get(i).getReceiptState()==2){
            viewHolder.jiezhen_item_time.setText("拒绝时间:"+ HcUtils.getData(mDatas.get(i).getCreateTime()));
            viewHolder.zhuangtai_item.setText("已拒绝");
            viewHolder.jujue_ll_item.setVisibility(View.VISIBLE);
            viewHolder.zhuanzhen_item_yuanyin.setText(mDatas.get(i).getRefuseReason());
        }
        viewHolder.patient_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickListener.onClick(3,i);
            }
        });

        viewHolder.zhuangtai_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickListener.onClick(0,i);
            }
        });

        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView zhuanzhen_item_name;
        public TextView zhuanzhen_item_sex;
        public TextView zhuanzhen_item_age;
        public TextView zhuangtai_item;


        public TextView jiezhen_item_time;
        public TextView zhuanout_hosit;
        public TextView zhuanzhen_item_doctor;
        public TextView zhuanzhen_item_yuanyin;
        public TextView zhuanout_keshi;
        public LinearLayout jujue_ll_item;
        public LinearLayout patient_item;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.patient_item = rootView.findViewById(R.id.patient_item);
            this.zhuanzhen_item_name = rootView.findViewById(R.id.zhuanzhen_item_name);
            this.zhuanzhen_item_sex = rootView.findViewById(R.id.zhuanzhen_item_sex);
            this.zhuanzhen_item_age = (TextView) rootView.findViewById(R.id.zhuanzhen_item_age);
            this.zhuangtai_item = (TextView) rootView.findViewById(R.id.zhuangtai_item);
            this.zhuanzhen_item_doctor = (TextView) rootView.findViewById(R.id.zhuanzhen_item_doctor);
            this.jiezhen_item_time = (TextView) rootView.findViewById(R.id.jiezhen_item_time);
            this.zhuanzhen_item_yuanyin = (TextView) rootView.findViewById(R.id.zhuanzhen_item_yuanyin);
            this.jujue_ll_item =  rootView.findViewById(R.id.jujue_ll_item);
            this.zhuanout_hosit =  rootView.findViewById(R.id.zhuanout_hosit);
            this.zhuanout_keshi =  rootView.findViewById(R.id.zhuanout_keshi);
        }
    }

    private onButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(onButtonClickListener
                                                 onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public interface onButtonClickListener {
        void onClick(int type,int position);
    }

}
