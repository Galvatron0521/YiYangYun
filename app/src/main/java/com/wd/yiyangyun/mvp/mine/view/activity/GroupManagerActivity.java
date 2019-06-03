package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.wd.yiyangyun.HttpCallback;
import com.wd.yiyangyun.HttpResMsg;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.TagListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.AddGroupBean;
import com.wd.yiyangyun.mvp.mine.bean.DeleteGroupBean;
import com.wd.yiyangyun.mvp.mine.bean.GroupListBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.app.Constants.GROUP_MANAGER;
import static com.wd.yiyangyun.utils.SynchniceUtil.addGroupBean;
import static com.wd.yiyangyun.utils.SynchniceUtil.deleteGroupBean;
import static com.wd.yiyangyun.utils.SynchniceUtil.queryGroupBean;
import static com.wd.yiyangyun.utils.SynchniceUtil.updateGroupBean;

public class GroupManagerActivity extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private List<GroupListBean.DataBean.ListBean> mData;
    private TagListAdapter tagListAdapter;
    private ListView group_list;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_group_manager;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        group_list = findViewById(R.id.group_list);
        title_back.setVisibility(View.VISIBLE);
        title_right_tv.setVisibility(View.VISIBLE);
        title_name.setText("分组管理");
        title_right_tv.setText("添加");
    }

    @Override
    protected void initData() {
        super.initData();
        mData = new ArrayList<>();
        tagListAdapter = new TagListAdapter(GroupManagerActivity.this,mData);
        group_list.setAdapter(tagListAdapter);
        requestTagList();
    }

    private void requestTagList() {

        final Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID,""));
        DoctorNetService.requestGroupList(GROUP_MANAGER, map, new NetWorkRequestInterface() {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Object info) {
                GroupListBean groupListBean = (GroupListBean) info;
                LogUtil.e("query0",groupListBean.getData().getList().size()+"");
                mData.clear();
                mData.addAll(groupListBean.getData().getList());
                LogUtil.e("query",mData.get(0).getGroupName());
                tagListAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    protected void initListener() {
        super.initListener();
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title_right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog("请输入标签名称", new OnInputClickListener() {
                    @Override
                    public void onClick(String text, View v) {
                        if (TextUtils.isEmpty(text)) {
                            ToastUtils.showMessage(mContext, "标签名称不能为空");
                            return;
                        }
                        requestAddTag(text);
                    }
                });
            }
        });

        tagListAdapter.setOnButtonClickListener(new TagListAdapter.onButtonClickListener() {
            @Override
            public void onClick(int type, int position) {
                final GroupListBean.DataBean.ListBean bean = mData.get(position);
                if (type == tagListAdapter.ON_DELETE_CLICK) {
                    showAffirmDialog("提示", "确认删除该标签？", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            requestDeleteTag(bean.getId());
                        }
                    });
                }
                if(type == tagListAdapter.ON_EDIT_CLICK){
                    showInputDialog("请输入标签名称", new OnInputClickListener() {
                        @Override
                        public void onClick(String text, View v) {
                            if (TextUtils.isEmpty(text)) {
                                ToastUtils.showMessage(mContext, "标签名称不能为空");
                                return;
                            }
                            requestUpDataTag(bean.getId(), text);
                        }
                    });
                }
            }
        });

    }

    private void requestAddTag(String text) {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        map.put("groupName", text);
        DoctorNetService.addGroupList(Constants.ADD_GROUP, map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtils.showMessage(mContext, "添加失败，请重试");
                    }

                    @Override
                    public void onNext(Object info) {
                        AddGroupBean bean = (AddGroupBean) info;
                        ToastUtils.showMessage(mContext, bean.getData().getData());
                        requestTagList();
                    }
                });

    }

    /**
     * 删除标签
     *
     * @param id
     */
    private void requestDeleteTag(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        String json = JSONUtil.parseMapToJson(map);
        DoctorNetService.deleteGroupList(Constants.DELETE_GROUP, map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtils.showMessage(mContext, "删除失败，请重试");
                    }

                    @Override
                    public void onNext(Object info) {
                        AddGroupBean bean = (AddGroupBean) info;
                        ToastUtils.showMessage(mContext, bean.getData().getData());
                        requestTagList();
                    }
                });
    }


    /**
     * 更新标签
     *
     * @param id
     * @param text
     */
    private void requestUpDataTag(String id, String text) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("groupName", text);
        DoctorNetService.updateGroupList(Constants.UPDATE_GROUP, map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtils.showMessage(mContext, "修改失败，请重试");
                    }

                    @Override
                    public void onNext(Object info) {
                        AddGroupBean bean = (AddGroupBean) info;
                        ToastUtils.showMessage(mContext, bean.getData().getData());
                        requestTagList();
                    }
                });
    }

    @Override
    public Context context() {
        return null;
    }
}
