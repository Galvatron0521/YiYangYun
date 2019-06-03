package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.bean.ZhuanInBean;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import java.util.List;

public class ZhuanInAdapter extends BaseAdapter {

    private Context mContext;
    private List<ZhuanInBean.DataBean.ListBean> mDatas;

    public ZhuanInAdapter(Context mContext, List<ZhuanInBean.DataBean.ListBean> mDatas) {
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
            view = View.inflate(mContext, R.layout.item_zhuanru, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.zhuanzhen_item_name.setText(mDatas.get(i).getName());
        viewHolder.zhuanzhen_item_age.setText(mDatas.get(i).getAge()+"岁");
        viewHolder.zhuanzhen_item_sex.setText(mDatas.get(i).getSex());
        viewHolder.zhuanin_hosit.setText("转出医院："+mDatas.get(i).getFromHospitalName());
        viewHolder.zhuanin_keshi.setText("转出科室："+mDatas.get(i).getFromDepartmentName());
        viewHolder.zhuanin_time.setText("转出时间："+ HcUtils.getData(mDatas.get(i).getCreateTime()));
        viewHolder.zhuanin_name.setText("操作医生："+mDatas.get(i).getFromUserName());


        if(mDatas.get(i).getReceiptState()==0){
            viewHolder.layout1.setVisibility(View.VISIBLE);
            viewHolder.layout2.setVisibility(View.GONE);
        }
        if(mDatas.get(i).getReceiptState()==1){
            viewHolder.zhuangtai_item2.setText("已接诊");
            viewHolder.layout1.setVisibility(View.GONE);
            viewHolder.layout2.setVisibility(View.VISIBLE);
        }
        if(mDatas.get(i).getReceiptState()==2){
            viewHolder.zhuangtai_item2.setText("已拒绝");
            viewHolder.layout1.setVisibility(View.GONE);
            viewHolder.layout2.setVisibility(View.VISIBLE);
        }

        viewHolder.patient_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDatas.get(i).getReceiptState()==1){
                    onButtonClickListener.onClick(3,i);
                }else{
                    ToastUtils.showMessage(mContext,"请确认转诊后查看");
                }
            }
        });

        viewHolder.queren_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickListener.onClick(0,i);
            }
        });
        viewHolder.jujue_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClickListener.onClick(1,i);
            }
        });
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public LinearLayout patient_item;
        public LinearLayout layout1;
        public LinearLayout layout2;
        public TextView zhuanzhen_item_name;
        public TextView zhuanzhen_item_sex;
        public TextView zhuanzhen_item_age;
        public TextView zhuanin_hosit;
        public TextView zhuanin_keshi;
        public TextView zhuanin_name;
        public TextView zhuanin_time;
        public TextView queren_item;
        public TextView jujue_item;
        public TextView zhuangtai_item2;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.patient_item = rootView.findViewById(R.id.patient_item);
            this.zhuanzhen_item_name = rootView.findViewById(R.id.zhuanzhen_item_name);
            this.zhuanzhen_item_sex = rootView.findViewById(R.id.zhuanzhen_item_sex);
            this.zhuanzhen_item_age = (TextView) rootView.findViewById(R.id.zhuanzhen_item_age);
            this.zhuanin_hosit = (TextView) rootView.findViewById(R.id.zhuanin_hosit);
            this.zhuanin_keshi = (TextView) rootView.findViewById(R.id.zhuanin_keshi);
            this.zhuanin_name = (TextView) rootView.findViewById(R.id.zhuanin_name);
            this.zhuanin_time = (TextView) rootView.findViewById(R.id.zhuanin_time);


            this.queren_item = (TextView) rootView.findViewById(R.id.queren_item);
            this.jujue_item = (TextView) rootView.findViewById(R.id.jujue_item);
            this.zhuangtai_item2 = (TextView) rootView.findViewById(R.id.zhuangtai_item2);
            this.layout2 =  rootView.findViewById(R.id.layout2);
            this.layout1 =  rootView.findViewById(R.id.layout1);
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
