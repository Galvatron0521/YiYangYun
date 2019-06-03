package com.wd.yiyangyun.mvp.patient.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.mvp.home.bean.BianZhengBean;
import com.wd.yiyangyun.mvp.patient.bean.PatientGroupBean;
import com.wd.yiyangyun.utils.CommonlyUtils;
import com.wd.yiyangyun.utils.DateUtils;
import com.wd.yiyangyun.utils.GlideCircleTransUtils;

import java.util.Date;
import java.util.List;

import static com.wd.yiyangyun.utils.DateUtils.FORMAT_1;
import static com.wd.yiyangyun.utils.DateUtils.FORMAT_5;

public class PatientGroupAdapter implements ExpandableListAdapter {
    private Context mContext;
    private List<PatientGroupBean.DataBean.GroupListBean> parentList;
    private List<List<PatientGroupBean.DataBean.GroupListBean.PatientListBean>> childlists;

    public PatientGroupAdapter(Context mContext, List<PatientGroupBean.DataBean.GroupListBean> parentList,
                               List<List<PatientGroupBean.DataBean.GroupListBean.PatientListBean>> childlists) {
        this.mContext = mContext;
        this.parentList = parentList;
        this.childlists = childlists;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return parentList == null ? 0 : parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childlists == null ? 0 : childlists.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Log.e("ttttttt", "getChild: "+groupPosition+"   "+childPosition);
        return childlists.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int i, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(mContext, R.layout.group_item, null);
        }
        TextView item_name = view.findViewById(R.id.group_name_tv);
        TextView item_num = view.findViewById(R.id.group_num_tv);
        PatientGroupBean.DataBean.GroupListBean parentModuleBean = parentList.get
                (groupPosition);
        item_name.setText(parentModuleBean.getGroupName());
        item_num.setText(parentModuleBean.getNum()+"人");
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(mContext, R.layout.group_child_item, null);
        }
        ImageView item_patient_logo = view.findViewById(R.id.item_patient_logo);
        TextView item_patient_name = view.findViewById(R.id.item_patient_name);
        TextView item_patient_phone = view.findViewById(R.id.item_patient_phone);
        TextView item_patient_time = view.findViewById(R.id.item_patient_time);
        Glide.with(mContext)
                .load(Constants.PATIENT_IV)
                .transform(new GlideCircleTransUtils(mContext))
                .into(item_patient_logo);
        PatientGroupBean.DataBean.GroupListBean.PatientListBean parentModuleBean = parentList.get
                (groupPosition).getPatientList().get(childPosition);
//        item_patient_logo.setText(parentModuleBean.getName().substring(0, 1));
        item_patient_name.setText(parentModuleBean.getName());
        item_patient_phone.setText(parentModuleBean.getMobile());
        item_patient_time.setText(DateUtils.dateToString(new Date(parentModuleBean.getCreateTime()),FORMAT_5));
        return view;
    }

    //子item点击事件开关
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }
}
