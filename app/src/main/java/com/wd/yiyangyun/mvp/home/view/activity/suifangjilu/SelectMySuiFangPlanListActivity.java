package com.wd.yiyangyun.mvp.home.view.activity.suifangjilu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.base.DoctorBaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.MyPlanCrfListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.PatientSuiFangListBean;
import com.wd.yiyangyun.mvp.home.view.activity.suifangfangan.MyPlanCRFListBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SelectMySuiFangPlanListActivity extends DoctorBaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ListView my_plan_list_cancel_listview;
    private SpringView my_plan_list_cancel_springview;
    private String taskOption;
    private int pageCount = 10;
    private int pageNo = 0;
    private List<MyPlanCRFListBean.CrflistBean> mDatas;
    private MyPlanCrfListAdapter myPlanCrfListAdapter;
    private List<PatientSuiFangListBean.ListBean.ProjectlistBean.OptionlistBean> optionlist;
    public static String MY_PLAN_SELECT = "myPlanSelect";
    private int position;
    private String followprojectID;
    private String patientID;
    private String followRecordId;
    private int taskNum;
    private String followID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suifang_select_my_plan_list);
        taskOption = getIntent().getStringExtra("taskOption");
        patientID = getIntent().getStringExtra("patientID");
        followRecordId = getIntent().getStringExtra("followRecordId");
        followprojectID = getIntent().getStringExtra("id");
        followID = getIntent().getStringExtra("followID");
        position = getIntent().getIntExtra("position", -1);
        taskNum = getIntent().getIntExtra("taskNum", -1);
        optionlist = (List<PatientSuiFangListBean.ListBean.ProjectlistBean.OptionlistBean>)
                getIntent()
                        .getSerializableExtra("info");
        initView();
        initData();
        initListener();
    }

    private void initView() {
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        my_plan_list_cancel_listview = (ListView) findViewById(R.id.my_plan_list_cancel_listview);
        my_plan_list_cancel_springview = (SpringView) findViewById(R.id
                .my_plan_list_cancel_springview);
        my_plan_list_cancel_springview.setFooter(new DefaultFooter(mContext));

        title_back.setVisibility(View.VISIBLE);
        title_name.setText("选择表单");
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        myPlanCrfListAdapter = new MyPlanCrfListAdapter(mContext, mDatas);
        my_plan_list_cancel_listview.setAdapter(myPlanCrfListAdapter);
        myPlanCrfListAdapter.setCanEdit(false);
        requestListDatas();


    }

    /**
     * act=selectFollowCRFListNew&data={"followPlanID":"4028801e65eabe520165eac42e6a0004",
     * "typeFlag":0,"pageNo":"0",  "pageCount":"8"}
     * <p> http://111.17.215.37:8023/skyapp_xy/api/app_patient/?act=selectFollowCRFListNew&data={"typeFlag":"0","pageCount":"10","followPlanID":"4028e47b68e06eda0168e4c2089b0055","pageNo":"0"}

     * followPlanID:随访方案计划id,如果要查询所有量表和CRF表,followPlanID不传就好,"typeFlag":0代表量化指标,1代表量表
     */
    private void requestListDatas() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("typeFlag", taskOption);
        Log.e("followprojectID", "requestListDatas: "+followRecordId);
        map.put("followPlanID", followID);
        map.put("pageNo", pageNo + "");
        map.put("pageCount", pageCount + "");
        map.put("taskNum", taskNum);
        DoctorNetService.requestFollowCRFList(Constants.SELECT_FOLLOW_CRF_LIST_NEW, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        try {
                            MyPlanCRFListBean myPlanCRFListBean = (MyPlanCRFListBean) info;
//                            Log.e("tttttt", "onNext: "+myPlanCRFListBean.alllist.toString());
                            if (taskOption.equals("0")) {
                                for (MyPlanCRFListBean.CrflistBean crflistBean : myPlanCRFListBean
                                        .crflist) {
                                    mDatas.add(new MyPlanCRFListBean.CrflistBean(crflistBean
                                            .moduleName, crflistBean.moduleCode, false,"0"));
                                }
                            }
                            if(taskOption.equals("1")){
                                for (MyPlanCRFListBean.ScaleListBean scaleListBean :
                                        myPlanCRFListBean.scaleList) {
                                    mDatas.add(new MyPlanCRFListBean.CrflistBean(scaleListBean
                                            .moduleName, scaleListBean.moduleCode, false,"1"));
                                }
                            }
                            if(taskOption.equals("2")){
                                for (MyPlanCRFListBean.AllListBean scaleListBean :
                                        myPlanCRFListBean.allList) {
                                    mDatas.add(new MyPlanCRFListBean.CrflistBean(scaleListBean
                                            .moduleName, scaleListBean.moduleCode, false,"2"));
                                }
                            }
                            if (!TextUtils.isEmpty(followprojectID)) {
                                myPlanCrfListAdapter.setCanEdit(false);
                            } else {
                                myPlanCrfListAdapter.setCanEdit(false);
                                if (optionlist != null) {
                                    for (MyPlanCRFListBean.CrflistBean mData : mDatas) {
                                        for (PatientSuiFangListBean.ListBean.ProjectlistBean
                                                .OptionlistBean optionlistBean : optionlist) {
                                            if (mData.moduleCode.equals(optionlistBean
                                                    .optionModuleCodes)) {
                                                mData.isCheck = true;
                                            }
                                        }
                                    }
                                }
                            }
                            LogUtil.e("6666----" + mDatas.size());
                            myPlanCrfListAdapter.notifyDataSetChanged();
                            my_plan_list_cancel_springview.onFinishFreshAndLoad();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void initListener() {
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        my_plan_list_cancel_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(mContext, LiangBiaoListActivity
                            .class);
                    intent.putExtra("moduleCode", mDatas.get(i).moduleCode);
                    intent.putExtra("patientID", patientID);
                    intent.putExtra("followRecordId", followRecordId);
                    intent.putExtra("taskNum", taskNum);
                    startActivity(intent);
            }
        });
        myPlanCrfListAdapter.setOnButtonClickListener(new MyPlanCrfListAdapter
                .onButtonClickListener() {
            @Override
            public void onClick(String type, int position) {
                mDatas.get(position).isCheck = !mDatas.get(position).isCheck;
            }
        });
        my_plan_list_cancel_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                mDatas.clear();
//                pageNo = 0;
//                requestListDatas();
                my_plan_list_cancel_springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                pageNo = pageNo + pageCount;
                mDatas.clear();
                requestListDatas();
            }
        });
    }
}
