package com.wd.yiyangyun.mvp.home.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.bean.BianZhengBean;

import java.util.List;

public class MyExpandableListViewAdapter implements ExpandableListAdapter {

    private Context mContext;
    private List<BianZhengBean.DataBean.ModuleListBean.ParentModuleBean> parentList;
    private List<List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean>> childlists;

    public MyExpandableListViewAdapter(Context mContext, List<BianZhengBean.DataBean.ModuleListBean
            .ParentModuleBean> parentList, List<List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean
            >> childlists) {
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
    public int getChildrenCount(int i) {
        return childlists == null ? 0 : childlists.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return parentList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childlists.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(mContext, R.layout.bianzheng_item_text, null);
        }
        TextView item_name = view.findViewById(R.id.item_bianzheng_name);
        BianZhengBean.DataBean.ModuleListBean.ParentModuleBean parentModuleBean = parentList.get
                (groupPosition);
        item_name.setText(parentModuleBean.getModuleName());
        item_name.setTextSize(16);
        if (parentModuleBean.isSelect) {
            item_name.setBackgroundColor(Color.parseColor("#f8b203"));
        } else{
            item_name.setBackgroundColor(Color.WHITE);
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(mContext, R.layout.bianzheng_child_item_text, null);
        }
        TextView item_name = view.findViewById(R.id.item_bianzheng_name);
        BianZhengBean.DataBean.ModuleListBean.ChildlistBean.TagModuleBean parentModuleBean = childlists.get
                (groupPosition).get(childPosition).getTagModule();
        item_name.setText(parentModuleBean.getModuleName());
        item_name.setTextSize(14);
        if (childlists.get(groupPosition).size() == 1) {
            item_name.setVisibility(View.GONE);
        } else {
            item_name.setVisibility(View.VISIBLE);
            if (parentModuleBean.isSelect) {
                item_name.setTextColor(Color.parseColor("#f7b102"));
            }
            else{
                item_name.setTextColor(Color.parseColor("#42484b"));

            }
        }
        return view;
    }

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
