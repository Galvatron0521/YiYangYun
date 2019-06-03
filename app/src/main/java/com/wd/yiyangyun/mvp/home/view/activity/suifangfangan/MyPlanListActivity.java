package com.wd.yiyangyun.mvp.home.view.activity.suifangfangan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.MyPlanListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;
import com.wd.yiyangyun.mvp.patient.bean.PatientInfoBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPlanListActivity extends BaseActivity {
    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ListView my_plan_listview;
    private SpringView my_plan_spring;
    private TextView add_my_plan;
    private MyPlanListAdapter suiFangListAdapter;
    private String patientID;
    private int pageCount = 10;
    private int pageNo = 0;
    private List<MyPlanListBean.FollowlistBean> mDatas;
    private PatientInfoBean infoBean;


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_my_plan_list;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);

        my_plan_listview = (ListView) findViewById(R.id.my_plan_listview);
        my_plan_spring = (SpringView) findViewById(R.id.my_plan_spring);
        add_my_plan = (TextView) findViewById(R.id.add_my_plan);

        title_back.setVisibility(View.VISIBLE);
        title_name.setText("我的随访方案");

        my_plan_spring.setHeader(new DefaultHeader(mContext));
        my_plan_spring.setFooter(new DefaultFooter(mContext));

        EventBus.getDefault().register(this);
        patientID = getIntent().getStringExtra("patientID");
        infoBean = (PatientInfoBean) getIntent().getSerializableExtra("info");
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        suiFangListAdapter = new MyPlanListAdapter(mContext, mDatas);
        my_plan_listview.setAdapter(suiFangListAdapter);
        requestSuiFangList();
    }

    /**
     * patientID":40,
     * "pageNo":"0",
     * "pageCount":"8"
     */
    private void requestSuiFangList() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageCount", pageCount + "");
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        DoctorNetService.requestMyPlanList(Constants.SELECT_FOLLOW_PLAN_LIST, map,
                new NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        MyPlanListBean bean = (MyPlanListBean) info;
                        mDatas.addAll(bean.followlist);
                        suiFangListAdapter.notifyDataSetChanged();
                        my_plan_spring.onFinishFreshAndLoad();
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
        add_my_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddMyPlanActivity.class);
                startActivity(intent);
            }
        });
        //侧滑删除
        suiFangListAdapter.setOnDeleteClickListener(new MyPlanListAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(final int position) {
                showAffirmDialog("提示", "确认删除改随访方案？", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delectProject(position);
                    }
                });
            }
        });
        suiFangListAdapter.setOnItemClickListener(new MyPlanListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyPlanListBean.FollowlistBean bean = mDatas.get(position);
                MyPlanListBean.FollowlistBean followlistBean = new MyPlanListBean.FollowlistBean();
                followlistBean.planName = bean.planName;
                followlistBean.baseDate = bean.baseDate;
                followlistBean.projectlist = new ArrayList<>();
                for (MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean : bean
                        .projectlist) {
                    MyPlanListBean.FollowlistBean.ProjectlistBean projectBean = new
                            MyPlanListBean.FollowlistBean.ProjectlistBean();
                    projectBean.project = new MyPlanListBean.FollowlistBean.ProjectlistBean
                            .ProjectBean();
                    projectBean.project.taskNum = projectlistBean.taskNum;
                    projectBean.project.beforeOrAfter = projectlistBean.beforeOrAfter;
                    projectBean.project.scope = projectlistBean.scope;
                    projectBean.project.amount = projectlistBean.amount;
                    projectBean.project.unit = projectlistBean.unit;
                    projectBean.project.followprojectID = projectlistBean.followprojectID;
                    projectBean.project.optionlist = new ArrayList<>();
                    for (MyPlanListBean.FollowlistBean.ProjectlistBean.OptionlistBean
                            optionlistBean : projectlistBean.optionlist) {
                        MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean.OptionlistBean
                                bean1 = new MyPlanListBean.FollowlistBean.ProjectlistBean
                                .ProjectBean.OptionlistBean("0",
                                optionlistBean.optionModuleCodes);
                        projectBean.project.optionlist.add(bean1);
                    }
                    followlistBean.projectlist.add(projectBean);
                }

                Intent intent = new Intent(mContext, QueryMyPlanActivity.class);
                intent.putExtra("isAdd", false);
                intent.putExtra("followprojectID", bean.id);
                intent.putExtra("name", bean.planName);
                Log.e("tttt", "onItemClick:修改 "+bean.id);
                intent.putExtra("info", (Serializable) followlistBean);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        my_plan_spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mDatas.clear();
                pageNo = 0;
                requestSuiFangList();
            }

            @Override
            public void onLoadmore() {
                pageNo = pageNo + pageCount;
                requestSuiFangList();
            }
        });
    }

    /**
     * 删除方案
     * "followPlanID":"4028801e65eaba910165eaba91840000","mobileType":"2","userID":1
     */
    private void delectProject(int position) {
        showWaitDialog();
        MyPlanListBean.FollowlistBean bean = mDatas.get(position);
        Map<String, Object> map = new HashMap<>();
        map.put("followPlanID", bean.id);
        map.put("mobileType", Constants.MOBILE_TYPE);
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
//        DoctorNetService.deleteGroupList(Constants.DELECT_PLAN, map, new NetWorkRequestInterface() {
//            @Override
//            public void onError(Throwable throwable) {
//                hideWaitDialog();
//                        ToastUtils.showMessage(mContext, "删除失败，请重试");
//            }
//
//            @Override
//            public void onNext(Object info) {
//                hideWaitDialog();
//                AddGroupBean bean = (AddGroupBean) info;
//                        ToastUtils.shortToast(mContext, bean.getData().getData());
//                        mDatas.clear();
//                        pageNo = 0;
//                        requestSuiFangList();
////                        Map<String, Object> map = new HashMap<>();
////                        map.put(Constants.EVENTBUS_TYEPE, CHANG_FOLLOW_SUCCESS);
////                        EventBus.getDefault().post(map);
//            }
//        });
        DoctorNetService.requestResult(Constants.DELECT_PLAN, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                        ToastUtils.showMessage(mContext, "删除失败，请重试");
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        ResultBean bean = (ResultBean) info;
                        ToastUtils.shortToast(mContext, bean.getData().getData());
                        mDatas.clear();
                        pageNo = 0;
                        requestSuiFangList();
//                        Map<String, Object> map = new HashMap<>();
//                        map.put(Constants.EVENTBUS_TYEPE, CHANG_FOLLOW_SUCCESS);
//                        EventBus.getDefault().post(map);
                    }
                });


    }

    @Subscribe
    public void onEventMainThread(Map<String, Object> map) {
        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
        if (type.equals(AddMyPlanActivity.CHANG_FOLLOW_SUCCESS)) {
            pageNo = 0;
            mDatas.clear();
            requestSuiFangList();
        }
    }

    @Override
    public Context context() {
        return null;
    }
}
